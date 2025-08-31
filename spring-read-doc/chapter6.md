# 第六章: Spring IOC 依赖注入
---

## 依赖注入的模式和类型
### 手动模式-配置或者编程的方式，提前安排注入规则
* XML 资源配置元信息
* Java 注解配置元信息
* Java API 配置元信息
### 自动模式-实现方提供依赖自动关联的方式，按照内建的注入规则
* Autowiring （自动绑定）

### 依赖注入的类型
| 依赖注入类型          | 配置元数据举例                                                                 |
|:----------------|:------------------------------------------------------------------------|
| Setter 方法       | <font color='gray'><\property name=' ' ref=' '/></font>                 |
| Constructor 构造器 | <font color='gray'><constructor-arg name=' ' ref=' '/></font>           |
| Field 字段        | <font color='gray'>@Autowired User user</font>                          |
| Method 方法       | <font color='gray'>@Autowired public void user(User user){...}</font>   |
| Callback 接口回调   | <font color='gray'>Class MyBean implements BeanFactoryAware{...}</font> |

## 自动绑定（Autowiring）

## 自动绑定（Autowiring）模式
| 模式          | 说明                                    |
|:------------|:--------------------------------------|
| No          | 默认值，未激活 Autowiring，需要手动指定依赖注入对象       |
| byName      | 根据被注入属性的名称作为 Bean 名称进行依赖查找，并将对象设置到该属性 |
| byType      | 根据注入属性的类型作为 Bean 类型进行依赖查找，并设置该对象      |
| constructor | 特殊 byType 类型，用于构造器参数                  |

## 自动绑定 （Autowiring）限制和不足
> autowiring 是一种猜测性的自动绑定，Spring 容器会尝试自动绑定依赖关系，但是无法保证成功。

## Setter 方法依赖注入
### 实现方法
* 手动模式
    * XML 资源配置元数据
    * Java 注解配置元数据
    * Java API 配置元数据
* 自动模式
    * byName
    * byType

## 构造器依赖注入
* Xml 方式构造器注入
```xml
    <bean class="com.ant.ioc.dependency.injection.UserHolder">
            <constructor-arg name="user" ref="superUser"></constructor-arg>
    </bean>
```
* Java 注解方式构造器注入
```java
    @Bean
    public UserHolder UserHolder(User user) {
        /**
         * 这里使用java注解来注入，如果上下文中有user对象，这里就会注入进来
         */
        return new UserHolder(user);
    }
```
* Java API 构造器注入
```java
    private static BeanDefinition createUserHolderBeanDefinition() {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        builder.addConstructorArgReference("superUser");
        return builder.getBeanDefinition();
    }
```

## 字段注入
> 字段注入，@Autowired 会忽略掉静态字段
```java
    @Autowired
    private
//    static // Autowired 会忽略掉静态字段
            UserHolder userHolder;

    @Resource
    private UserHolder userHolder2;
```

## 方法注入

```java
    @Autowired
    public void initUserHolder(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    @Resource
    public void initUserHolder2(UserHolder userHolder2) {
        this.userHolder2 = userHolder2;
    }

    @Bean
    public UserHolder UserHolder(User user) {
        /**
         * 这里使用java注解来注入，如果上下文中有user对象，这里就会注入进来
         */
        return new UserHolder(user);
    }
```

## 回调注入
| 内建接口                           | 说明                                             |
|:-------------------------------|:-----------------------------------------------|
| BeanFactoryAware               | 获取 IoC 容器 - BeanFactory                        |
| ApplicationContextAware        | 获取 Spring 应用上下文 - ApplicationContext 对象        |
| EnvironmentAware               | 获取 Environment 对象                              |
| ResourceLoaderAware            | 获取资源加载器对象 -  ResourceLoader                    |
| BeanClassLoaderAware           | 获取加载当前 Bean Class 的加载器对象 - ClassLoader         |
| BeanNameAware                  | 获取当前 Bean 的名称                                  |
| MessageSourceAware             | 获取 MessageSource 对象，用于 Spring国际化               |
| ApplicationEventPublisherAware | 获取 ApplicationEventPublishAware 对象，用于 Spring事件 |
| EmbeddedValueResolverAware     | 获取 StringValueResource 对象，用于占位符处理              |

## 依赖注入类型选择
### 基础类型
* 原生类型（Primitive）：boolean、byte、char、short、int、long、float、double
* 标量类型（Scalar）：Number、Character、Boolean、Enum、Locale、Charset、Currency、Properties、UUID
* 常规类型（General）：Object、String、TimeZone、Calendar、Optional
* Spring类型：Resource、InputSource、Formatter...