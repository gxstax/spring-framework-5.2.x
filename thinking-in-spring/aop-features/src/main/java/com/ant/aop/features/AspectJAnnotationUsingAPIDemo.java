package com.ant.aop.features;

import com.ant.aop.features.aspect.AspectConfiguration;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2021/1/1 4:10 下午
 */
@SuppressWarnings({"unchecked", "rawtypes", "serial"})
public class AspectJAnnotationUsingAPIDemo {

	public static void main(String[] args) {
		// 缓存
		Map<String, Object> cache = new HashMap<>();

		// 创建 Proxy 工厂（Aspect）
		AspectJProxyFactory proxyFactory = new AspectJProxyFactory();

		// 增加 Aspect 配置类
		proxyFactory.addAspect(AspectConfiguration.class);

		proxyFactory.addAdvice(new MethodBeforeAdvice() {
			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				if ("put".equals(method.getName()) && args.length == 2) {
					System.out.printf("当前存放的 key: %s, Value: %s \n", args[0], args[1]);
				}
			}
		});

		// 获取代理对象
		Map<String, Object> proxy = proxyFactory.getProxy();
		// 通过代理对象存储数据
		proxy.put("1", "A");

		System.out.println(cache.get("1"));

	}

}
