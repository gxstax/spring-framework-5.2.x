package com.ant.container.lifecycle.postprocessors;

import com.ant.container.lifecycle.ContainerLifeCycleDemo;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.PriorityOrdered;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/7/29 9:47 上午
 */
public class MyPostProcessor1 implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		final ContainerLifeCycleDemo bean = beanFactory.getBean(ContainerLifeCycleDemo.class);
		System.out.println("MyPostProcessor1 : " + bean.toString());
	}

}
