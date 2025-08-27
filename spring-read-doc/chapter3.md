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

## Spring 应用上下文

## 使用 Spring IOC 容器

## Spring IOC 容器生命周期