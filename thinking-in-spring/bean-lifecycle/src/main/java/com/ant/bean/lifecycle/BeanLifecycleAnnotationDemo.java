package com.ant.bean.lifecycle;


import com.ant.bean.lifecycle.bean.EmptyBean;
import com.ant.bean.lifecycle.bean.LifecycleBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.*;

/**
 * <p>
 * Spring Bean的全部生命周期展示（使用注解形式）
 * </P>
 *
 * @author Ant
 * @since 2022/11/01 4:51 下午
 **/
@Configuration(proxyBeanMethods = true)
@PropertySource("classpath:META-INF/customer.properties")
public class BeanLifecycleAnnotationDemo {

	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(BeanLifecycleAnnotationDemo.class);
		context.getBeanFactory().addBeanPostProcessor(new LifecycleBean.LifecycleBeanPostProcessor());
		context.refresh();

		// 容器关闭
		context.close();

		// 强制GC
		System.gc();

		Thread.sleep(2000L);

		System.gc();
	}

	@Bean(initMethod = "doInit", destroyMethod = "doDestroy")
	public LifecycleBean lifecycleBean() {
		LifecycleBean lifecycleBean = new LifecycleBean();
		lifecycleBean.setDescription("The LifecycleBean");
		return lifecycleBean;
	}

}
