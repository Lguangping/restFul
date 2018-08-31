###SpringSercurity + RestFul


RestFul的设计类包括 
    // 这是一个centroller
    demosecurity/src/main/java/com/example/demosecurity/controller/SampleCrudController.java
    //这是返回信息规范化的一个封装
    demosecurity/src/main/java/com/example/demosecurity/domain/Message.java
    //这是返回值规范化的一个生成类
    demosecurity/src/main/java/com/example/demosecurity/securityUtil/MsgUtil.java
    //这是支撑centroller的一个service,由于结构简单没有设计service接口
    demosecurity/src/main/java/com/example/demosecurity/service/SampleCrudService.java
    //这是整个演示的启动类
    demosecurity/src/main/java/com/example/demosecurity/DemosecurityApplication.java
    //这是演示的一些spring配置
    demosecurity/src/main/resources/application.properties
    demosecurity/src/main/resources/application.yml
    
    //其他均为SpringSercurity的代码  请无视它们