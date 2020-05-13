package com.ant.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 * {@link NoUniqueBeanDefinitionException} 代码示例
 * </p>
 *
 * @author Ant
 * @since 2020/2/16 2:51 下午
 */
public class NoUniqueBeanDefinitionExceptionDemo {
    public static void main(String[] args) {
        // 初始化Spring上下文环境
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 ObjectProviderDemo Class(配置类)
        context.register(NoUniqueBeanDefinitionExceptionDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();

        try {
            // 由于Spring上下文中存在两个 String 类型的bean，所以通过单一类型查找会抛出异常
            context.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.err.printf("Spring 应用上下文中存在 %d 个 %s 类型的 Bean;\n具体原因: %s;",
                    e.getNumberOfBeansFound(),
                    e.getBeanType(),
                    e.getMessage());
        }


        // 关闭 Spring 应用上下文
        context.close();
    }

    @Bean
    private String bean1() {
        return "bean1";
    }

    @Bean
    private String bean2() {
        return "bean2";
    }
}
