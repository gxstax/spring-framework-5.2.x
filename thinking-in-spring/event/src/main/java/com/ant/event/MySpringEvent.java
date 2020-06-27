package com.ant.event;

import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * 自定义事件
 * </p>
 *
 * @author Ant
 * @since 2020/6/27 8:20 下午
 */
@SuppressWarnings("serial")
public class MySpringEvent extends ApplicationEvent {

//	public MySpringEvent(String message) {
//		super(String.format("【线程 %s】：%s", Thread.currentThread().getName(), message));
//	}

	/**
	 * Create a new {@code ApplicationEvent}.
	 *
	 * @param source the object on which the event initially occurred or with
	 *               which the event is associated (never {@code null})
	 */
	public MySpringEvent(Object source) {
		super(source);
	}

	@Override
	public String getSource() {
		return (String) super.getSource();
	}

	public String getMessage() {
		return getSource();
	}
}
