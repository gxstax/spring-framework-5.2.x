# 第七章: Spring IOC 依赖来源
---
## 依赖查找的来源
### 查找来源
| 来源                    | 配置元数据                                    |
|:----------------------|:-----------------------------------------|
| Spring BeanDefinition | \<bean id="user" class="org.ant...User"> |
|                       | @Bean public User user() {...}           |
|                       | BeanDefinitionBuilder                    |
| 单例对象                  | API实现                                    |
### Spring 内建BeanDefinition
| Bean 名称                   | Bean 实例                       | 使用场景                |
|:--------------------------|:------------------------------|:--------------------|
| environment               | Environment 对象                | 外部化配置以及Profiles     |
| systemProperties          | java.util.Properties 对象       | Java 系统属性           |
| systemEnvironment         | java.util.Map 对象              | 操作系统环境变量            |
| messageSource             | messageSource 对象              | 国际化文案               |
| lifecycleProcessor        | LifecycleProcessor 对象         | Lifecycle Bean 处理器  |
| applicationEventPublisher | ApplicationEventPublisher 抽象类 | Spring 事件广播器        |
### Spring 内建单例对象
| Bean 名称            | Bean 实例                        | 使用场景            |
|:-------------------|:-------------------------------|:----------------|
| environment        | Environment 对象                 | 外部化配置以及Profiles |
| systemProperties   | java.util.Properties 对象        | Java 系统属性       |
| systemEnvironment  | java.util.Map 对象               | 操作系统环境变量       |
| messageSource      | messageSource 对象               | 国际化文案           |
| lifecycleProcessor | LifecycleProcessor 对象          | Lifecycle Bean 处理器 |
| applicationEventMultiCaster | ApplicationEventMultiCaster 对象 | Spring 事件广播器 |


## 依赖注入的来源

## Spring容器管理和游离对象

## Spring BeanDefinition 作为依赖来源

## 单例对象作为依赖来源

## 非 Spring 容器管理对象作为依赖来源

## 外部化配置作为依赖来源
