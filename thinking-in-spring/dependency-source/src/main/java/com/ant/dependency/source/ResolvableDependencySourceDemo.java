package com.ant.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * <p>
 * ResolvableDependency 作为依赖来源
 * </p>
 *
 * @author Ant
 * @since 2020/5/17 1:59 下午
 */
public class ResolvableDependencySourceDemo {

	@Autowired
	private String value;

	@PostConstruct
	public void init() {
		System.out.println(value);
	}

	public static void main(String[] args) {
		// 初始化Spring上下文环境
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册Configuration Class(配置类) ->Spring Bean(配置类也是spirng的bean)
		context.register(ResolvableDependencySourceDemo.class);

		context.addBeanFactoryPostProcessor(beanFactory -> {
			// 注册
			beanFactory.registerResolvableDependency(String.class, "Hello World");
		});

		// 启动 Spring 应用上下文
		context.refresh();

//		AutowireCapableBeanFactory beanFactory = context.getAutowireCapableBeanFactory();
//
//		if (beanFactory instanceof ConfigurableListableBeanFactory) {
//			ConfigurableListableBeanFactory configurableListableBeanFactory =
//					ConfigurableListableBeanFactory.class.cast(beanFactory);
//
//		}

		// 显式的关闭Spring应用上下文
		context.close();
	}
}
