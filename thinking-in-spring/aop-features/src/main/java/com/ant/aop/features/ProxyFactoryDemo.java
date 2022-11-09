package com.ant.aop.features;

import com.ant.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.ant.aop.overview.proxy.DefaultEchoService;
import com.ant.aop.overview.proxy.EchoService;
import org.springframework.aop.framework.ProxyFactory;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2021/1/1 7:55 下午
 */
public class ProxyFactoryDemo {

	public static void main(String[] args) {
		DefaultEchoService defaultEchoService = new DefaultEchoService();

		// 注入目标对象（被代理）
		ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);

		// 添加 Advice 实现 （MethodInterceptor < Interceptor < Advice）
		proxyFactory.addAdvice(new EchoServiceMethodInterceptor());

		// 获取代理对象
		EchoService echoService = (EchoService) proxyFactory.getProxy();

		System.out.println(echoService.echo("Hello, World"));

	}

}
