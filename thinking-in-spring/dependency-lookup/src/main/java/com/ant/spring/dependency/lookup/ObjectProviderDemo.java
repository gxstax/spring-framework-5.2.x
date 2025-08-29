package com.ant.spring.dependency.lookup;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * <p>
 *  通过 {@link org.springframework.beans.factory.ObjectProvider} 进行依赖查找
 * </p>
 *
 * @author Ant
 * @since 2020/2/4 3:25 下午
 */
public class ObjectProviderDemo {
    public static void main(String[] args) {
        // 初始化Spring上下文环境
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 ObjectProviderDemo Class(配置类)
        context.register(ObjectProviderDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();

        lookupByObjectProvider(context);

//        lookupIfAvaIfAvailable(context);
//
//        lookupByStreamOps(context);


        // 关闭 Spring 应用上下文
        context.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext context) {
        ObjectProvider<String> beanProvider = context.getBeanProvider(String.class);
//        Iterable<String> iterable = beanProvider;
//        for (String s : iterable) {
//            System.out.println(s);
//        }
        beanProvider.stream().forEach(System.out::println);

    }

    private static void lookupIfAvaIfAvailable(AnnotationConfigApplicationContext context) {
        ObjectProvider<User> beanProvider = context.getBeanProvider(User.class);
        // 如果上下文中没有user对象，我们这里进行一个兜底操作，新建一个User对象
        User user = beanProvider.getIfAvailable(User::createUser);

        System.out.println("当前的 User 对象: " + user);

    }

    @Bean
    @Primary
    public String helloWorld() {
        return "Hello,World";
    }

    @Bean
    public String message() {
        return "Message";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext context) {
        ObjectProvider<String> beanProvider = context.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }
}
