# 第二章: 认识 IOC
---
## 定义
> IOC(inversion of control) 控制反转
## 面向对象编程语言中几种**实现策略**
* Using a service locator pattern （服务定位模式）
    * JNDI EJB
* Using dependency injection （依赖注入）
    * Constructor injection 构造器注入
    * Parameter injection 参数注入
    * Setter injection setter方法注入
    * Interface injection 接口注入
* Using a contextualized lookup（上下文依赖查找）
* Using template method design pattern（模版方法模式）
    * JDBC
* Using strategy design pattern （策略器模式）
## IOC 容器职责
* 依赖处理
    * 依赖查找
    * 依赖注入
* 生命周期管理
    * 容器
    * 托管的资源（java beans 或其他资源）
* 配置
    * 容器
    * 外部化配置
    * 托管的资源（Java Beans 或其他资源）
## IOC 容器的实现
* Java SE
    * Java Beans
    * Java ServiceLoader SPI
    * JNDI （Java Naming and Directory Interface）
* Java EE
    * EJB （Enterprise Java Beans）
    * Servlet
* 开源框架
    * Apache Avalon
    * PicoContainer
    * Google Guice
    * Spring Framework
## 传统IOC容器-Java Beans实现
* 特性
    * 依赖查找
    * 生命周期管理
    * 配置元信息
    * 事件
    * 自定义
    * 资源管理
    * 持久化
* 规范
    * [Java Beans](https://www.oracle.com/technetwork/java/javase/tech/index-jsp-138795.html)
    * [BeanContext](https://docs.oracle.com/javase/8/docs/technotes/guides/beans/spec/beancontext.html)




-------
## 一些面试题
1. 沙雕面试题：什么是IOC
  > 答：简单的说，IOC是控制反转，类似于好莱坞原则，主要有依赖查找和依赖注入的方式实现。
2. 依赖查找喝依赖注入的区别
  > 依赖查找是主动或手动的依赖查找方式，通常需要借助容易活标准API来实现。
  > 而依赖注入则是手动或自动依赖绑定的方式，无需依赖特定的容器和API。
3. Spring 作为IOC容器有什么优势
 * 典型的IOC管理，依赖查找和依赖注入
 * AOP抽象
 * 事务抽象
 * 事件抽象
 * SPI扩展
 * 强大的第三方整合
 * 易测试性
 * 更好的面向对象