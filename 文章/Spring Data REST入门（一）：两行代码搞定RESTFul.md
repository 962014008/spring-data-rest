[Spring Data REST入门（一）](http://blog.csdn.net/soul_code/article/details/54108105) 
[Spring Data REST入门（二）](http://blog.csdn.net/soul_code/article/details/54135703) 
[Spring Data REST入门（三）](http://blog.csdn.net/soul_code/article/details/54139709) 
[Spring Data REST入门（四）](http://blog.csdn.net/soul_code/article/details/54237594)

## 1. 什么是Spring Data REST 

Spring Data REST是基于Spring Data的repository之上，可以把 repository 自动输出为REST资源，目前支持Spring Data JPA、Spring Data MongoDB、Spring Data Neo4j、Spring Data GemFire、Spring Data Cassandra的 repository 自动转换成REST服务。注意是自动。简单点说，Spring Data REST把我们需要编写的大量REST模版接口做了自动化实现。举个例子，比如你写了如下代码

```java
@RepositoryRestResource(path="user")
public interface UserRepository extends JpaRepository<User, Long>{  
}
```

自定了一个接口UserRepository 继承了JpaRepository，其中泛型中的User是实体类，Long是主键类型，在类的头部加上了一个 @RepositoryRestResource注解，并添加了一个Path为user。两行代码即可实现User实体类的RESTFul风格的所有接口，比如发送GET请求到127.0.0.1/api/user，返回JSON格式的数据集合(注：”api”为统一前缀)，并且每个Item都提供了相应的Detail URI 

![](https://img-blog.csdn.net/20170105231937378?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc291bF9jb2Rl/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

简单分页查询127.0.0.1:8080/api/user?page=2&size=2 

![](https://img-blog.csdn.net/20170105232258129?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc291bF9jb2Rl/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

这里简单的传入了页码也页数，Spring Data REST为我们自动做了分页功能，是不是很炫？还没完，注意下半部分红框圈住的内容，这里Spring Data REST 还为我们返回了上一页，下一页，以及最后一页的URI。然而到目前为止你只写了两行代码，而且Spring Data REST的功能还不止如此，这里只是简单展现下Spring Data REST的魅力而已。 

## 2. Spring Data REST本身是一个Spring MVC的应用

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>white.yu</groupId>
    <artifactId>spring-data-rest-demo</artifactId>
    <packaging>war</packaging>
    <version>0.0.1-SNAPSHOT</version>
    <name>spring-data-rest-demo Maven Webapp</name>
    <url>http://maven.apache.org</url>
    <dependencies>
        <dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-rest-webmvc</artifactId>
            <version>2.5.6.RELEASE</version>
        </dependency>
    </dependencies>
    <build>
        <finalName>spring-data-rest-demo</finalName>
    </build>
</project>
```

![](https://img-blog.csdn.net/20170105222743104?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc291bF9jb2Rl/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

可以看到在Maven项目中，我们加入了Spring Data REST，从引入的Jar可以看到其依赖于Spring和Spring MVC，还可以猜测出Spring Data REST提供的REST服务默认返回的JSON格式，并且默认是用的jackSon解析。 

## 3. Spring MVC配置Spring Data REST 

Spring Data REST的配置定义在RepositoryRestMvcConfiguration类中，其中定义了Spring Data REST的默认配置，在Spring MVC中可以采用继承或者使用@import导入的方式导入Spring Data REST的默认配置，如果需要自定义配置，则需要实现RepositoryRestConfigurer接口 或者继承 RepositoryRestConfigurerAdapter然后重写你自己所需要的方法即可 

## 4. Spring Boot整合Spring Data REST 

如果你还没用过请看这篇博客[Spring Boot环境搭建](http://www.imooc.com/article/15259)，在Spring Boot项目中，我只需要引入spring-boot-starter-data-rest的依赖，无需任何配置即可使用

```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.5.RELEASE</version>
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-rest</artifactId>
        </dependency>
    </dependencies>
```

下一节将开始正式编码，从环境搭建到实现一个企业级RESTFul服务。

