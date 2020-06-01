package com.ant.resources;

import com.ant.resources.util.ResourceUtil;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * <p>
 * 注入 {@link Resource} 对象示例
 * </p>
 *
 * @author GaoXin
 * @since 2020/5/31 10:39 下午
 *
 * @see Resource
 * @see Value
 * @see AnnotationConfigApplicationContext
 */
public class InjectingResourceDemo {

	@Value("classpath:/META-INF/default.properties")
	private Resource defaultPropertyResource;

	@Value("classpath*:/META-INF/*.properties")
	private  Resource[] propertiesResources;


	@Value("${user.dir}")
	private String currentProjectBashPath;

	@PostConstruct
	public void init() {
		System.out.println(ResourceUtil.getContent(defaultPropertyResource));
		System.out.println(currentProjectBashPath);
		System.out.println("-----------------------------");
		Stream.of(propertiesResources).map(ResourceUtil::getContent).forEach(System.out::println);
		System.out.println("-----------------------------");
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 注册当前类为 Configuration class
		context.register(InjectingResourceDemo.class);

		// 启动 Spring 上下文
		context.refresh();

		// 关闭 Spring 上下文
		context.close();
	}
}
