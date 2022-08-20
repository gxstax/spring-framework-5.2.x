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
    public static void main(String[] args) throws InterruptedException {
        // 初始化Spring上下文环境
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 ObjectProviderDemo Class(配置类)
        context.register(TypeSafetyDependencyLookupDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();

        // 演示 Beanfactory#getBean 方法的安全性(类型非安全)
        displayBeanFactoryGetBean(context);

        displayBeanFactoryGetBeanByName(context);

        // 演示 Beanfactory#getObject 方法的安全性(类型非安全)
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

	/**
	 * <p>
	 * 演示 {@link BeanFactory#getBean(Class)} 方法的安全性 (类型非安全)
	 * </p>
	 *
	 * @param beanFactory
	 * @return void
	 */
	private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
		printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
	}

    /**
     * <p>
     * 演示 {@link BeanFactory#getBean(String)} 方法的安全性 (类型非安全)
     * </p>
     *
     * @param context
     * @return void
     */
	private static void displayBeanFactoryGetBeanByName(AnnotationConfigApplicationContext context) {
		printBeansException("displayBeanFactoryGetBeanByName", () -> context.getBean("user"));
	}

    /**
     * <p>
     * 演示 {@link ListableBeanFactory#getBeansOfType(Class)}) 方法的安全性 (类型安全)
     * </p>
     *
     * @param beanFactory
     * @return void
     */
    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory) {
        printBeansException("displayListableBeanFactoryGetBeansOfType", () -> beanFactory.getBeansOfType(User.class));
    }

    /**
     * <p>
     * 演示 {@link ObjectProvider#getIfAvailable() } 方法的安全性(类型安全)
     * </p>
     *
     * @param context
     * @return void
     */
    private static void displayObjectProvideGetIfAvailable(AnnotationConfigApplicationContext context) {
        ObjectProvider<User> beanProvider = context.getBeanProvider(User.class);
		printBeansException("displayObjectProviderIfAvailable", () -> beanProvider.getIfAvailable());
    }

	/**
	 * <p>
	 * 演示 {@link ObjectProvider#stream()} 方法的安全性 (类型安全)
	 * </p>
	 *
	 * @param context
	 * @return void
	 */
	private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext context) {
		ObjectProvider<User> beanProvider = context.getBeanProvider(User.class);
		printBeansException("displayObjectProviderStreamOps", () -> beanProvider.stream().forEach(System.out::println));
	}

    private static void displayBeanFactoryGetObject(AnnotationConfigApplicationContext context) {
        ObjectProvider<User> beanProvider = context.getBeanProvider(User.class);
        printBeansException("displayBeanFactoryGetObject", () -> beanProvider.getObject());
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
