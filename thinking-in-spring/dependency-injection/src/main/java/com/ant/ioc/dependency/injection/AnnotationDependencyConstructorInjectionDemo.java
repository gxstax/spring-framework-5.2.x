package com.ant.ioc.dependency.injection;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 * 基于 java注解依赖 Constructor 方法注入示例
 * </p>
 *
 * @author Ant
 * @since 2020/5/7 8:46 上午
 */
public class AnnotationDependencyConstructorInjectionDemo {
    public static void main(String[] args) {
        // 初始化Spring上下文环境
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册Configuration Class(配置类)
        context.register(AnnotationDependencyConstructorInjectionDemo.class);

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


    @Bean
    public UserHolder UserHolder(User user) {
        /**
         * 这里使用java注解来注入，如果上下文中有user对象，这里就会注入进来
         */
        return new UserHolder(user);
    }
}
