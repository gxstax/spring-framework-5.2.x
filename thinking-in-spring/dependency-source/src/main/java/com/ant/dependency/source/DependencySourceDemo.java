package com.ant.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * <p>
 * 依赖来源示例
 * </p>
 *
 * @author Ant
 * @since 2020/5/16 11:58 下午
 */
public class DependencySourceDemo {

	// 注入在 postProcessProperties 方法执行，早于 setter 更早于 @PostConstruct
	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@PostConstruct
	public void initByInjection() {
		System.out.println("beanFactory == applicationContext " + (beanFactory == applicationContext));
		System.out.println("beanFactory == applicationContext.getBeanFactory " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
		System.out.println("resourceLoader == applicationContext " + (resourceLoader == applicationContext));
		System.out.println("applicationEventPublisher == applicationContext " + (applicationEventPublisher == applicationContext));
	}

	@PostConstruct
	public void initByLookUp() {
		getBean(BeanFactory.class);
		getBean(ResourceLoader.class);
		getBean(ApplicationContext.class);
		getBean(ApplicationEventPublisher.class);
	}

	public <T> T getBean(Class<T> beanType) {
		try {
			return beanFactory.getBean(beanType);
		} catch (NoSuchBeanDefinitionException e) {
			System.err.println("当前类型 " + beanType.getName() + "无法在 beanFactory 中查找到");
		}
		return null;
	}

	public static void main(String[] args) {
		// 初始化Spring上下文环境
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 注册Configuration Class(配置类) ->Spring Bean(配置类也是spirng的bean)
		context.register(DependencySourceDemo.class);

		// 启动 Spring 应用上下文
		context.refresh();

		// 依赖查找 AnnotationDependencyFieldInjectionDemo Bean
		DependencySourceDemo demo = context.getBean(DependencySourceDemo.class);


		// 显式的关闭Spring应用上下文
		context.close();
	}
}
