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

## Spring Bean 属性赋值前阶段

## Spring Bean Aware 接口回调阶段

## Spring Bean 初始化前阶段

## Spring Bean 初始化阶段

## Spring Bean 初始化后阶段

## Spring Bean 初始化完成阶段

## Spring Bean 销毁前阶段

## Spring Bean 销毁阶段

## Spring Bean 垃圾收集