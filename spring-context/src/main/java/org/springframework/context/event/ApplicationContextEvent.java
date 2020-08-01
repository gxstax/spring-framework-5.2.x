/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

/**
 * Base class for events raised for an {@code ApplicationContext}.
 *
 * @author Juergen Hoeller
 * @since 2.5
 */
// Spring 标准事件，继承了 ApplicationEvent -> EventObject(java 标准事件)

/**
 * Spring 应用上下文 （ApplicationContext）作为事件源
 * 具体实现：
 * 		  1：org.springframework.context.event.ContextClosedEvent (关闭事件)
 * 		  2: org.springframework.context.event.ContextRefreshedEvent （刷新）
 * 		  3: org.springframework.context.event.ContextStartedEvent （启动）
 * 		  4: org.springframework.context.event.ContextStoppedEvent （停止）
 */

@SuppressWarnings("serial")
public abstract class ApplicationContextEvent extends ApplicationEvent {

	/**
	 * Create a new ContextStartedEvent.
	 * @param source the {@code ApplicationContext} that the event is raised for
	 * (must not be {@code null})
	 */
	public ApplicationContextEvent(ApplicationContext source) {
		super(source);
	}

	/**
	 * Get the {@code ApplicationContext} that the event was raised for.
	 */
	public final ApplicationContext getApplicationContext() {
		return (ApplicationContext) getSource();
	}

}
