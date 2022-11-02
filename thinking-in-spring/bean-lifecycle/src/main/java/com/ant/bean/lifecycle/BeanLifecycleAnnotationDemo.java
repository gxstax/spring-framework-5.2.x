package com.ant.bean.lifecycle;


import com.ant.bean.lifecycle.bean.LifecycleBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 * Spring Bean的全部生命周期展示（使用注解形式）
 * </P>
 *
 * @author Ant
 * @since 2022/11/01 4:51 下午
 **/
public class BeanLifecycleAnnotationDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(BeanLifecycleAnnotationDemo.class);
		context.getBeanFactory().addBeanPostProcessor(new LifecycleBean.LifecycleInstantiationAwareBeanPostProcessor());
		context.refresh();

		LifecycleBean bean = context.getBean(LifecycleBean.class);

		System.out.println(bean);

	}

	@Bean(initMethod = "init")
	public LifecycleBean lifecycleBean() {
		LifecycleBean lifecycleBean = new LifecycleBean();
		lifecycleBean.setDescription("The LifecycleBean");
		return lifecycleBean;
	}

}
