package com.ant.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * <p>
 * 注入 {@link ApplicationEventPublisher} 示例
 * </p>
 *
 * @author Ant
 * @since 2020/6/27 4:34 下午
 */
@SuppressWarnings({"serial", "rawtypes"})
public class InjectingApplicationEventPublisherDemo implements ApplicationEventPublisherAware,
		ApplicationContextAware, BeanPostProcessor{

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void init() {
		// #3
		applicationEventPublisher.publishEvent(new MySpringEvent("The event from @Autowired ApplicationEventPublisher"));
		// #4
		applicationContext.publishEvent(new MySpringEvent("The event from @Autowired ApplicationContext"));
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册 Configurantion class
		context.register(InjectingApplicationEventPublisherDemo.class);

		// 注册
		context.register(MySpringEventListener.class);

		// 启动 Spring 应用上下文
		context.refresh();

		// 关闭 Spring 应用上下文
		context.close();
	}

	// #1
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		applicationEventPublisher.publishEvent(new MySpringEvent("The event from ApplicationEventPublisherAware"));
	}

	// #2
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		applicationContext.publishEvent(new MySpringEvent("The event from ApplicationContextAware"));
	}
}
