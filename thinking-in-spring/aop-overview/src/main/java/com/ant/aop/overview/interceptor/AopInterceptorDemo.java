package com.ant.aop.overview.interceptor;

import com.ant.aop.overview.DefaultEchoService;
import com.ant.aop.overview.EchoService;
import com.ant.aop.overview.ProxyEchoService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 * AOP 拦截器示例
 * </p>
 *
 * @author Ant
 * @since 2020/12/25 11:00 上午
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class AopInterceptorDemo {

	public static void main(String[] args) {
		// 前置模式 + 后置模式
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		EchoService proxyObj = (EchoService) Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
					Long startTime = 0L;
					Long endTime = 0L;
					Object result = null;
					try {
						// 前置拦截器
						BeforeInterceptor beforeInterceptor = new BeforeInterceptor() {
							@Override
							public Object before(Object proxy, Method method, Object[] args) {
								return System.currentTimeMillis();
							}
						};

						startTime = (long) beforeInterceptor.before(proxy, method, args);
						ProxyEchoService proxyEchoService = new ProxyEchoService(new DefaultEchoService());
						result = proxyEchoService.echo((String) args[0]);

						// 后置拦截器
						AfterInterceptor afterInterceptor = new AfterInterceptor() {
							@Override
							public Object after(Object proxy, Method method, Object[] args, Object returnResult) {
								return System.currentTimeMillis();
							}
						};
						endTime = (long) afterInterceptor.after(proxy, method, args, result);
						Long costTime = endTime - startTime;
					} catch (Exception e) {
						ExceptionInterceptor exceptionInterceptor = new ExceptionInterceptor() {
							@Override
							public void intercept(Object proxy, Method method, Object[] args, Throwable throwable) {
								System.out.println("echo 方法执行异常" + throwable);
							}
						};
					} finally {
						FinallyInterceptor finallyInterceptor = new TimeFinallyInterceptor(startTime, endTime);
						Long costTime = (Long) finallyInterceptor.finalize(proxy, method, args, result);
						System.out.println("echo 方法执行的时间：" + costTime + "ms");
					}
				}
				return null;
			}
		});

		System.out.println(proxyObj.echo("Hello, World"));
	}

}
