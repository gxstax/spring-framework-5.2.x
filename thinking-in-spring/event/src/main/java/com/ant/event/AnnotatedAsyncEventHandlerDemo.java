package com.ant.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newSingleThreadExecutor;


/**
 * <p>
 * 注解驱动异步事件处理示例
 * </p>
 *
 * @author Ant
 * @since 2020/6/27 10:58 下午
 */
@EnableAsync
public class AnnotatedAsyncEventHandlerDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.register(AnnotatedAsyncEventHandlerDemo.class);

		// 启动 Spring 应用上下文
		context.refresh(); // 初始化 ApplicationEventMulticaster


		context.publishEvent(new MySpringEvent("Hello, World"));

		// 关闭 Spring 应用上下文
		context.close();
	}

	@Async // 同步 -> 异步
	@EventListener
	public void onEvent(MySpringEvent event) {
		System.out.printf("【线程 %s】onEvent 方法监听到事件：%s \n", Thread.currentThread().getName(), event);
	}

	@Bean
	public Executor taskExecutor() {
		ExecutorService taskExecutor = newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-event-thread-pool-a"));
		return taskExecutor;
	}
}
