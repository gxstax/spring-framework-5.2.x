package com.ant.ioc.dependency.injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * 基于 {@link Aware} 接口回调依赖注入示例
 * </p>
 *
 * @author Ant
 * @since 2020/5/7 8:46 上午
 */
public class AwareInterfaceDependencyInjectionDemo implements BeanFactoryAware, ApplicationContextAware {

    private static BeanFactory beanFactory;

    private static ApplicationContext applicationContext;
    public static void main(String[] args) {
        // 初始化Spring上下文环境
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册Configuration Class(配置类)
        context.register(AwareInterfaceDependencyInjectionDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();

        System.out.println(beanFactory == context.getBeanFactory());
        System.out.println(context == applicationContext);

        // 显式的关闭Spring应用上下文
        context.close();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.applicationContext = applicationContext;
    }
}
