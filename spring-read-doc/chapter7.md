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
### 注入来源
| 来源                    | 配置元数据                                    |
|:----------------------|:-----------------------------------------|
| Spring BeanDefinition | \<bean id="user" class="org.ant...User"> |
|                       | @Bean public User user() {...}           |
|                       | BeanDefinitionBuilder                    |
| 单例对象                  | API实现                                    |
| 非 Srping 容器管理对象      | API实现             ||

对于非Spring 容器管理的对象，这里可以看下依赖注入的源码：
```java
protected Map<String, Object> findAutowireCandidates(
			@Nullable String beanName, Class<?> requiredType, DependencyDescriptor descriptor) {

    // 从 beanFactory 获取容器中的bean
    String[] candidateNames = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(
            this, requiredType, true, descriptor.isEager());
    Map<String, Object> result = new LinkedHashMap<>(candidateNames.length);
    // 这里会判断是否有依赖Spring容器的内建对象，如果有就放入到result中
    // resolvableDependencies 主要包含四个在 prepareBeanFactory 中注册进去的对象
    // 1. BeanFactory 2. ResourceLoader 3. ApplicationContext 4. ApplicationEventPublisher
    // 其中后面3个对应的都是 ApplicationContext 对象本身
    for (Map.Entry<Class<?>, Object> classObjectEntry : this.resolvableDependencies.entrySet()) {
        Class<?> autowiringType = classObjectEntry.getKey();
        if (autowiringType.isAssignableFrom(requiredType)) {
            Object autowiringValue = classObjectEntry.getValue();
            autowiringValue = AutowireUtils.resolveAutowiringValue(autowiringValue, requiredType);
            if (requiredType.isInstance(autowiringValue)) {
                result.put(ObjectUtils.identityToString(autowiringValue), autowiringValue);
                break;
            }
        }
    }

    // ....
}
```
> 
> resolvableDependencies 主要包含四个在 prepareBeanFactory 中注册进去的对象
> 1. BeanFactory 
> 2. ResourceLoader 
> 3. ApplicationContext 
> 4. ApplicationEventPublisher
> 
> 其中后面3个对应的都是 ApplicationContext 对象本身

## Spring容器管理和游离对象
### 依赖对象
示例代码连接
> thinking-in-spring -> dependency-source -></br>
> com.ant.dependency.source.DependencySourceDemo

| 来源                    | Spring Bean 对象 | 生命周期管理                     | 配置元信息                      | 使用场景      |   
|:----------------------|:---------------|:---------------------------|:---------------------------|:----------|
| Spring BeanDefinition | 是              | 是                          | 有                          | 依赖查找、依赖注入 |
| 单体对象                  | 是              | <font color='red'>否</font> | <font color='red'>无</font>                           | 依赖查找、依赖注入 |
| Resolvable Dependency | <font color='red'>否</font>              | <font color='red'>否</font>                          | <font color='red'>无</font> | 依赖注入      |
> Resolvable Dependency 这部份是游离对象，这部分对象是不能被**依赖查找**的，只能**依赖注入**；

## Spring BeanDefinition 作为依赖来源
 
## 单例对象作为依赖来源

## 非 Spring 容器管理对象作为依赖来源

## 外部化配置作为依赖来源
