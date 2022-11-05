<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**目录**  

- [spring 核心编程思想](#spring-%E6%A0%B8%E5%BF%83%E7%BC%96%E7%A8%8B%E6%80%9D%E6%83%B3)
  - [第一章：Spring Framework 总览](#%E7%AC%AC%E4%B8%80%E7%AB%A0spring-framework-%E6%80%BB%E8%A7%88)
  - [第二章: 认识 IOC](#%E7%AC%AC%E4%BA%8C%E7%AB%A0-%E8%AE%A4%E8%AF%86-ioc)
  - [第三章: Spring IOC 容器概述](#%E7%AC%AC%E4%B8%89%E7%AB%A0-spring-ioc-%E5%AE%B9%E5%99%A8%E6%A6%82%E8%BF%B0)
  - [第四章: Spring Bean 基础](#%E7%AC%AC%E5%9B%9B%E7%AB%A0-spring-bean-%E5%9F%BA%E7%A1%80)
  - [第五章: Spring IOC 依赖查找](#%E7%AC%AC%E4%BA%94%E7%AB%A0-spring-ioc-%E4%BE%9D%E8%B5%96%E6%9F%A5%E6%89%BE)
  - [第六章: Spring IOC 依赖注入](#%E7%AC%AC%E5%85%AD%E7%AB%A0-spring-ioc-%E4%BE%9D%E8%B5%96%E6%B3%A8%E5%85%A5)
  - [第七章: Spring IOC 依赖来源](#%E7%AC%AC%E4%B8%83%E7%AB%A0-spring-ioc-%E4%BE%9D%E8%B5%96%E6%9D%A5%E6%BA%90)
  - [第八章: Spring Bean 作用域](#%E7%AC%AC%E5%85%AB%E7%AB%A0-spring-bean-%E4%BD%9C%E7%94%A8%E5%9F%9F)
  - [第九章: Spring Bean 生命周期](#%E7%AC%AC%E4%B9%9D%E7%AB%A0-spring-bean-%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F)
  - [第十章: Spring 配置元信息](#%E7%AC%AC%E5%8D%81%E7%AB%A0-spring-%E9%85%8D%E7%BD%AE%E5%85%83%E4%BF%A1%E6%81%AF)
  - [第十一章: Spring 资源管理](#%E7%AC%AC%E5%8D%81%E4%B8%80%E7%AB%A0-spring-%E8%B5%84%E6%BA%90%E7%AE%A1%E7%90%86)
  - [第十二章: Spring 国际化（i18n）](#%E7%AC%AC%E5%8D%81%E4%BA%8C%E7%AB%A0-spring-%E5%9B%BD%E9%99%85%E5%8C%96i18n)
  - [第十三章: Spring 校验（Validation）](#%E7%AC%AC%E5%8D%81%E4%B8%89%E7%AB%A0-spring-%E6%A0%A1%E9%AA%8Cvalidation)
  - [第十四章: Spring 数据绑定（Data Binding）](#%E7%AC%AC%E5%8D%81%E5%9B%9B%E7%AB%A0-spring-%E6%95%B0%E6%8D%AE%E7%BB%91%E5%AE%9Adata-binding)
  - [第十五章: Spring 类型转换（Type Conversion）](#%E7%AC%AC%E5%8D%81%E4%BA%94%E7%AB%A0-spring-%E7%B1%BB%E5%9E%8B%E8%BD%AC%E6%8D%A2type-conversion)
  - [第十六章: Spring 范型处理（Generic Resolution）](#%E7%AC%AC%E5%8D%81%E5%85%AD%E7%AB%A0-spring-%E8%8C%83%E5%9E%8B%E5%A4%84%E7%90%86generic-resolution)
  - [第十七章: Spring 事件（Events）](#%E7%AC%AC%E5%8D%81%E4%B8%83%E7%AB%A0-spring-%E4%BA%8B%E4%BB%B6events)
  - [第十八章: Spring 注解（Annotations）](#%E7%AC%AC%E5%8D%81%E5%85%AB%E7%AB%A0-spring-%E6%B3%A8%E8%A7%A3annotations)
  - [第十九章: Spring Environment抽象（Environment Abstract）](#%E7%AC%AC%E5%8D%81%E4%B9%9D%E7%AB%A0-spring-environment%E6%8A%BD%E8%B1%A1environment-abstract)
  - [第二十章: Spring 应用上下文生命周期（Container Lifecycle）](#%E7%AC%AC%E4%BA%8C%E5%8D%81%E7%AB%A0-spring-%E5%BA%94%E7%94%A8%E4%B8%8A%E4%B8%8B%E6%96%87%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9Fcontainer-lifecycle)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## spring 核心编程思想
(参考极客时间[小马哥讲 Spring 核心编程思想](https://time.geekbang.org/course/intro/100042601?tab=catalog))

### 第一章：Spring Framework 总览
### 第二章: 认识 IOC
### 第三章: Spring IOC 容器概述
### 第四章: Spring Bean 基础
### 第五章: Spring IOC 依赖查找
### 第六章: Spring IOC 依赖注入
### 第七章: Spring IOC 依赖来源
### 第八章: Spring Bean 作用域
### 第九章: Spring Bean 生命周期
``` 
  配套代码目录  |—thinking-in-spring
              |—————— bean-lifecycle
  ```
1.  Spring Bean 元信息配置阶段
2.  Spring Bean 元信息解析阶段
3.  Spring Bean 注册阶段
4.  Spring BeanDefinition 合并阶段
5.  Spring Bean Class 加载阶段
6.  Spring Bean 实例化前阶段
7.  Spring Bean 实例化阶段
8.  Spring Bean 实例化后阶段
9.  Spring Bean 属性赋值前阶段
10. Spring Bean Aware 接口回调阶段
11. Spring Bean 初始化前阶段
12. Spring Bean 初始化阶段
13. Spring Bean 初始化后阶段
14. Spring Bean 初始化完成阶段
15. Spring Bean 销毁前阶段
16. Spring Bean 销毁阶段
17. Spring Bean 垃圾收集

  
### 第十章: Spring 配置元信息
### 第十一章: Spring 资源管理
### 第十二章: Spring 国际化（i18n）
### 第十三章: Spring 校验（Validation）
### 第十四章: Spring 数据绑定（Data Binding）
### 第十五章: Spring 类型转换（Type Conversion）
### 第十六章: Spring 范型处理（Generic Resolution）
### 第十七章: Spring 事件（Events）
### 第十八章: Spring 注解（Annotations）
### 第十九章: Spring Environment抽象（Environment Abstract）
### 第二十章: Spring 应用上下文生命周期（Container Lifecycle）

