package com.ant.bean.lifecycle;

import com.ant.spring.ioc.overview.domain.SuperUser;
import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * Bean 实例化生命周期示例
 * </p>
 *
 * @author GaoXin
 * @since 2020/5/20 9:13 上午
 */
public class BeanInstantiationLifecycleDemo {
	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 添加 BeanPostProcessor 实现 (实例)
		beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

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

	static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
		@Override
		public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
				// 把配置完成的 superUser 替换掉
				return new SuperUser();
			}
			// 保持原来的 spring IOC 初始化的 Bean
			return null;
		}

		@Override
		public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
				// 手动设置属性之
				User user = (User) bean;
				user.setId(2L);
				user.setName("Ant");
				// 不允许属性赋值 (配置元信息 -> 属性值)
				return false;
			}
			return true;
		}
	}
}
