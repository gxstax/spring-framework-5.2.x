package com.ant.configuration.metadata;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 外部化配置示例
 * </p>
 *
 * @author GaoXin
 * @since 2020/5/29 2:45 上午
 */
@PropertySource("classpath:/META-INF/user-config.properties")
public class PropertySourceDemo {

	/**
	 * user.name 是 Java Properties 默认存在的，所以这里的 name 是设置的 当前 pc 的用户名, 而并非 我们自己在 properties 中设置的 "马以"
	 */
	@Bean
	public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		return user;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 扩展 Enviroment 中的 PropertySource
		// 添加 PropertySource 操作必须在 refresh 之前完成
		Map<String, Object> propertiesSourceMap = new HashMap<>();
		propertiesSourceMap.put("user.name", "马以");
		MapPropertySource mapPropertySource = new MapPropertySource("first-property-source", propertiesSourceMap);
		context.getEnvironment().getPropertySources().addFirst(mapPropertySource);

		// 注册当前类作为 Configuration Class
		context.register(PropertySourceDemo.class);

		// 启动 Spring 应用上下文
		context.refresh();

		Map<String, User> userMap = context.getBeansOfType(User.class);

		for (Map.Entry<String, User> entry : userMap.entrySet()) {
			System.out.printf("User Bean name-> %s, value-> %s \n", entry.getKey(), entry.getValue());
		}
		System.out.println(context.getEnvironment().getPropertySources());

		// 关闭 Spring 应用上下文
		context.close();
	}
}
