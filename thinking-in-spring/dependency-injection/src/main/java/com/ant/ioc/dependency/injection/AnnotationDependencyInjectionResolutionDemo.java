package com.ant.ioc.dependency.injection;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Lazy;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 注解驱动的依赖注入过程
 * </p>
 *
 * @author Ant
 * @since 2020/5/7 8:46 上午
 */
public class AnnotationDependencyInjectionResolutionDemo {

	@Autowired
	@Lazy
	private User lazyUser;

    @Autowired         // 依赖查找
    private User user; // DependencyDescriptor ->
                       // 必须的 (required=true)
                       // 实时注入 (eager = true)
                       // 通过类型 (User.class)
                       // 字段名称 ('user')
                       // 是否首要 (primary = true)


	@Autowired  // 集合类型依赖注入
	private Map<String, User> users; // —>user, superUser

	@Autowired
	private Optional<User> userOptional;

	@Inject
	private User injectUser;

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

		System.out.println("demo.lazyUser-->" + demo.lazyUser);

        // 期待输出 superUser
        System.out.println("demo.user-->" + demo.user);

        // 期待输出 superUser
        System.out.println("demo.injectUser-->" + demo.injectUser);

        // 期待输出 user superUser
        System.out.println("demo.users-->" + demo.users);

        // 期待输出 userOptional
        System.out.println("demo.userOptional-->" + demo.userOptional);

        // 显式的关闭Spring应用上下文
        context.close();
    }
}
