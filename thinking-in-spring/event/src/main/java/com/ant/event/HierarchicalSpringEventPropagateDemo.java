package com.ant.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <p>
 * 层次性 Spring 事件传播 示例
 * </p>
 *
 * @author Ant
 * @since 2020/6/27 1:28 下午
 */
public class HierarchicalSpringEventPropagateDemo {
	public static void main(String[] args) {
		// 1. 创建 parent spring 应用上下文
		AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
		parentContext.setId("parent-context");
		// 注册 Mylistener 事件到 parent spring 应用上下文
		parentContext.register(Mylistener.class);

		// 2. 创建 current spring 应用上下文
		AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
		currentContext.setId("current-context");
		// 注册 Mylistener 事件到 current spring 应用上下文
		currentContext.register(Mylistener.class);

		// 3. 关联 current -> parent
		currentContext.setParent(parentContext);

		// 4. 启动应用上下文
		parentContext.refresh();
		currentContext.refresh();

		// 5. 关闭应用上下文
		currentContext.close();
		parentContext.close();
	}

	static class Mylistener implements ApplicationListener<ApplicationContextEvent> {

		private static Set<ApplicationContextEvent> applicationContextEvents = new LinkedHashSet<>();

		@Override
		public void onApplicationEvent(ApplicationContextEvent event) {
			if (applicationContextEvents.add(event)) {
				System.out.printf("监听到了 Spirng Context[ID-: %s ] 的 %s 事件\n",
						event.getApplicationContext().getId(), event.getClass().getSimpleName());
			}
		}
	}
}
