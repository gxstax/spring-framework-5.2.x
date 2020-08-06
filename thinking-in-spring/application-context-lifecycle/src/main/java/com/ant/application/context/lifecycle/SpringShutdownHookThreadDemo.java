package com.ant.application.context.lifecycle;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

/**
 * <p>
 * Spring shutdownHook 示例
 * </p>
 *
 * @author Ant
 * @since 2020/8/7 12:27 上午
 */
public class SpringShutdownHookThreadDemo {
	public static void main(String[] args) throws IOException {
		GenericApplicationContext context = new GenericApplicationContext();

		context.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
			@Override
			public void onApplicationEvent(ContextClosedEvent event) {
				System.out.printf("[线程 %s] ContextEvent 处理\n", Thread.currentThread().getName());
			}
		});


		context.refresh();


		// 注册 ShutdownHook
		context.registerShutdownHook();

		System.out.println("按任意键关闭 Spring 应用上下文");

		System.in.read();

		context.close();
	}
}
