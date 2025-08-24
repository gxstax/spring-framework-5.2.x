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
