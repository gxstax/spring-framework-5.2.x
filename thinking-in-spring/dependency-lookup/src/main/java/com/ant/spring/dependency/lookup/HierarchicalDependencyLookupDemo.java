package com.ant.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * 层次性依赖查找示例
 * </p>
 *
 * @author Ant
 * @since 2020/2/12 4:09 下午
 */
public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        // 初始化Spring上下文环境
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 ObjectProviderDemo Class(配置类)
        context.register(HierarchicalDependencyLookupDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();

        // 1.获取HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前 BeanFactory 的 parent BeanFactory：" + beanFactory.getParentBeanFactory());

        displayLocalBean(beanFactory, "user");
        displayLocalBean(parentBeanFactory, "user");

        displayContainBean(beanFactory, "user");
        displayContainBean(parentBeanFactory, "user");

        // 关闭 Spring 应用上下文
        context.close();
    }

    private static void displayContainBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前BeanFactory [%s] 是否包含 Bean [name:%s]: %s \n", beanFactory, beanName,
                containsBean(beanFactory, beanName));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory bean = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(bean, beanName)){
                return true;
            }
        }

        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前BeanFactory [%s] 是否包含 Local Bean [name:%s]: %s \n", beanFactory, beanName,
                beanFactory.containsLocalBean(beanName));
    }

    private static ConfigurableListableBeanFactory createParentBeanFactory() {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 加载配置
        String locationXmlPath = "META-INF/dependency-lookup-context.xml";
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(locationXmlPath);

        return beanFactory;
    }

}
