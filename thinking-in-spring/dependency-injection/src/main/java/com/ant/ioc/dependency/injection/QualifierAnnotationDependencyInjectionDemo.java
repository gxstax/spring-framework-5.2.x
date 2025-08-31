package com.ant.ioc.dependency.injection;

import com.ant.ioc.dependency.injection.annotation.UserGroup;
import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * {@link Qualifier} 注解依赖注入
 * </p>
 *
 * @author Ant
 * @since 2020/5/9 8:48 上午
 */
public class QualifierAnnotationDependencyInjectionDemo {
    // 整体上下文中存在 4 个 User 类型的 Bean
    // superUser
    // user
    // user1 -> @Qualifier
    // user2 -> @Qualifier

    @Autowired
    private Collection<User> allUser; // 非限定类型的bean(并不会包含分组的bean)-> user, nameUser

    @Autowired
    @Qualifier
    private Collection<User> qualifierUser; // 限定类型的bean -> user1, user2, user3, user4

    @Autowired
    @UserGroup
    private Collection<User> groupUser; // 限定类型bean -> user3, user4

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user") // 指定 Bean 名称或 ID
    private User nameUser;

    @Bean
    @Qualifier
    public User user1() {
        return createUser(7L);
    }

    @Bean
    @Qualifier
    public User user2() {
        return createUser(8L);
    }

    @Bean
    @UserGroup
    public User user3() {
        return createUser(9L);
    }

    @Bean
    @UserGroup
    public User user4() {
        return createUser(10L);
    }

    private static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        // 初始化Spring上下文环境
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册Configuration Class(配置类) ->Spring Bean(配置类也是spirng的bean)
        context.register(QualifierAnnotationDependencyInjectionDemo.class);

        /** 这里偷懒使用 XML 的方式把bean扫描到上下文环境当中 **/
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        reader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        context.refresh();

        // 依赖查找 AnnotationDependencyFieldInjectionDemo Bean
        QualifierAnnotationDependencyInjectionDemo demo = context.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        // 期待输出 superUser
        System.out.println(demo.user);
        // 期待输出 user
        System.out.println(demo.nameUser);

        // 期待输出 user, superUser
        System.out.println("demo.allUser" + demo.allUser);
        // 期待输出 user1,user2,user3,user4
        System.out.println("demo.qualifierUser" + demo.qualifierUser);
        // 期待输出 user3，user4
        System.out.println("demo.groupUser" + demo.groupUser);

        // 显式的关闭Spring应用上下文
        context.close();
    }
}
