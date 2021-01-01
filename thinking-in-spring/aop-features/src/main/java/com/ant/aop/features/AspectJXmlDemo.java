package com.ant.aop.features;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2021/1/1 4:10 下午
 */
@Aspect
@Configuration
public class AspectJXmlDemo {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-aop-context.xml");

		AspectJXmlDemo aspectJAnnotationDemo = context.getBean(AspectJXmlDemo.class);

		// 关闭 Spring 容器
		context.close();
	}

}
