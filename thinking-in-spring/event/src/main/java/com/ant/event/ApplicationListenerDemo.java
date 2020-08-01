package com.ant.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * <p>
 * {@link ApplicationListener} 示例
 * </p>
 *
 * @author Ant
 * @since 2020/6/26 11:59 下午
 * @see ApplicationListener
 * @see EventListener
 */
@SuppressWarnings("serial")
@EnableAsync
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {
	public static void main(String[] args) {
//		GenericApplicationContext context = new GenericApplicationContext();

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 将引导类 ApplicationListenerDemo 作为 Configuration Class
		context.register(ApplicationListenerDemo.class);

		// 方法1：基于Spring 接口，向 Spring 容器中注册事件
		// 1-a: 基于 ConfigurableApplicationContext API 实现
		context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
			@Override
			public void onApplicationEvent(ApplicationEvent event) {
				println("ApplicationListener-> 接收到 Spring 事件 = " + event);
			}
		});

		// 1-b：基于 ApplicationListener 注册为 Spring Bean
		// 通过 Configuration Class 来注册
		context.register(MyApplicationListener.class);


		// 方法2：基于spring 注解 @EventListener

		// 启动 Spring 应用上下文
		context.refresh(); // 会发布 ContextRefreshedEvent 内建事件

		// 启动 Spring 应用上下文
		context.start(); // 会发布 ContextStartedEvent 内建事件

		// 停止 Spring 应用上下文
		context.stop(); // 会发布 ContextStoppedEvent 内建事件

		// 关闭 Spring 应用上下文
		context.close(); // 会发布 ContextClosedEvent 内建事件
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		applicationEventPublisher.publishEvent(new ApplicationEvent("Hello, World") {
		});

		// 发布  PayloadApplicationEvent 事件
		applicationEventPublisher.publishEvent("Hello, World");

		applicationEventPublisher.publishEvent(new MyPayloadApplicationEvent(this, "Hello World"));
	}

	static class MyPayloadApplicationEvent<String> extends PayloadApplicationEvent<String> {

		/**
		 * Create a new PayloadApplicationEvent.
		 *
		 * @param source  the object on which the event initially occurred (never {@code null})
		 * @param payload the payload object (never {@code null})
		 */
		public MyPayloadApplicationEvent(Object source, String payload) {
			super(source, payload);
		}
	}

	static class MyApplicationListener implements ApplicationListener<ContextClosedEvent> {
		@Override
		public void onApplicationEvent(ContextClosedEvent event) {
			println("MyApplicationListener-> 接收到 Spring 事件 = " + event);
		}
	}

	@EventListener
	@Order(2)
	public void onApplicationEvent(ContextRefreshedEvent event) {
		println("@EventListener#onApplicationEvent -> 接收到 Spring 事件 = ContextRefreshedEvent ");
	}

	@EventListener
	@Order(1)
	public void onApplicationEvent1(ContextRefreshedEvent event) {
		println("@EventListener#onApplicationEvent1 -> 接收到 Spring 事件 = ContextRefreshedEvent ");
	}

	@EventListener
	public void onApplicationEvent(ContextClosedEvent event) {
		println("@EventListener#onApplicationEvent1 -> 接收到 Spring 事件 = ContextClosedEvent");
	}

	@EventListener
	@Async
	public void onApplicationEventAsync(ContextRefreshedEvent event) {
		println("@EventListener (异步) -> 接收到 Spring 事件 = ContextRefreshedEvent ");
	}

	public static void println(Object printable) {
		System.out.printf("[线程 %s ] - %s\n", Thread.currentThread().getName(), printable);
	}

}
