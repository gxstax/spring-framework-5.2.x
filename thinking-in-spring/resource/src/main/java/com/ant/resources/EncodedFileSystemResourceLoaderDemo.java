package com.ant.resources;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * <p>
 * 带有字符编码的 {@link FileSystemResourceLoader} 示例
 * </p>
 *
 * @author Ant
 * @since 2020/5/30 10:20 下午
 *
 * @see FileSystemResourceLoader
 * @see FileSystemResource
 * @see EncodedResource
 */
public class EncodedFileSystemResourceLoaderDemo {

	public static void main(String[] args) throws IOException {
		String currentJavaFilePath = "/" + System.getProperty("user.dir") + "/thinking-in-spring/resource/src/main/java/com/ant/resources/EncodedFileSystemResourceDemo.java";

		// FileSystemResourceLoader => DefaultResourceLoader => ResourceLoader
		FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();

		Resource resource = resourceLoader.getResource(currentJavaFilePath);
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

		// 字符输入流
		try (Reader reader = encodedResource.getReader()) {
			System.out.println(IOUtils.toString(reader));
		}

	}
}
