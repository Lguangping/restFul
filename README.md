# SpringSercurity + RestFul


## RestFul的设计类包括 
### 这是一个centroller
    demosecurity/src/main/java/com/example/demosecurity/controller/SampleCrudController.java
### 这是返回信息规范化的一个封装
    demosecurity/src/main/java/com/example/demosecurity/domain/Message.java
### 这是返回值规范化的一个生成类
    demosecurity/src/main/java/com/example/demosecurity/securityUtil/MsgUtil.java
### 这是支撑centroller的一个service,由于结构简单没有设计service接口
    demosecurity/src/main/java/com/example/demosecurity/service/SampleCrudService.java
### 这是整个演示的启动类
    demosecurity/src/main/java/com/example/demosecurity/DemosecurityApplication.java
### 这是演示的一些spring配置
    demosecurity/src/main/resources/application.properties
    demosecurity/src/main/resources/application.yml
### 其他均为SpringSercurity的代码  请无视它们
## 启动
* 进入演示启动类运行main函数,端口号为8078
* 登陆成功后点击 进入restFul演示  
   
    
## 账户
### 登陆账户为springSecurity配置在内存
### 现只有两个账户:
> 帐号:admin   密码:aa123
> 帐号:任意的账户名  密码:123456