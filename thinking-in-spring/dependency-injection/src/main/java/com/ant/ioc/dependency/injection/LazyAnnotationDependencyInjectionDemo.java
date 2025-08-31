package com.ant.ioc.dependency.injection;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

/**
 * <p>
 * {@link ObjectProvider} 实现延时依赖注入
 * </p>
 *
 * @author Ant
 * @since 2020/5/9 8:48 上午
 */
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // 实时注入

    /**
     * 使用ObjectProvider 作为延迟依赖注入方式可以注入一些非必要依赖的对象，
     * 可以避免诸如NoSuchBeanException 或者 NoUniqueBeanDefinitionException 等的一些错误
     **/
    @Autowired
    private ObjectProvider<User> userObjectProvider; // 延迟注入

    @Autowired
    private ObjectFactory<Set<User>> objectFactory;

    public static void main(String[] args) {
        // 初始化Spring上下文环境
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册Configuration Class(配置类) ->Spring Bean(配置类也是spirng的bean)
        context.register(LazyAnnotationDependencyInjectionDemo.class);

        /** 这里偷懒使用 XML 的方式把bean扫描到上下文环境当中 **/
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        reader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        context.refresh();

        // 依赖查找 AnnotationDependencyFieldInjectionDemo Bean
        LazyAnnotationDependencyInjectionDemo demo = context.getBean(LazyAnnotationDependencyInjectionDemo.class);

        System.out.println("demo.user-->" + demo.user);

        // ObjectProvider.getObject();实际上是继承了ObjectFactory的getObject方法
        System.out.println("demo.userObjectProvider" + demo.userObjectProvider.getObject());

		demo.userObjectProvider.forEach(System.out::println);

        demo.objectFactory.getObject().forEach(System.out::println);


        // 显式的关闭Spring应用上下文
        context.close();
    }
}
