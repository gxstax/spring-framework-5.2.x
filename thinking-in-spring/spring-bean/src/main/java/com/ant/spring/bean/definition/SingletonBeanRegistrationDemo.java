package com.ant.spring.bean.definition;

import com.ant.spring.bean.factory.DefaultUserFactory;
import com.ant.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * 单体 Bean 注册
 * </p>
 *
 * @author Ant
 * @since 2020/1/29 5:26 下午
 */
public class SingletonBeanRegistrationDemo {
    public static void main(String[] args) {
        // 初始化Spring上下文环境
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 创建一个外部 UserFactory 对象
        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 注册单体bean
        beanFactory.registerSingleton("userFactory", userFactory);
        // 启动 Spring 应用上下文
        context.refresh();

        // 通过依赖查找方式来获取 beanFactory 对象
        UserFactory userFactoryLookUp = context.getBean("userFactory", UserFactory.class);
        System.out.println("userFactory == userFactoryLookUp : " + (userFactory == userFactoryLookUp));

        // 关闭 Spring 应用上下文
        context.close();
    }
}
