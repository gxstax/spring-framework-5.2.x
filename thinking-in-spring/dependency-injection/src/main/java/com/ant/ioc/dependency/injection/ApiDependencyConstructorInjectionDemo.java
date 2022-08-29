package com.ant.ioc.dependency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * 基于 API 实现依赖 Constructor 方法注入示例
 * </p>
 *
 * @author Ant
 * @since 2020/5/7 8:46 上午
 */
public class ApiDependencyConstructorInjectionDemo {
    public static void main(String[] args) {
        // 初始化Spring上下文环境
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册 UserHolder 的 BeanDefinition
        BeanDefinition definition =  createUserHolderBeanDefinition();
        // 注册 UserHolder 的 BeanDefinition
        context.registerBeanDefinition("userHolder", definition);

        /** 这里偷懒使用 XML 的方式把bean扫描到上下文环境当中 **/
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        reader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        context.refresh();

        UserHolder bean = context.getBean(UserHolder.class);
        System.out.println(bean);

        // 显式的关闭Spring应用上下文
        context.close();
    }

    /**
     * <p>
     * 为 {@link UserHolder} 生成 {@link BeanDefinition}
     * </p>
     *
     * @param
     * @return
     */
    private static BeanDefinition createUserHolderBeanDefinition() {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        builder.addConstructorArgReference("superUser");
        return builder.getBeanDefinition();
    }

}
