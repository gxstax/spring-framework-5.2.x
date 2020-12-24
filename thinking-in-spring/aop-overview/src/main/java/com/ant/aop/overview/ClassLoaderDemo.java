package com.ant.aop.overview;

/**
 * <p>
 * 类加载器示例
 * </p>
 *
 * @author Ant
 * @since 2020/12/24 5:49 下午
 */
public class ClassLoaderDemo {
	public static void main(String[] args) {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

		System.out.println(contextClassLoader.toString());

		ClassLoader parentClassLoader = contextClassLoader.getParent();;
		while (true) {
			if (null == parentClassLoader) {
				break;
			}
			System.out.println(parentClassLoader.toString());
			parentClassLoader = parentClassLoader.getParent();
		}
	}
}
