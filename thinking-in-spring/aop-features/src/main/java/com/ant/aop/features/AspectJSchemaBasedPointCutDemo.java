package com.ant.aop.features;

import com.ant.aop.overview.proxy.EchoService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * 基于 XML 配置 Pointcut 示例
 * </p>
 *
 * @author Ant
 * @since 2021/1/1 4:10 下午
 */
public class AspectJSchemaBasedPointCutDemo {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-context.xml");


		EchoService echoService = context.getBean("echoService", EchoService.class);

		System.out.println(echoService.echo("Hello, World"));

		// 关闭 Spring 容器
		context.close();
	}

}
