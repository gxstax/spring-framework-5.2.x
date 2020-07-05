package com.ant.anntation.selfdesign;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 自定义 {@link Component} 注解
 * </p>
 *
 * @author Ant
 * @since 2020/7/4 4:24 下午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component // 元注解，实现 @Component 的 "派生性"
public @interface MyComponent {

}
