package com.ant.i18n;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/6/14 5:52 下午
 */
//@EnableAutoConfiguration
public class CustomizedMessageSourceBeanDemo {


    /**
     * 在 springboot 场景中， primary Configuration Source 高于 AutoConfiguration
     */
    @Bean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
    public MessageSource messageSource() {
        return new ReloadableResourceBundleMessageSource();
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext =
                new SpringApplicationBuilder(CustomizedMessageSourceBeanDemo.class)
                        .web(WebApplicationType.NONE)
                        .run(args);

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        if (beanFactory.containsBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)) {
            // 查找 MessageSource 的 BeanDefinition
            System.out.println(applicationContext.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME));

            // 查找 MessageSource 的 Bean
            MessageSource messageSource =
                    applicationContext.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);

            System.out.println(messageSource);
        }


        // 关闭应用上下文
        applicationContext.close();
    }
}
