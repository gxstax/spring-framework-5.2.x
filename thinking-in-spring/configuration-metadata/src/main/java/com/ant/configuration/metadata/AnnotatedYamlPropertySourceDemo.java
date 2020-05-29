package com.ant.configuration.metadata;

import com.ant.spring.ioc.overview.domain.User;
import com.ant.spring.ioc.overview.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * <p>
 * 基于 java 注解的 YAML 外部实例化配置
 * </p>
 *
 * @author GaoXin
 * @since 2020/5/29 6:38 下午
 */
@PropertySource(name = "yamlPropertySource",
				value = "classpath:/META-INF/user.yaml",
				factory = YamlPropertySourceFactory.class
)
public class AnnotatedYamlPropertySourceDemo {


	/**
	 * user.name 是 Java Properties 默认存在的，所以这里的 name 是设置的 当前 pc 的用户名, 而并非 我们自己在 properties 中设置的 "马以"
	 */
	@Bean
	public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name, @Value("${user.city}") City city) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setCity(city);
		return user;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册当前类作为 Configuration Class
		context.register(AnnotatedYamlPropertySourceDemo.class);

		// 启动 Spring 应用上下文
		context.refresh();

		User user = context.getBean("user", User.class);
		System.out.println(user);

		// 关闭 Spring 应用上下文
		context.close();
	}
}
