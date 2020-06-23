package com.ant.i18n;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.context.MessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.Watchable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * <p>
 * 动态（更新）资源 {@link MessageSource} 实现
 * </p>
 *
 *
 * 实现步骤
 * 1. 定位资源位置（ properties 文件）
 * 2. 初始化 properties 对象
 * 3. 实现 AbstractMessageSource#resolveCode 方法
 * 4. 监听资源文件 (java NIO 2 WatchService)
 * 5. 使用线程池处理文件变化
 *
 * @author Ant
 * @since 2020/6/14 9:42 下午
 * @see MessageSource
 * @see AbstractMessageSource
 */
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

	private static final String resourceFileName = "msg.properties";

	private static final String resourcePath = "/META-INF/msg.properties";

	private static final String ENCODING = "UTF-8";

	private ResourceLoader resourceLoader;

	private final Properties messageProperties;

	private final Resource messagePropertiesResource;

	private final ExecutorService executorService;

	public DynamicResourceMessageSource() {
		this.messagePropertiesResource = getMessagePropertyResource();
		this.messageProperties = loadMessageProperties();
		this.executorService = Executors.newSingleThreadExecutor();
		// 监听资源文件 (java NIO 2 WatchService)
		onMessagePropertyChanged();
	}

	private void onMessagePropertyChanged() {
		if (this.messagePropertiesResource.isFile()) {
			// 获取文件系统中的文件
			try {
				File messagePropertiesResourceFile = this.messagePropertiesResource.getFile();
				Path messagePropertiesResourcePath = messagePropertiesResourceFile.toPath();
				// 获取当前 OS 文件系统
				FileSystem fileSystem = FileSystems.getDefault();
				// 新建 WatchService
				WatchService watchService = fileSystem.newWatchService();
				// 获取资源文件所在的目录
				Path dirPath = messagePropertiesResourcePath.getParent();
				// 注册 watchService 到 messagePropertiesResourcePath，并且关心修改时间
				dirPath.register(watchService, ENTRY_MODIFY);
				// 处理资源文件变化（异步）
				processMessagePropertyChanged(watchService);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * <p>
	 * 处理资源文件变化（异步）
	 * </p>
	 *
	 * @param watchService
	 * @return void
	 */
	private void processMessagePropertyChanged(WatchService watchService) {
		executorService.submit(() -> {
			while (true) {
				WatchKey watchKey = watchService.take();
				try {
					// 判断 watchKey 是否有效
					if (watchKey.isValid()) {
						for (WatchEvent<?> pollEvent : watchKey.pollEvents()) {
							Watchable watchable = watchKey.watchable();
							// 目录路径（监听的注册目录）
							Path dirPath = (Path) watchable;
							// 事件关联的对象即注册目录的子文件（或子目录）
							// 事件发生源是相对路径
							Path fileRelativePath = (Path) pollEvent.context();
							if (resourceFileName.equals(fileRelativePath.getFileName().toString())) {
								// 处理为绝对路径
								Path filePath = dirPath.resolve(fileRelativePath);
								File file = filePath.toFile();
								Properties properties = loadMessageProperties(new FileReader(file));
								synchronized (messageProperties) {
									messageProperties.clear();
									messageProperties.putAll(properties);
								}
							}
						}
					}
				} finally {
					if (watchKey != null) {
						// 重置 watchKey
						watchKey.reset();
					}
				}
			}
		});
	}

	private Properties loadMessageProperties() {
		EncodedResource encodedResource = new EncodedResource(this.messagePropertiesResource, ENCODING);

		try {
			Reader reader = encodedResource.getReader();
			return loadMessageProperties(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Properties loadMessageProperties(Reader reader) throws IOException {
		Properties properties = new Properties();
		try {
			properties.load(reader);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (null != reader) {
				reader.close();
			}
		}
		return properties;
	}

	private Resource getMessagePropertyResource() {
		ResourceLoader resourceLoader = getResourceLoader();
		final Resource resource = resourceLoader.getResource(resourcePath);
		return resource;

	}

	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		final String property = messageProperties.getProperty(code);
		if (StringUtils.hasText(property)) {
			return new MessageFormat(property, locale);
		}
		return null;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public ResourceLoader getResourceLoader() {
		return this.resourceLoader = resourceLoader != null ? this.resourceLoader : new DefaultResourceLoader();
	}

	public static void main(String[] args) throws InterruptedException {
		DynamicResourceMessageSource resourceMessageSource = new DynamicResourceMessageSource();

		for (int i = 0; i < 10000; i++) {
			final String message = resourceMessageSource.getMessage("name", new Object[]{}, Locale.getDefault());

			System.out.println(message);

			TimeUnit.MILLISECONDS.sleep(1000);
		}
	}
}
