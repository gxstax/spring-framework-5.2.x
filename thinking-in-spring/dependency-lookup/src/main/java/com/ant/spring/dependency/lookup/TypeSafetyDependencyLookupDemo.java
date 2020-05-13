package com.ant.spring.dependency.lookup;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * 类型安全 依赖查找示例
 * </p>
 *
 * @author GaoXin
 * @since 2020/2/16 12:27 下午
 */
public class TypeSafetyDependencyLookupDemo {
    public static void main(String[] args) {
        // 初始化Spring上下文环境
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 ObjectProviderDemo Class(配置类)
        context.register(TypeSafetyDependencyLookupDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();

//        // 演示 Beanfactory#getBean 方法的安全性(类型非安全)
//        displayBeanFactoryGetBean(context);
//
//        // 演示 Beanfactory#getObject 方法的安全性(类型非安全)
//        displayBeanFactoryGetObject(context);

        // 演示 ObjectProvider#getIfAvailable 方法的安全性(类型安全)
        displayObjectProvideGetIfAvailable(context);

        // 演示 ListableBeanFactory#GetBeansOfType 方法的安全性(类型安全)
        displayListableBeanFactoryGetBeansOfType(context);

        // 演示 ObjectProvider#stream 方法的安全性(类型安全)
        displayObjectProviderStreamOps(context);

        // 关闭 Spring 应用上下文
        context.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext context) {
        ObjectProvider<User> beanProvider = context.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps", () -> beanProvider.stream().forEach(System.out::println));
    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory) {

        printBeansException("displayListableBeanFactoryGetBeansOfType", () -> beanFactory.getBeansOfType(User.class));
    }

    private static void displayObjectProvideGetIfAvailable(AnnotationConfigApplicationContext context) {
        ObjectProvider<User> beanProvider = context.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable", () -> beanProvider.getIfAvailable());
    }

    private static void displayBeanFactoryGetObject(AnnotationConfigApplicationContext context) {
        ObjectProvider<User> beanProvider = context.getBeanProvider(User.class);
        printBeansException("displayBeanFactoryGetObject", () -> beanProvider.getObject());
    }

    private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
//        try {
//            User bean = beanFactory.getBean(User.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    private static void printBeansException(String source, Runnable runnable) {
        System.err.println("-------------------------------------------------------------");
        System.err.println("Source From :" + source);
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
