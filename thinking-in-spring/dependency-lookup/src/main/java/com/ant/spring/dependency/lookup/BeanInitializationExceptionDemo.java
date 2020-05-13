package com.ant.spring.dependency.lookup;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * {@link BeanInitializationException } 代码示例
 * </p>
 *
 * @author Ant
 * @since 2020/2/16 3:09 下午
 */
public class BeanInitializationExceptionDemo {
    public static void main(String[] args) {
        // 初始化Spring上下文环境
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册 一个接口类型的Bean
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
        context.registerBeanDefinition("errorBean", builder.getBeanDefinition());

        // 启动 Spring 应用上下文
        context.refresh();

        // 关闭 Spring 应用上下文
        context.close();
    }
}
