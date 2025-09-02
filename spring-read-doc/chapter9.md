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

## Spring Bean Class 加载阶段

## Spring Bean 实例化前阶段

## Spring Bean 实例化阶段

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