package com.ant.aop.overview;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * <p>
 * AOP 目标过滤示例
 * </p>
 *
 * @author Ant
 * @since 2020/12/25 9:44 上午
 */
public class TargetFilterDemo {

	public static void main(String[] args) throws ClassNotFoundException {
		String targetClassName = "com.ant.aop.overview.proxy.EchoService";
		// 获取目标类
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Class<?> targetClass = classLoader.loadClass(targetClassName);

		Method targetMethod = ReflectionUtils.findMethod(targetClass, "echo", String.class);
		System.out.println(targetMethod);

		// 查找 throws 类型为 NullPointerException 的方法
		ReflectionUtils.doWithMethods(targetClass,
				methodCallback ->
						System.out.println("仅抛出 NullPointerException 的方法为：" + methodCallback),
				methodFilter -> {
					Class<?>[] parameterTypes = methodFilter.getParameterTypes();
					Class<?>[] exceptionTypes = methodFilter.getExceptionTypes();

					return parameterTypes.length == 1
							&& parameterTypes[0].equals(String.class)
							&& exceptionTypes.length == 1
							&& exceptionTypes[0].equals(NullPointerException.class);
				});
	}

}
