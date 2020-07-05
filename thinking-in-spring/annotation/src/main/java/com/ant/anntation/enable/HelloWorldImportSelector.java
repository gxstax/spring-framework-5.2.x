package com.ant.anntation.enable;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * <p>
 * HelloWorld 模块 {@link ImportSelector} 实现
 * </p>
 *
 * @author Ant
 * @since 2020/7/4 6:48 下午
 *
 * @see ImportSelector
 */
public class HelloWorldImportSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{"com.ant.anntation.enable.HelloWorldConfiguration"};
	}
}
