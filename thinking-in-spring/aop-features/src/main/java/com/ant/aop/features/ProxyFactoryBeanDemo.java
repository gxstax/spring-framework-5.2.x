package com.ant.aop.features;

import com.ant.aop.overview.proxy.EchoService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2021/1/1 6:47 下午
 */
@Aspect
@Configuration
public class ProxyFactoryBeanDemo {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("META-INF/spring-aop-context.xml");

		EchoService echoService = context.getBean("echoServiceProxyFactoryBean", EchoService.class);

		System.out.println(echoService.echo("Hello, World"));

		// 关闭 Spring 容器
		context.close();
	}
}
