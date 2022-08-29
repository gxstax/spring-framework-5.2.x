package com.ant.spring.dependency.lookup;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * <p>
 * {@link BeanCreationException} 示例
 * </p>
 *
 * @author Ant
 * @since 2020/2/16 3:14 下午
 */
public class BeanCreationExceptionDemo {
    public static void main(String[] args) {
        // 初始化Spring上下文环境
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
        context.registerBeanDefinition("errorCreateBean", builder.getBeanDefinition());

        // 启动 Spring 应用上下文
        context.refresh();

        // 关闭 Spring 应用上下文
        context.close();
    }

    static class POJO implements InitializingBean {

        @PostConstruct //CommonAnnotationBeanPostProcessor 来进行处理
        public void init() throws Throwable {
            throw new Throwable("init(): For purposes...");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet(): For Purposes...");
        }
    }
}
