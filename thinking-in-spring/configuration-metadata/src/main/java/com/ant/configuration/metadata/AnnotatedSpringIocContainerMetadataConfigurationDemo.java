package com.ant.configuration.metadata;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.util.Map;

/**
 * <p>
 * 基于 java 注解 Spring IOC 容器元信息配置示例
 * </p>
 *
 * @author Ant
 * @since 2020/5/28 8:44 上午
 */
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource("classpath:/META-INF/user-config.properties")
@PropertySource("classpath:/META-INF/user-config.properties")
//@PropertySources(@PropertySource(..))
public class AnnotatedSpringIocContainerMetadataConfigurationDemo {

	/**
	 * user.name 是 Java Properties 默认存在的，所以这里的 name 是设置的 当前 pc 的用户名, 而并非 我们自己在 properties 中设置的 "马以"
	 */
	@Bean
	public User configurationUser(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		return user;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册当前类作为 Configuration Class
		context.register(AnnotatedSpringIocContainerMetadataConfigurationDemo.class);

		// 启动 Spring 应用上下文
		context.refresh();

		Map<String, User> userMap = context.getBeansOfType(User.class);

		for (Map.Entry<String, User> entry : userMap.entrySet()) {
			System.out.printf("User Bean name-> %s, value-> %s \n", entry.getKey(), entry.getValue());
		}

		// 关闭 Spring 应用上下文
		context.close();
	}
}
