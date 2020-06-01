package com.ant.resources;

import com.ant.resources.util.ResourceUtil;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

/**
 * <p>
 * 自定义 {@link ResourcePatternResolver} 示例
 * </p>
 *
 * @author GaoXin
 * @since 2020/5/31 6:54 下午
 */
public class CustomizedResourcePatternResolverDemo {

	public static void main(String[] args) throws IOException {
		// 读取当前 package 对应的所有 .java 文件
		// *.java
		String currentPackagePath = "/" + System.getProperty("user.dir") + "/thinking-in-spring/resource/src/main/java/com/ant/resources/";

		String locationPattern = currentPackagePath + "*.java";

		PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());

		resourcePatternResolver.setPathMatcher(new JavaFileClassMatcher());

		Resource[] resource = resourcePatternResolver.getResources(locationPattern);


		Stream.of(resource).map(ResourceUtil::getContent).forEach(System.out::println);
	}

	static class JavaFileClassMatcher implements PathMatcher {

		@Override
		public boolean isPattern(String path) {
			return path.endsWith(".java");
		}

		@Override
		public boolean match(String pattern, String path) {
			return path.endsWith(".java");
		}

		@Override
		public boolean matchStart(String pattern, String path) {
			return false;
		}

		@Override
		public String extractPathWithinPattern(String pattern, String path) {
			return null;
		}

		@Override
		public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
			return null;
		}

		@Override
		public Comparator<String> getPatternComparator(String path) {
			return null;
		}

		@Override
		public String combine(String pattern1, String pattern2) {
			return null;
		}
	}
}
