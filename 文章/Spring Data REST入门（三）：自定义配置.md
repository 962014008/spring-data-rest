# Spring Data REST入门（三）：自定义配置

## 1. 基础配置 
Spring Data REST的基础配置定义在RepositoryRestConfiguration（org.springframework.data.rest.core.config.RepositoryRestConfiguration）类中。

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530212127.png)

可以通过继承 

```java
@Component 
public class CustomizedRestMvcConfiguration extends RepositoryRestConfigurerAdapter {

  @Override 
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) { 
  configuration.setBasePath(“/api”) 
  } 
} 
```

或者

```java
@Configuration
class CustomRestMvcConfiguration {

  @Bean
  public RepositoryRestConfigurer repositoryRestConfigurer() {

    return new RepositoryRestConfigurerAdapter() {

      @Override
      public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        configuration.setBasePath("/api")
      }
    };
  }
}
```

这两种方式来配置相应的信息。还可以在application.yml中直接进行配置。

```yaml
spring:
  data:
    rest:
      base-path: /api
```

这里只配置了basePath，其他配置同理 

## 2. 自定义输出字段

1、隐藏某个字段

```java
public class User {

    /**
     * 指定id为主键，并设置为自增长
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    private String name;
    @JsonIgnore
    private String password;
    private int age;
    private boolean sex;
}
```

比如在实体对象User中，我们不希望password 序列化未JSON，在上篇博客中说到，Spring Data REST默认使用的是JackSon，则我们就可以使用在需要隐藏的字段添加@JsonIgnore即可

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530212837.png) 

2、@Projections

```java
@Projection(name="list",types=User.class)
public interface ListUser {
    String getName();
    long getId();
}
```

也可以通过@Projection注解实现1中的效果， 

请求URL为：127.0.0.1:8080/api/user?projection=list 

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530213020.png)

@Projection还可以用来建立虚拟列

```java
@Projection(name="virtual",types=User.class)
public interface VirtualUser {

    @Value("#{target.name} #{target.age}") 
    String getFullInfo();

}
```

这里把User中的name和age合并成一列，这里需要注意String getFullInfo();方法名前面一定要加get，不然无法序列化为JSON数据 

url：[http://127.0.0.1:8080/user?projection=virtual](http://127.0.0.1:8080/user?projection=virtual) 

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530213628.png)

@Projection定义的数据格式还可以直接配置到Repository之上，就像下面代码中的这样

```java
@RepositoryRestResource(path="user",excerptProjection=ListUser.class)
public interface UserRepository extends JpaRepository<User, Long>{

}
```

配置之后返回的JSON数据会按照ListUser定义的数据格式进行输出 

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530214007.png)

## 3. 屏蔽自动化方法 
在实际生产环境中，不会轻易的删除用户数据，此时我们不希望DELETE的提交方式生效，可以添加@RestResource注解，并设置exported=false，即可屏蔽Spring Data REST的自动化方法 

比如我们不想轻易的暴露按主键删除的方法，只需要写如下代码

```java
@RepositoryRestResource(path="user",excerptProjection= ListUser.class)
public interface UserRepository extends JpaRepository<User, Long> {
    
    @RestResource(exported = false)
    @Override
    void deleteById(Long id);
}
```

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530214809.png)



