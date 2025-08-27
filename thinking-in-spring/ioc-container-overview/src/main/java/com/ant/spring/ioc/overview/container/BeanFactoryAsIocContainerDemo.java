package com.ant.spring.ioc.overview.container;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * <p>
 * {@link BeanFactory} 作为 IOC 容器示例
 * </p>
 *
 * @author <a href="mailto:gxstaxant@gmail.com">Ant</a>
 * @since 2025/8/27 12:22
 */

public class BeanFactoryAsIocContainerDemo {
	public static void main(String[] args) {
		// 创建 BeanFactory 容器
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

		// 加载 Bean 定义
		int beanDefinitionCounter = reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
		System.out.println("Bean 定义加载的数量：" + beanDefinitionCounter);

		lookupByCollectionType(beanFactory);
	}

	private static void lookupByCollectionType(BeanFactory beanFactory) {
		if(beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
			System.out.println("查找到的所有的 User 集合对象：" + beansOfType);
		}
	}
}
