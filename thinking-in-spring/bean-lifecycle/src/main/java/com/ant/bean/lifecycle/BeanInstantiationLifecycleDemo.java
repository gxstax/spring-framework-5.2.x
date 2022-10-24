package com.ant.bean.lifecycle;

import com.ant.spring.ioc.overview.domain.SuperUser;
import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * Bean 实例化生命周期示例
 * </p>
 *
 * @author Ant
 * @since 2020/5/20 9:13 上午
 */
public class BeanInstantiationLifecycleDemo {

	public static void main(String[] args) {
		executeBeanFactory();

		System.out.println("----------------------------------------------------------");

		executeApplicationContext();
	}



	private static void executeBeanFactory() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 方法一: 添加 BeanPostProcessor 的实现类 MyInstantiationAwareBeanPostProcessor
//		beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

		// 方法二: 将 MyInstantiationAwareBeanPostProcessor 作为 Bean 通过XML方式注册
		// 基于 XML  资源的 BeanDefinitionReader 实现
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};

		// 定义字符编码为 UTF-8
//		Resource resource = new ClassPathResource(locations);
//		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
		int num = beanDefinitionReader.loadBeanDefinitions(locations);
		System.out.printf("已加载了 %d 条 BeanDefinition %n", num);

		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);

		User superUser = beanFactory.getBean("superUser", User.class);
		System.out.println(superUser);

		// 构造器注入是按照类型注入 resolveDependency
		UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
		System.out.println(userHolder);
	}

	private static void executeApplicationContext() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml");

		// 启动应用上下文
		applicationContext.refresh();

		User user = applicationContext.getBean("user", User.class);
		System.out.println(user);

		User superUser = applicationContext.getBean("superUser", User.class);
		System.out.println(superUser);

		// 构造器注入是按照类型注入 resolveDependency
		UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
		System.out.println(userHolder);

		// 关闭应用上下文
		applicationContext.close();
	}

}

