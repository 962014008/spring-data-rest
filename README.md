# 使用Spring Data REST构建微服务 (两行代码搞定RESTFul)

> 原文地址 [Spring Data REST入门](https://blog.csdn.net/soul_code/article/details/54108105)

## 快速开始

```
1. clone 本项目
2. 导入idea
3. 运行 SpringDataRestApplication.java
```

## 教程

文章目录下

## 运行效果

- POST 新增User 

![](https://img-blog.csdn.net/20170106103810431?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc291bF9jb2Rl/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

同样方式新增几条测试数据 

- GET查询 

  - 列表查询

  ![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530205642.png)

  - 主键查询 

  ![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530203732.png)

  - 分页查询 

  ![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530205036.png)

  - 分页+排序查询 

  ![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530211040.png)

- PUT、POST更新数据 

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530211252.png)

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530211309.png)

或者

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530211421.png)

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530211513.png)

- DELETE删除数据 

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530211613.png)

测试完毕 
最后再看一下项目结构

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530211735.png)

## 注意点

spring data rest 默认是不暴露id的，需要加个配置类

```java
@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(User.class);
    }
}
```

## 参考

> https://www.imooc.com/article/15706

>[spring data rest and swagger](https://itnext.io/building-microservices-with-spring-data-rest-40bb94080a9e)

> https://github.com/LINKIT-Group/spring-data-rest

