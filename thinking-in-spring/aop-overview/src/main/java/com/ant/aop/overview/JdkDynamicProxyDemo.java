package com.ant.aop.overview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 * JDK 动态代理示例
 * </p>
 *
 * @author Ant
 * @since 2020/12/24 9:06 下午
 */
@SuppressWarnings("rawtypes")
public class JdkDynamicProxyDemo {

	public static void main(String[] args) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		EchoService proxyObj = (EchoService) Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
					ProxyEchoService proxyEchoService = new ProxyEchoService(new DefaultEchoService());
					return proxyEchoService.echo((String) args[0]);
				}
				return null;
			}
		});

		System.out.println(proxyObj.echo("Hello, World"));

	}

}
