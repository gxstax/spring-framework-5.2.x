package com.ant.event;

import org.springframework.context.ApplicationListener;

/**
 * <p>
 * 自定义 SpringEventListener 事件监听器
 * </p>
 *
 * @author Ant
 * @since 2020/6/27 8:20 下午
 */
public class MySpringEventListener implements ApplicationListener<MySpringEvent> {

	@Override
	public void onApplicationEvent(MySpringEvent event) {
		System.out.printf("【线程 %s】监听到事件：%s \n", Thread.currentThread().getName(), event);
	}
}
