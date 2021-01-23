package com.ant.aop.features;

import com.ant.aop.features.aspect.AspectConfiguration;
import com.ant.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.ant.aop.features.pointcut.EchoServiceEchoMethodPointCut;
import com.ant.aop.features.pointcut.EchoServicePointCut;
import com.ant.aop.overview.DefaultEchoService;
import com.ant.aop.overview.EchoService;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * <p>
 * 切点
 * </p>
 *
 * @author Ant
 * @since 2021/1/11 3:59 下午
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class PointCutAPIDemo {

	public static void main(String[] args) {

		EchoServicePointCut echoServicePointCut = new EchoServicePointCut("echo", EchoService.class);

		ComposablePointcut pointCut = new ComposablePointcut(EchoServiceEchoMethodPointCut.INSTANT);

		pointCut.intersection(echoServicePointCut.getClassFilter());
		pointCut.intersection(echoServicePointCut.getMethodMatcher());

		// 将 Pointcut 适配成 Advisor
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointCut, new EchoServiceMethodInterceptor());

		DefaultEchoService defaultEchoService = new DefaultEchoService();
		// 创建 Proxy 工厂（Aspect）
		ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);

		// 添加 Advisor 实现
		proxyFactory.addAdvisor(advisor);

		// 获取代理对象
		EchoService echoService = (EchoService) proxyFactory.getProxy();
		System.out.println(echoService.echo("Hello,World"));
	}

}
