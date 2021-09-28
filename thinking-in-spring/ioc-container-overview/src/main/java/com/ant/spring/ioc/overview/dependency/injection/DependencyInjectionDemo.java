package com.ant.spring.ioc.overview.dependency.injection;

import com.ant.spring.ioc.overview.dependency.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * <p>
 * 依赖查找示例
 * </p>
 *
 * @author Ant gxstax@163.com
 * @since 2020-01-03 09:21
 */
public class DependencyInjectionDemo {

	@SuppressWarnings(value = "rawtypes")
    public static void main(String[] args) {
        // 初始化spring容器上下文环境
        BeanFactory beanFactory
                = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");
        ApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");

        // 依赖来源一：自定义的bean
        UserRepository userPository = (UserRepository) applicationContext.getBean("userRepository");
//		System.out.println(userPository.getUsers());

//		System.out.println(userPository.getBeanFactory() == beanFactory);

        // 依赖来源二：内建的依赖
        System.out.println(userPository.getBeanFactory());

        // 依赖查找（错误）
//        System.out.println(context.getBean(BeanFactory.class));

        // 依赖来源三：容器内建 Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println("获取 Environment 类型的 Bean" + environment);

		ObjectFactory objectFactory = userPository.getObjectFactory();

		System.out.println(objectFactory.getObject());
		System.out.println(objectFactory.getObject() == beanFactory);


		whoIsIocContainer(userPository, applicationContext);

    }

    public static void whoIsIocContainer(UserRepository userRepository, ApplicationContext applicationContext) {

        // ConfigurableApplicationContexgt <- ApplicationContext <- BeanFactory
        // 这个表达式为什么不成立
        System.out.println(userRepository.getBeanFactory() == applicationContext);
    }

}
