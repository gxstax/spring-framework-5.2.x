package com.ant.container.lifecycle.postprocessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/7/29 9:48 上午
 */
public class MyPostProcessor2 implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		/**
		 * 注册第一个 BeanDefinition （ 本质就是往 {@link DefaultListableBeanFactory.beanDefinitionMap} 集合中添加一个元素 ）
		 **/
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClassName("fuck");
		beanDefinition.setBeanClass(String.class);
		registry.registerBeanDefinition("fuck", beanDefinition);

		/**
		 * 注册第二个 BeanDefinition （ 本质就是往 {@link DefaultListableBeanFactory.beanDefinitionMap} 集合中添加一个元素 ）
		 **/
		GenericBeanDefinition registryBeanDefinition = new GenericBeanDefinition();
		registryBeanDefinition.setBeanClassName("registry");
		registryBeanDefinition.setBeanClass(PostProcessorForRegistry.class);
		registry.registerBeanDefinition("registry", registryBeanDefinition);
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

}
