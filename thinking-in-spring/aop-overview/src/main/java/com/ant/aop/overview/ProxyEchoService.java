package com.ant.aop.overview;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/12/24 8:54 下午
 */
public class ProxyEchoService implements EchoService {
	private final EchoService echoService;

	public ProxyEchoService(EchoService echoService) {
		this.echoService = echoService;
	}

	@Override
	public String echo(String message) {
		long startTime = System.nanoTime();
		echoService.echo(message);
		long costTime = System.nanoTime() - startTime;
		return "echo 方法执行的时间：" + costTime/1000 + "us";
	}
}
