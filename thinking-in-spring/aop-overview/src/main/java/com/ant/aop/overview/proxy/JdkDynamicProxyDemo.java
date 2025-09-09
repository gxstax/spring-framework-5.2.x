package com.ant.aop.overview.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

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

		// class $Proxy0 extends java.lang.reflect.Proxy implements EchoService {}
		// 代理类中的echo方法
//		public final String echo(String var1) throws NullPointerException {
//			try {
//				return (String)super.h.invoke(this, m3, new Object[]{var1});
//			} catch (RuntimeException | Error var3) {
//				throw var3;
//			} catch (Throwable var4) {
//				throw new UndeclaredThrowableException(var4);
//			}
//		}
		// 启用保存生成的代理类
		System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		// $Proxy0
		Object proxyObj = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
					// 代理逻辑
					System.out.println("[Proxy Logic]");
					ProxyEchoService proxyEchoService = new ProxyEchoService(new DefaultEchoService());
					return proxyEchoService.echo((String) args[0]);
				}
				return null;
			}
		});

		EchoService echoService = (EchoService) proxyObj;
		echoService.echo("Hello, World");

		// $Proxy1
		Object proxy2 = Proxy.newProxyInstance(classLoader, new Class[]{Comparable.class}, (proxy, method, args1) -> {
			return null;
		});

		System.out.println(proxy2);
	}

}
