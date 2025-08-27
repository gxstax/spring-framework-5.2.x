# 第三章: Spring IOC 容器概述
---
## Spring IOC 依赖查找
> 对应测试用例代码路径 <br/>
> （thinking-in-java）-> (ioc-container-overview) ->
> （com.ant.spring.ioc.overview.dependency.lookup.DependencyLookupDemo）

* 根据 Bean 名称查找
    * 实时查找
    * 延迟查找
* 根据 Bean 类型查找
    * 单个 Bean 对象
    * 集合 Bean 对象
* 根据 Bean 名称 + 类型查找
* 根据 Java 注解查找
    * 单个 Bean 对象
    * 集合 Bean 对象

## Spring IOC 依赖注入
> 对应测试用例代码路径 <br/>
> （thinking-in-java）-> (ioc-container-overview) -> 
> （com.ant.spring.ioc.overview.dependency.injection.DependencyInjectionDemo）
* 根据 Bean 名称注入
* 根据 Bean 类型注入
    * 单个 Bean 对象
    * 集合 Bean 对象
* 注入容器内建 Bean 对象
* 注入非 Bean 对象
* 注入类型
    * 实时注入
    * 延迟注入

## Spring IOC 依赖来源
> 对应测试用例代码路径 <br/>
> （thinking-in-java）-> (ioc-container-overview) ->
> （com.ant.spring.ioc.overview.dependency.injection.DependencyInjectionDemo）<br/>

* 自定义 Bean
```java
// 依赖来源一：自定义的bean
// UserRepository userPository = (UserRepository) applicationContext.getBean("userRepository");
```
> 自定义的Bean

* 容器内建 Bean 对象

```java
// 依赖来源三：容器内建 Bean
// Environment environment = applicationContext.getBean(Environment.class);
// System.out.println("获取 Environment 类型的 Bean" + environment);
```
> 像上面的 Environment 对象，其实不是我们手动创建的Bean，是spring内部创建的Bean对象
> 当我们执行这行代码，之所以会报错，是因为

* 容器内建依赖
```java
// 依赖来源二：内建的依赖
// System.out.println(userPository.getBeanFactory());
```
> BeanFactory 虽然是spring内建的对象，
> 但是我们可以自定义我们自己的Bean对象，然后依赖内建的BeanFactory对象；


## Spring IOC 配置元信息
* Bean 定义配置
    * 基于 XML 文件
    * 基于 Properties 文件
    * 基于 Java 注解
    * 基于 Java API （专题讨论）
* IOC 容器配置
    * 基于 XML 文件
    * 基于 Java 注解
    * 基于 Java API
* 外部化属性配置
    * 基于 Java 注解

## Spring IOC 容器
**ApplicationContext 和 BeanFactory 谁才是spring容器**
可以先看下这段代码，然后思考下为什么不成立
```java
// 初始化spring容器上下文环境
ApplicationContext applicationContext
        = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");// ... 省略部分代码，可以去工程里看完整代码
UserRepository userPository = (UserRepository) applicationContext.getBean("userRepository");


public static void whoIsIocContainer(UserRepository userRepository, ApplicationContext applicationContext) {

  // ConfigurableApplicationContext <- ApplicationContext <- BeanFactory
  // 这个表达式为什么不成立
  System.out.println(userRepository.getBeanFactory() == applicationContext);
}
```

step2: 接下来看下spring的这部份源码
```java
protected void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory) {
    // ...省略部分源码
  
    // 6. 注入 ResolvableDependency 对象（BeanFactory、ResourceLoader、ApplicationEventPublisher、ApplicationContext）
    beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory);
    beanFactory.registerResolvableDependency(ResourceLoader.class, this);
    beanFactory.registerResolvableDependency(ApplicationEventPublisher.class, this);
    beanFactory.registerResolvableDependency(ApplicationContext.class, this);

    // ...省略部分源码

}
```
> 从底层实现上，ApplicationContext 就是 BeanFactory 的实现，也就是说ApplicationContext就是BeanFactory；<br/>
> 而ApplicationContext的作用其实是一个应用上下文，底层依赖的依然是Spring IOC的底层容器，也就是BeanFactory的另一个抽象实现->DefaultListableBeanFactory;
> 从上面的代码中，我们也不难看出，spring Bean工厂，注册的BeanFactory.class类型的是底层beanFactory对象；<br/>
> 而注册的ApplicationContext.class类型的对象是this，也就是ApplicationContext本身，这个是属于应用上下文层面的，
> 它底层依然是依赖springIoc的底层容器，也就是BeanFactory（DefaultListableBeanFactory）；
> 这也是 userRepository.getBeanFactory() == applicationContext 为false的原因；

## Spring 应用上下文

## 使用 Spring IOC 容器

## Spring IOC 容器生命周期