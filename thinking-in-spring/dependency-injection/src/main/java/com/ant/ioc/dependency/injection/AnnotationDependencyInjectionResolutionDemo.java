package com.ant.ioc.dependency.injection;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * 注解驱动的依赖注入过程
 * </p>
 *
 * @author Ant
 * @since 2020/5/7 8:46 上午
 */
public class AnnotationDependencyInjectionResolutionDemo {

    @Autowired         // 依赖查找
    private User user; // DependencyDescriptor ->
                       // 必须的 (required=true)
                       // 实时注入 (eager = true)
                       // 通过类型 (User.class)
                       // 字段名称 ('user')
                       // 是否首要 (primary = true)


    // 实时注入 + 通过类型(User.class)类型查找依赖查找（处理）+ 字段名称 user


    public static void main(String[] args) {
        // 初始化Spring上下文环境
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册Configuration Class(配置类) ->Spring Bean(配置类也是spirng的bean)
        context.register(AnnotationDependencyInjectionResolutionDemo.class);

        /** 这里偷懒使用 XML 的方式把bean扫描到上下文环境当中 **/
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        reader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        context.refresh();

        // 依赖查找 AnnotationDependencyFieldInjectionDemo Bean
        AnnotationDependencyInjectionResolutionDemo demo = context.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        System.out.println("demo.user-->" + demo.user);

        // 显式的关闭Spring应用上下文
        context.close();
    }
}
