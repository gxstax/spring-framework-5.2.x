package com.ant.resources;

import com.ant.resources.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * <p>
 * 注入 {@link ResourceLoader} 对象示例
 * </p>
 *
 * @author GaoXin
 * @since 2020/5/31 10:39 下午
 *
 * @see Resource
 * @see Value
 * @see AnnotationConfigApplicationContext
 */
public class InjectingResourceLoaderDemo implements ResourceLoaderAware {

	// 方法一
	private ResourceLoader resourceLoader;

	// 方法二
	@Autowired
	private ResourceLoader autowiredResourceLoader;

	// 方法三
	@Autowired
	private AbstractApplicationContext applicationContext;

	@PostConstruct
	public void init() {
		System.out.println(resourceLoader.getClass());
		System.out.println(autowiredResourceLoader.getClass());
		System.out.println(applicationContext.getClass());
		System.out.println("resourceLoader == autowiredResourceLoader " + (resourceLoader == autowiredResourceLoader));
		System.out.println("resourceLoader == applicationContext " + (resourceLoader == applicationContext));
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 注册当前类为 Configuration class
		context.register(InjectingResourceLoaderDemo.class);

		// 启动 Spring 上下文
		context.refresh();

		// 关闭 Spring 上下文
		context.close();
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
}
