package com.ant.container.lifecycle;

import com.ant.container.lifecycle.postprocessors.MyPostProcessor1;
import com.ant.container.lifecycle.postprocessors.MyPostProcessor2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * Spring 容器生命周期示例
 * </p>
 *
 * @author Ant
 * @since 2020/7/29 9:42 上午
 */
public class ContainerLifeCycleDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();


		context.register(ContainerLifeCycleDemo.class);


		// 自定义 BeanPostProcessors
		context.addBeanFactoryPostProcessor(new MyPostProcessor1());

		context.addBeanFactoryPostProcessor(new MyPostProcessor2());

		// 启动 Spring 应用上下文
		context.refresh();

		// 关闭 Spring 应用上下文
		context.close();
	}
}
