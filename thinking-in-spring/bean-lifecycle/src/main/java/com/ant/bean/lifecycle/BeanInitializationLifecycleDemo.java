package com.ant.bean.lifecycle;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * Bean 初始化生命周期示例
 * </p>
 *
 * @author GaoXin
 * @since 2020/5/20 9:13 上午
 */
public class BeanInitializationLifecycleDemo {

	public static void main(String[] args) {
		executeBeanFactory();

	}



	private static void executeBeanFactory() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		//添加 BeanPostProcessor 的实现类 MyInstantiationAwareBeanPostProcessor
		beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

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

}

