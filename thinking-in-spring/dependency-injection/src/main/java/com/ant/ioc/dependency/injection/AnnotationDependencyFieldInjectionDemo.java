package com.ant.ioc.dependency.injection;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * <p>
 * 基于 java注解依赖'字段'方法注入示例
 * </p>
 *
 * @author Ant
 * @since 2020/5/7 8:46 上午
 */
public class AnnotationDependencyFieldInjectionDemo {

    @Autowired
    private
//    static // Autowired 会忽略掉静态字段
            UserHolder userHolder;

    @Resource
    private UserHolder userHolder2;

    public static void main(String[] args) {
        // 初始化Spring上下文环境
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册Configuration Class(配置类) ->Spring Bean(配置类也是spirng的bean)
        context.register(AnnotationDependencyFieldInjectionDemo.class);

        /** 这里偷懒使用 XML 的方式把bean扫描到上下文环境当中 **/
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        reader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        context.refresh();

        // 依赖查找 AnnotationDependencyFieldInjectionDemo Bean
        AnnotationDependencyFieldInjectionDemo demo = context.getBean(AnnotationDependencyFieldInjectionDemo.class);

        UserHolder userHolder = demo.userHolder;
        System.out.println(userHolder);
        System.out.println(demo.userHolder2);

        System.out.println(userHolder == demo.userHolder2);


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
