# 第五章: Spring IOC 依赖查找
---

## 依赖查找的前世今生
### 示例代码链接
> （thinking-in-java）-> (dependency-lookup)
### Java 中的依赖查找
* 单一类型依赖查找
  * JNDI -javax.naming.Context#lookup(java.naming.Name)
  * JavaBeans - java.beans.beancontext.BeanContext
* 集合类型依赖查找
    * java.beans.beancontext.BeanContext
* 层次性依赖查找
    * java.beans.beancontext.BeanContext
## 单一类型依赖查找
* 根据 Bean 名称查找
    * getBean(String)
    * Spring 2.5 覆盖默认参数：getBean(String, Object...)
* 根据 Bean 类型查找
    * Bean 实时查找
      * Spring 3.0 getBean(Class)
      * Spring 4.1 覆盖默认参数：getBean(Class,Object)
    * Spring5.1 Bean 延迟查找
      * getBeanProvider(Class)
      * getBeanProvider(ResolvableType)
* 根据 Bean 名称 + 类型查找

## 集合类型依赖查找
### 集合类型依赖查找接口-ListableBeanFactory
* 根据同类型 Bean 名称列表
    * 获取同类型 Bean 名称列表
        * getBeanNamesForType(Class)
        * Spring 4.2 getBeanNamesForType(ResolvableType)
    * 获取同类型 Bean 实例列表
        * getBeansOfType(Class) 以及重载方法
* 通过注解类型查找
    * Spring 3.0 获取标注类型 Bean 名称列表
        * getBeanNamesForAnnotation(Class<? extends Annotation>)
    * Spring 3.0 获取标注类型 Bean 实例列表
        * getBeansWithAnnotation(Class<? extends Annotation>)
    * Spring 3.0 获取指定名称 + 标注类型 Bean 实例
        * findAnnotationOfBean(String, Class<? extends Annotation>)
## 层次性依赖查找
### 层次性依赖查找接口-HierarchicalBeanFactory
* 双亲 BeanFactory：getParentBeanFactory()
* 层次性查找
    * 根据 Bean 名称查找
        * 基于 containsLocalBean 方法实现
    * 根据 Bean 类型查找实例列表
        * 单一类型: BeanFactoryUtils#beanOfType
        * 集合类型: BeanFactoryUtils#beansOfTypeIncludingAncestors
    * 根据 Java 注解查找名称列表
        * BeanFactoryUtils#beanNamesForTypeIncludingAncestors

## 延迟依赖查找
### Bean 延迟依赖查找接口
* org.springframework.beans.factory.ObjectFactory
* org.springframework.beans.factory.ObjectProvider
    * Spring 5 对 Java8 特性扩展
        * 函数式接口
            * getIfAvailable(Supplier)
            * ifAvailable(Consumer)
        * Stream 扩展 - stream()

## 安全依赖查找
| 依赖查找类型 | 代表实现                                  | 是否安全                         |
|:-------|:--------------------------------------|:-----------------------------|
| 单一类型查找 | BeanFactory#getBean                   | <font color='red'>否</font>   |
|        | ObjectFactory#getObject               | <font color='red'>否</font>   |
|        | ObjectProvider#getIfAvailable         | <font color='green'>否</font> |
| 集合类型查找 | ListableBeanFactory#getBeansOfType    | <font color='green'>否</font>                            |
|        | ObjectProvider#stream                 | <font color='green'>否</font>                            |

## 内建可查找的依赖
### AbstractApplicationContext
| Bean 名称                   | Bean 实例                       | 使用场景                |
|:--------------------------|:------------------------------|:--------------------|
| environment               | Environment 对象                | 外部化配置以及Profiles     |
| systemProperties          | java.util.Properties 对象       | Java 系统属性           |
| systemEnvironment         | java.util.Map 对象              | 操作系统环境变量            |
| messageSource             | messageSource 对象              | 国际化文案               |
| lifecycleProcessor        | LifecycleProcessor 对象         | Lifecycle Bean 处理器  |
| applicationEventPublisher | ApplicationEventPublisher 抽象类 | Spring 事件广播器        |

### 注解驱动 Spring 应用上下文内建可查找的依赖（部分）
| Bean 名称                                                                                       | Bean 实例                                 | 使用场景                                       |
|:----------------------------------------------------------------------------------------------|:----------------------------------------|:-------------------------------------------|
| org.springframework.context.<br/>annotation.<br/>**internalConfigurationAnnotationProcessor** | ConfigurationClassPostProcessor 对象      | **处理Spring 配置类**                           |
| org.springframework.context.<br/>annotation.<br/>**internalAutowiredAnnotationProcessor**     | AutowiredAnnotationBeanPostProcessor 对象 | **处理 @Autowired 以及 @Value 注解**             |
| org.springframework.context.<br/>annotation.<br/>**internalCommonAnnotationProcessor**        | CommonAnnotationBeanPostProcessor 对象    | **（条件激活）处理 JSR-250 注解，如 @PostConstruct 等** |
| org.springframework.context.<br/>event.<br/>**internalEventListenerProcessor**                     | EventListenerMethodProcessor 对象    | **处理 @EventListener 的 Spring事件监听方法**       |

## 依赖查找中的经典异常