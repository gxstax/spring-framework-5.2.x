package com.ant.bean.scope;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import javax.management.Descriptor;
import javax.security.auth.Destroyable;
import java.util.Map;

/**
 * <p>
 * Bean 的作用域示例
 * </p>
 *
 * @author Ant
 * @since 2020/5/16 11:58 下午
 */
public class BeanScopeDemo implements DisposableBean {

	@Bean
	// 默认 scope 是 singleton
	private static User singletonUser() {
		return createUser();
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	private static User prototypeUser() {
		return createUser();
	}

	private static User createUser() {
		User user = new User();
		user.setId(System.nanoTime());

		return user;
	}

	@Autowired
	@Qualifier("singletonUser")
	private User singletonUser;

	@Autowired
	@Qualifier("singletonUser")
	private User singletonUser1;

	@Autowired
	@Qualifier("prototypeUser")
	private User prototypeUser;

	@Autowired
	@Qualifier("prototypeUser")
	private User prototypeUser1;

	@Autowired
	@Qualifier("prototypeUser")
	private User prototypeUser2;

	@Autowired
	private Map<String, User> users;

	@Autowired
	private ConfigurableListableBeanFactory beanFactory; // Resolvable Dependency

	public static void main(String[] args) {
		// 初始化Spring上下文环境
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 注册Configuration Class(配置类) ->Spring Bean(配置类也是spirng的bean)
		context.register(BeanScopeDemo.class);

		context.addBeanFactoryPostProcessor(beanFactory -> {
			beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
				@Override
				public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

					System.out.printf("%s Bean 名称:%s 在初始化后回调... %n", bean.getClass().getName(), beanName);
					return bean;
				}
			});
		});

		// 启动 Spring 应用上下文
		context.refresh();

		// 依赖查找 AnnotationDependencyFieldInjectionDemo Bean
		BeanScopeDemo demo = context.getBean(BeanScopeDemo.class);

		// 结论一:
		// singleton Bean 无论依赖查找还是依赖注入，均为同一个对象
		// propotype Bean 无论依赖查找还是依赖注入，均为新生成的对象

		// 结论二:
		// 如果注入集合对象，singleton Bean 和 propotype Bean 均只会存在一个
		// 并且 propotype Bean 有别于其它地方生成的对象，也是新生成的对象

		// 结论二:
		// singleton Bean 和 propotype Bean 均会执行初始化回调
		// 但只有 singleton Bean 会执行销毁方法回调，所以原型类的生命周期是不完整的

		scopeBeansByLookUp(context);

//		scopeBeansByInjection(context);


		// 显式的关闭Spring应用上下文
		context.close();
	}

	public static void scopeBeansByLookUp(AnnotationConfigApplicationContext context) {
		for (int i = 0; i < 3; i++) {
			// singletonUser 是共享 Bean 对象
			User singletonUser = context.getBean("singletonUser", User.class);
			System.out.println("singletonUser-->" + singletonUser);

			// prototypeUser 是每次依赖查找都生成新的 Bean 对象
			User prototypeUser = context.getBean("prototypeUser", User.class);
			System.out.println("prototypeUser-->" + prototypeUser);

		}
	}

	private static void scopeBeansByInjection(AnnotationConfigApplicationContext context) {
		BeanScopeDemo demo = context.getBean(BeanScopeDemo.class);

		System.out.println("demo.singletonUser" + demo.singletonUser);
		System.out.println("demo.singletonUser1" + demo.singletonUser1);
		System.out.println("demo.prototypeUser" + demo.prototypeUser);
		System.out.println("demo.prototypeUser1" + demo.prototypeUser1);
		System.out.println("demo.prototypeUser2" + demo.prototypeUser2);
		System.out.println("demo.users" + demo.users);

	}

	@Override
	public void destroy() throws Exception {

		System.out.println("当前 BeanScopeDemo Bean 正在销毁中... ");

		this.prototypeUser.destroy();
		this.prototypeUser1.destroy();
		this.prototypeUser2.destroy();
		// 获取 Beandefinition

		for (Map.Entry<String, User> entry : this.users.entrySet()) {
			String beanName = entry.getKey();
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
			if (beanDefinition.isPrototype()) { // 如果当前 Bean 是 prototype scope
				User user = entry.getValue();
				user.destroy();
			}
		}

		System.out.println("当前 BeanScopeDemo Bean 销毁完成！！！");
	}
}
