package com.ant.bean.lifecycle;


import com.ant.bean.lifecycle.bean.ConfigurationBean1;
import com.ant.bean.lifecycle.bean.ConfigurationBean2;
import com.ant.bean.lifecycle.configuration.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * {@link Configuration#proxyBeanMethods()} 属性原理
 * </P>
 *
 * @author Ant
 * @since 2022/11/04 8:48 下午
 **/
public class ConfigurationProxyBeanMethodsDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppConfig.class);
		context.refresh();

		System.out.println("---------------------------------------------------");

		ConfigurationBean1 bean1 = context.getBean(ConfigurationBean1.class);
		bean1.syHello();

		System.out.println("---------------------------------------------------");

		ConfigurationBean2 bean2 = context.getBean(ConfigurationBean2.class);
		bean2.sayHello();
	}
}
