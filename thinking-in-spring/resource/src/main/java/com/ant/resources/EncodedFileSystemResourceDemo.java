package com.ant.resources;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * <p>
 * 带有字符编码的 {@link FileSystemResource} 示例
 * </p>
 *
 * @author Ant
 * @since 2020/5/30 10:20 下午
 *
 * @see FileSystemResource
 * @see EncodedResource
 */
public class EncodedFileSystemResourceDemo {

	public static void main(String[] args) throws IOException {
		String currentJavaFilePath = System.getProperty("user.dir") + "/thinking-in-spring/resource/src/main/java/com/ant/resources/EncodedFileSystemResourceDemo.java";

		File currentJavaFile = new File(currentJavaFilePath);

		// FileSystemResource => WritableResource => Resource
		FileSystemResource fileSystemResource = new FileSystemResource(currentJavaFilePath);

		EncodedResource encodedResource = new EncodedResource(fileSystemResource, "UTF-8");

		// 字符输入流
		try (Reader reader = encodedResource.getReader()) {
			System.out.println(IOUtils.toString(reader));
		}

	}
}
