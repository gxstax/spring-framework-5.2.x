package com.ant.spring.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * Bean 垃圾回收 （GC）示例
 * </p>
 *
 * @author Ant
 * @since 2020/1/29 5:14 下午
 */
public class BeanGarbageCollectionDemo {
    public static void main(String[] args) {
        // 初始化Spring上下文环境
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册Configuration Class(配置类)
        context.register(BeanInitializationDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();

        // 关闭 Spring 应用上下文
        context.close();
        System.gc();
    }
}
