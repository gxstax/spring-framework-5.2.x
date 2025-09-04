# 第九章: Spring Bean 生命周期
---

## Spring Bean 元信息配置阶段
### BeanDefinition 配置
* 面向资源
    * XML 配置
    * Properties 资源配置
    ```properties
        user.(class) = com.ant.spring.ioc.overview.domain.User
        user.id = 1001
        user.name = 马以
        user.city = BEIJING
    ```
* 面向注解
* 面向 API

## Spring Bean 元信息解析阶段
### 面向资源 BeanDefinition 解析
* BeanDefinitionReader
* XML 解析器 - BeanDefinitionParser

### 面向注解 BeanDefinition 解析
* AnnotatedBeanDefinitionReader
> 扫描包的时候用到该类来解析注解

## Spring Bean 注册阶段
### BeanDefinition 注册接口
* BeanDefinitionRegistry
> 注册 基本上就是在 DefaultListableBeanFactory.registerBeanDefinition() 接口中完成的，
> 这里主要就是涉及两个集合对象
```java
    /** 通过BeanDefinition 注册进来进来的bean 都会存在这个 map 中 **/
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    // beanDefinitionNames 这里用list 说明是有顺序的，Spring Bean 的先进先出特性
    /** List of bean definition names, in registration order. */
    private volatile List<String> beanDefinitionNames = new ArrayList<>(256);
```
## Spring BeanDefinition 合并阶段
### BeanDefinition 合并
* 父子 BeanDefinition 合并
  * 当前 BeanFactory 查找
  * 层次性 BeanFactory 查找
  源码位置：ConfigurableBeanFactory#getMergedBeanDefinition();
```java
protected RootBeanDefinition getMergedLocalBeanDefinition(String beanName) throws BeansException {
  /**
   * 快速检索：mergedBeanDefinitions 保存的是 RootBeanDefinition，如果能从这里获取到，那么说明两种情况
   * 1: 该BeanDefinition 已经 merge 过了
   * 2: 它本身就没有父类，直接就是RootBeanDefinition，不需要 merge
   */
  // Quick check on the concurrent map first, with minimal locking.
  RootBeanDefinition mbd = this.mergedBeanDefinitions.get(beanName);
  if (mbd != null && !mbd.stale) {
    return mbd;
  }
  // 如果不满足前面两种情况，那么这里就需要进行 merge
  return getMergedBeanDefinition(beanName, getBeanDefinition(beanName));
}
```
> 所有的 Bean （包括有继承关系的）注册，最终都会解析为 RootBeanDefinition；
## Spring Bean Class 加载阶段
* ClassLoader 类加载
* Java Security 安全控制
* ConfigurableBeanFactory 临时 ClassLoader
这里我截取一段代码片段,是在这个方法
AbstractAutowireCapableBeanFactory.createBean(String, RootBeanDefinition,Object[])
```java
        // 这一步是 spring Bean 生命周期的 「Bean Class 加载阶段」阶段
		// 这里通过解析 beanDefinition 最终通过 java 的 classLoader 获取 Bean 的 Class
		Class<?> resolvedClass = resolveBeanClass(mbd, beanName);
		if (resolvedClass != null && !mbd.hasBeanClass() && mbd.getBeanClassName() != null) {
			mbdToUse = new RootBeanDefinition(mbd);
			mbdToUse.setBeanClass(resolvedClass);
		}
```

## Spring Bean 实例化前阶段
### 非主流生命周期 - Bean 实例化前阶段
* InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation
* 这部份代码也是在 AbstractAutowireCapableBeanFactory.createBean(String, RootBeanDefinition,Object[])
```java
try {
			// Give BeanPostProcessors a chance to return a proxy instead of the target bean instance.
			/**
			 * 初始化前解析，实现了
			 * {@link InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation }
			 * 方法会在这里执行，包括我们自己定义的 BeanPostProcessor
			 */
			Object bean = resolveBeforeInstantiation(beanName, mbdToUse);
			// 如果我们自己定义了指定返回的 Bean 这里就不再往下解析了，直接返回我们自己定义的 Bean
			if (bean != null) {
				return bean;
			}
		} catch (Throwable ex) {
			throw new BeanCreationException(mbdToUse.getResourceDescription(), beanName,
					"BeanPostProcessor before instantiation of bean failed", ex);
		}
```

## Spring Bean 实例化阶段
### 实例化方式
* 传统实例化方式
  * 实例化策略 - InstantiationStrategy
* 构造器依赖注入

## Spring Bean 实例化后阶段
### 执行一些回调函数，主要是下面的方法
> 源码地址：AbstractAutowireCapableBeanFactory.populateBean(String, RootBeanDefinition, BeanWrapper)
```java
    protected void populateBean(String beanName, RootBeanDefinition mbd, @Nullable BeanWrapper bw) {
      if (bw == null) {
        // 不允许 PropertyValues 实例为null值的实例注入属性
        if (mbd.hasPropertyValues()) {
          throw new BeanCreationException(
                  mbd.getResourceDescription(), beanName, "Cannot apply property values to null instance");
        } else {
          // Skip property population phase for null instance.
          return;
        }
      }
      /**
       *【Bean 生命周期】-「Bean 实例化后阶段」
       */
      // 判断是否是一个合成的 Bean 并且是否有 InstantiationAwareBeanPostProcessors 处理器
      if (!mbd.isSynthetic() && hasInstantiationAwareBeanPostProcessors()) {
        for (BeanPostProcessor bp : getBeanPostProcessors()) {
          if (bp instanceof InstantiationAwareBeanPostProcessor) {
            InstantiationAwareBeanPostProcessor ibp = (InstantiationAwareBeanPostProcessor) bp;
            // 判断 Bean 是否需要赋值，如果不需要这里执行完具体的方法后（我们方法中可以对bean 进行手动赋值操作）就直接返回
            if (!ibp.postProcessAfterInstantiation(bw.getWrappedInstance(), beanName)) {
              return;
            }
          }
        }
      }
    // ...省略部分代码
}

/**
```
> 这里主要做了一件事，就是如果我们做了 InstantiationAwareBeanPostProcessor扩展，
> 并且在postProcessAfterInstantiation（）方法中返回了false，那么这个Bean 就不会在进行后续的属性填充步骤；

## Spring Bean 属性赋值前阶段
### Bean 属性值元信息
* PropertyValues
### Bean 属性赋值前回调
* Spring 1.2-5.0：InstantiationAwareBeanPostProcessor#postProcessPropertyValues
* Spring 5.1：InstantiationAwareBeanPostProcessor#postProcessProperties
> 源码地址：AbstractAutowireCapableBeanFactory.populateBean(String, RootBeanDefinition, BeanWrapper)
```java
protected void populateBean(String beanName, RootBeanDefinition mbd, @Nullable BeanWrapper bw) {
		// ...省略部分代码

		/**
		 *【Bean 生命周期】-「Bean 属性赋值阶段」
		 */
		PropertyDescriptor[] filteredPds = null;
		if (hasInstAwareBpps) {
			if (pvs == null) {
				pvs = mbd.getPropertyValues();
			}
			for (BeanPostProcessor bp : getBeanPostProcessors()) {
				if (bp instanceof InstantiationAwareBeanPostProcessor) {
					InstantiationAwareBeanPostProcessor ibp = (InstantiationAwareBeanPostProcessor) bp;
					// 这里会进行依赖处理, 我们实现了InstantiationAwareBeanPostProcessor
					// 并重写postProcessProperties 方法 且不反回 null 时，这里就会处理我们的属性值
					PropertyValues pvsToUse = ibp.postProcessProperties(pvs, bw.getWrappedInstance(), beanName);
					if (pvsToUse == null) {
						if (filteredPds == null) {
							filteredPds = filterPropertyDescriptorsForDependencyCheck(bw, mbd.allowCaching);
						}
						pvsToUse = ibp.postProcessPropertyValues(pvs, filteredPds, bw.getWrappedInstance(), beanName);
						if (pvsToUse == null) {
							return;
						}
					}
					pvs = pvsToUse;
				}
			}
		}
		if (needsDepCheck) {
			if (filteredPds == null) {
				filteredPds = filterPropertyDescriptorsForDependencyCheck(bw, mbd.allowCaching);
			}
			checkDependencies(beanName, mbd, filteredPds, pvs);
		}

		// 属性值赋值
		if (pvs != null) {
			// 重写InstantiationAwareBeanPostProcessor#postProcessProperties 方法且不反回 null 时，
			// 这里就会应用我们的自己设定的属性值到 BeanWrapper 中去
			applyPropertyValues(beanName, mbd, bw, pvs);
		}
	}
```
> 同样的，如果我们在扩展InstantiationAwareBeanPostProcessor 中重写 postProcessProperties 方法且返回 null 时，那么属性值就不会被赋值

## Spring Bean Aware 接口回调阶段
### Spring Aware 接口 (<font color='green'>**按执行顺序枚举**</font>)
> **注意：**这一步骤只执行了前3个 Aware 接口
* <font color='green'>BeanNameAware</font>
* <font color='green'>BeanClassLoaderAware</font>
* <font color='green'>BeanFactoryAware</font>
* <font color='gray'>EnvironmentAware</font>
* <font color='gray'>EmbeddedValueResolverAware</font>
* <font color='gray'>ResourceLoaderAware</font>
* <font color='gray'>ApplicationEventPublisherAware</font>
* <font color='gray'>MessageSourceAware</font>
* <font color='gray'>ApplicationContextAware</font>
```java
protected Object initializeBean(final String beanName, final Object bean, @Nullable RootBeanDefinition mbd) {
		/**
		 *【Bean 生命周期】包含 4 个步骤：
		 * 	1. 「Bean Aware 接口回调阶段」
		 * 	2. 「Bean 初始化前阶段」
		 * 	3. 「Bean 初始化阶段」
		 * 	4. 「Bean 初始化后阶段」
		 */

		/**
		 * 【Bean 生命周期】-「Bean Aware 接口回调阶段」
		 */
		if (System.getSecurityManager() != null) {
			AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
				invokeAwareMethods(beanName, bean);
				return null;
			}, getAccessControlContext());
		} else {
			invokeAwareMethods(beanName, bean);
		}

		// ...省略部分代码
	}
```

## Spring Bean 初始化前阶段
> 初始化前步骤主要涉及的是回调操作,主要涉及3个PostProcessor
> 1. ApplicationContextAwareProcessor （处理 Aware 接口的回调）
> 2. ApplicationListenerDetector
> 3. PostProcessorRegistrationDelegate
> 4. CommonAnnotationBeanPostProcessor （处理**@PostConstruct 和 @PreDestroy、 @Resource、@Annotation**）
```java
    /**
     * BeanPostProcessor 初始化前方法回调
     */
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
            throws BeansException {
  
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            // 这里如果如果我们有实现 BeanPostProcessor#postProcessBeforeInitialization方法，
            // 那么会返回我们自己处理过的 Bean 对象
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }
```

当执行 ApplicationContextAwareProcessor 的回调时，就会回调到剩余的Aware接口
* <font color='gray'>BeanNameAware</font>
* <font color='gray'>BeanClassLoaderAware</font>
* <font color='gray'>BeanFactoryAware</font>
* <font color='green'>EnvironmentAware</font>
* <font color='green'>EmbeddedValueResolverAware</font>
* <font color='green'>ResourceLoaderAware</font>
* <font color='green'>ApplicationEventPublisherAware</font>
* <font color='green'>MessageSourceAware</font>
* <font color='green'>ApplicationContextAware</font>


## Spring Bean 初始化阶段
> 这里主要是执行两个回调：
> 1. **InitializingBean**回调
> 2. xml 配置中 **init-method="xxx"** 属性方法回调
```java
protected void invokeInitMethods(String beanName, final Object bean, @Nullable RootBeanDefinition mbd)
			throws Throwable {

		// 执行 InitializingBean 回调
		boolean isInitializingBean = (bean instanceof InitializingBean);
		if (isInitializingBean && (mbd == null || !mbd.isExternallyManagedInitMethod("afterPropertiesSet"))) {
			if (logger.isTraceEnabled()) {
				logger.trace("Invoking afterPropertiesSet() on bean with name '" + beanName + "'");
			}
			if (System.getSecurityManager() != null) {
				try {
					AccessController.doPrivileged((PrivilegedExceptionAction<Object>) () -> {
						((InitializingBean) bean).afterPropertiesSet();
						return null;
					}, getAccessControlContext());
				} catch (PrivilegedActionException pae) {
					throw pae.getException();
				}
			} else {
				((InitializingBean) bean).afterPropertiesSet();
			}
		}

		// 回调执行配置在 @Bean 注解伤的 initMethod 方法，或者配置在xml文件的 init-method="init" 属性方法
		if (mbd != null && bean.getClass() != NullBean.class) {
			String initMethodName = mbd.getInitMethodName();
			if (StringUtils.hasLength(initMethodName) &&
					!(isInitializingBean && "afterPropertiesSet".equals(initMethodName)) &&
					!mbd.isExternallyManagedInitMethod(initMethodName)) {
				invokeCustomInitMethod(beanName, bean, mbd);
			}
		}
	}
```
## Spring Bean 初始化后阶段
> InstantiationAwareBeanPostProcessor#postProcessAfterInitialization()

## Spring Bean 初始化完成阶段
### 方法回调
* Spring 4.1+：SmartInitializingSingleton#afterSingletonsInstantiated()
> 注意这里是从 Spring 4.1 版本之后开始支持
```java
/**
 * @author Juergen Hoeller
 * @since 4.1
 * @see org.springframework.beans.factory.config.ConfigurableListableBeanFactory#preInstantiateSingletons()
 */
public interface SmartInitializingSingleton {

	/**
	 * Invoked right at the end of the singleton pre-instantiation phase,
	 * with a guarantee that all regular singleton beans have been created
	 * already. {@link ListableBeanFactory#getBeansOfType} calls within
	 * this method won't trigger accidental side effects during bootstrap.
	 * <p><b>NOTE:</b> This callback won't be triggered for singleton beans
	 * lazily initialized on demand after {@link BeanFactory} bootstrap,
	 * and not for any other bean scope either. Carefully use it for beans
	 * with the intended bootstrap semantics only.
	 */
	void afterSingletonsInstantiated();

}

```

## Spring Bean 销毁前阶段

## Spring Bean 销毁阶段

## Spring Bean 垃圾收集