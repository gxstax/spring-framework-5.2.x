package com.ant.event;

import org.springframework.context.support.GenericApplicationContext;

/**
 * <p>
 * 自定义 Spring 事件示例
 * </p>
 *
 * @author Ant
 * @since 2020/6/27 8:00 下午
 */
public class CustomizedSpringEventDemo {
	public static void main(String[] args) {
		GenericApplicationContext context = new GenericApplicationContext();

		context.addApplicationListener(new MySpringEventListener());

		// 启动 Spring 应用上下文
		context.refresh();

		context.publishEvent(new MySpringEvent("Hello, World"));

		// 关闭 Spring 应用上下文
		context.close();
	}
}

