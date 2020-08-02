package com.ant.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.Executors.newSingleThreadExecutor;


/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/6/27 10:58 下午
 */
public class AsyncEventHandlerDemo {
	public static void main(String[] args) {
		GenericApplicationContext context = new GenericApplicationContext();

		context.addApplicationListener(new MySpringEventListener());

		// 启动 Spring 应用上下文
		context.refresh(); // 初始化 ApplicationEventMulticaster

		// 依赖查找
		ApplicationEventMulticaster applicationEventMulticaster =
				context.getBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME,
				ApplicationEventMulticaster.class);

		// 判断当前 ApplicationEventMulticaster 是否为 SimpleApplicationEventMulticaster
		if (applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
			SimpleApplicationEventMulticaster simpleApplicationEventMulticaster =
					(SimpleApplicationEventMulticaster) applicationEventMulticaster;

			// 切换 taskExecutor
			ExecutorService taskExecutor = newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-event-thread-pool"));
			// 同步 -> 异步
			simpleApplicationEventMulticaster.setTaskExecutor(taskExecutor);

			// 添加 ContextClosedEvent 事件处理
			simpleApplicationEventMulticaster.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
				@Override
				public void onApplicationEvent(ContextClosedEvent event) {
					if (!taskExecutor.isShutdown()) {
						taskExecutor.shutdown();
					}
				}
			});

			simpleApplicationEventMulticaster.setErrorHandler(e -> {
				System.err.println("Spring 事件执行异常，异常信息：" + e.getMessage());
			});

		}

		context.addApplicationListener(new ApplicationListener<MySpringEvent>() {
			@Override
			public void onApplicationEvent(MySpringEvent event) {
				throw new RuntimeException("故意抛出的异常");
			}
		});

		context.publishEvent(new MySpringEvent("Hello, World"));

		// 关闭 Spring 应用上下文
		context.close();
	}
}
