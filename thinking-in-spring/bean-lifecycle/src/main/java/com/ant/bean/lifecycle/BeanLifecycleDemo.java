package com.ant.bean.lifecycle;

import com.ant.bean.lifecycle.processors.MyDestructionAwareBeanPostProcessor;
import com.ant.bean.lifecycle.processors.MyInstantiationAwareBeanPostProcessor;
import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/5/24 4:51 下午
 */
public class BeanLifecycleDemo {

	public static void main(String[] args) throws InterruptedException {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		//添加 BeanPostProcessor 的实现类 MyInstantiationAwareBeanPostProcessor
		beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

		// 添加 MyDestructionAwareBeanPostProcessor
		beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());

		// 添加 CommonAnnotationBeanPostProcessor 解决 @PostConstruct @PreDestroy
		beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};

		// 定义字符编码为 UTF-8
//		Resource resource = new ClassPathResource(locations);
//		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
		int num = beanDefinitionReader.loadBeanDefinitions(locations);
		System.out.printf("已加载了 %d 条 BeanDefinition %n", num);

		// 显式的执行 preInstantiateSingletons() 方法
		// SmartInitializingSingleton 通常在 Spring ApplicationContext 场景使用
		// preInstantiateSingletons 将已注册的 BeanDefinition 初始化为 Spring Bean
		beanFactory.preInstantiateSingletons();

		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);

		User superUser = beanFactory.getBean("superUser", User.class);
		System.out.println(superUser);

		// 构造器注入是按照类型注入 resolveDependency
		UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
		System.out.println(userHolder);

		// 执行 Bean 销毁
		// Bean 销毁并不意味着 Bean 被垃圾回收了
		beanFactory.destroyBean("userHolder", userHolder);
		System.out.println(userHolder);

		beanFactory.destroySingletons();

		//强制 GC
		System.gc();

		Thread.sleep(2000L);

		System.gc();
	}
}
