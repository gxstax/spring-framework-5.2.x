package com.ant.anntation.enable;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 激活 "Hello World" 模块注解
 * </p>
 *
 * @author Ant
 * @since 2020/7/4 6:34 下午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 第二步：通过 @Import 注解导入具体实现
//@Import(HelloWorldConfiguration.class) // 方法一：通过 Configure Class 实现
//@Import(HelloWorldImportSelector.class)  // 方法二：通过 ImportSelector 实现
@Import(HelloWorldImportRegistrar.class)  // 方法二：通过 ImportBeanDefinitionRegistrar 实现
public @interface EnableHelloWorld { // 第一步：通过EnableXXX 命名

}
