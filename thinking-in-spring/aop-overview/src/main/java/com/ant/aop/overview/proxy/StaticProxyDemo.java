package com.ant.aop.overview.proxy;

/**
 * <p>
 * 静态代理示例
 * </p>
 *
 * @author Ant
 * @since 2020/12/24 8:50 下午
 */
public class StaticProxyDemo {

	public static void main(String[] args) {
		EchoService echoService = new ProxyEchoService(new DefaultEchoService());
		System.out.println(echoService.echo("Hello World"));
	}

}
