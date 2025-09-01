# 第八章: Spring Bean 作用域
---
## Spring Bean 作用域
| 来源        | 说明                                         |
|:----------|:-------------------------------------------|
| singleton | 默认 Spring Bean 作用域，一个 BeanFactory 有且仅有一个实例 |
| prototype | 原型作用域，每次依赖查找和依赖注入生成新 Bean 对象               |
| request   | 将 Spring Bean 存储在 ServletRequest 上下文中      |
| session   | 将 Spring Bean 存储在 HttpSession 上下文中         |
| application | 将 Spring Bean 存储在 ServletContext 上下文中       |

## "singleton" Bean 作用域

## "prototype" Bean 作用域
### 注意事项
* Spring 容器没有办法管理 prototype Bean 的完整生命周期，也没有办法记录实例的存在。<br/>
销毁回调方法将不会执行,可以利用 BeanPostProcessor 进行清扫工作。
## "request" Bean 作用域

## "session" Bean 作用域

## "application" Bean 作用域

## 自定义 Bean 作用域
