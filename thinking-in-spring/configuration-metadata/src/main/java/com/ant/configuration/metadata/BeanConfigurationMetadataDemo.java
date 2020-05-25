package com.ant.configuration.metadata;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * Bean 配置元信息示例
 * </p>
 *
 * @author Ant
 * @since 2020/5/24 9:56 下午
 */
@SuppressWarnings("uncheck")
public class BeanConfigurationMetadataDemo {

	public static void main(String[] args) {

		// Beandefinition 定义（声明）
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
		beanDefinitionBuilder.addPropertyValue("name", "Ant");

		// 获取 AbstractBeanDefinition
		AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

		// 附加属性（不影响 Bean 实例化、属性赋值、初始化 也就是不影响 popular、initialize）
		beanDefinition.setAttribute("name", "马以");

		// 当前 BeanDefinition 来自于何方 （辅助作用）
		beanDefinition.setSource(BeanConfigurationMetadataDemo.class);

		// 定义 BeanFactory
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 添加处理器
		beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
			@Override
			public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
				if (ObjectUtils.nullSafeEquals("user", beanName) && bean.getClass().equals(User.class)) {
					BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
					// 根据 source 来判断来源
					if (bd.getSource().equals(BeanConfigurationMetadataDemo.class)) {
						// 属性上下文 存储
						String name = (String) bd.getAttribute("name");// 这里的结果就是 "马以"

						User user = (User) bean;
						user.setName(name);
					}
				}
				return bean;
			}
		});
		// 注册 User 的 BeanDefinition
		beanFactory.registerBeanDefinition("user", beanDefinition);

		// 依赖查找
		User user = beanFactory.getBean("user", User.class);

		System.out.println(user);


	}

}
