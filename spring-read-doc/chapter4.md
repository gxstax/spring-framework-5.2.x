

# 第四章: Spring Bean 基础

---
## 定义 Spring Bean
* 什么是 BeanDefinition？
* BeanDefinition 是Spring Framework 中定义Bean的配置元信息接口，包含：
    * Bean 的类名
    * Bean 行为配置元素（如：作用域、自动绑定的模式、生命周期回调等）
    * 其他 Bean 引用，又可称作合作者（Collaborators）或者依赖（Dependencies）
    * 配置设置（如：Bean 属性）

## BeanDefinition 元信息
### 示例代码链接
> （thinking-in-java）-> (spring-beans) ->
> com.ant.spring.bean.definition.BeanDefinitionCreationDemo

| 属性（Property） | 说明                                         |
| :-------------- | :------------------------------------------- |
| Class            | Bean 全类名，必须是具体类，不能用抽象类或接口 |
| Name             | Bean 的名称或ID |
| Scope            | Bean 的作用域（如：singleton、prototype等） |
| Constructor arguments | Bean 构造器参数（用于依赖注入） |
| Properties | Bean 属性设置（用于依赖注入） |
| Autowiring mode | Bean 自动绑定模式（如：通过名称byName） |
| Lazy initialization mode | Bean 延迟初始化模式（延迟或非延迟） |
| Initialization method | Bean 初始化回调方法名称 |
| Destruction method | Bean 销毁回调方法名称 |


### 定义BeanDefinition 的两种方式
* BeanDefinitionBuilder
* AbstractBeanDefinition
```java
public static void main(String[] args) {

        // 1.通过 BeanDefinitionBuilder 构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 通过属性设置
        beanDefinitionBuilder
                .addPropertyValue("id", 1)
                .addPropertyValue("name", "马以");
        // 获取实例 BeanDefiniton并非bean的终态，所以我们可以自定义修改
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();


        // 2.通过 AbstractBeanDefinition 去派生
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        // 设置 Bean 类型
        genericBeanDefinition.setBeanClass(User.class);
        // 通过MutablePropertyValues 批量操作属性
        MutablePropertyValues propertyValues = new MutablePropertyValues();
//        propertyValues.addPropertyValue("id", 1);
//        propertyValues.addPropertyValue("name", "马以");
        propertyValues
                .add("id", 1)
                .add("name", "马以");
        genericBeanDefinition.setPropertyValues(propertyValues);
    }
```

## 命名 Spring Bean



## Spring Bean 的别名



## 注册 Spring Bean



## 实例化 Spring Bean



## 初始化 Spring Bean



## 延迟初始化 Spring Bean



## 销毁 Spring Bean



## 垃圾回收 Spring Bean