package com.ant.resources.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * <p>
 * {@link Resource} 工具类
 * </p>
 *
 * @author GaoXin
 * @since 2020/5/31 7:00 下午
 */
public interface ResourceUtil {


	static String getContent(Resource resource) {
		try {
			return getContent(resource, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	static String getContent(Resource resource, String encoding) throws IOException {
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

		// 字符输入流
		try (Reader reader = encodedResource.getReader()) {
			return IOUtils.toString(reader);
		}

	}

}
