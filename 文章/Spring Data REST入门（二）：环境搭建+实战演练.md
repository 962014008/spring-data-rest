## 1. 环境搭建

1. 创建Spring Boot项目，使用Gradle进行依赖管理，项目中添加相应依赖

```
dependencies {
    compile 'org.springframework.boot:spring-boot-starter-data-rest:2.1.5.RELEASE'
    compile 'org.springframework.boot:spring-boot-starter-web:2.1.5.RELEASE'
    compile 'org.springframework.boot:spring-boot-starter-data-jpa:2.1.5.RELEASE'
    compile 'mysql:mysql-connector-java:6.0.6'
    compile 'com.spring4all:swagger-spring-boot-starter:1.9.0.RELEASE'
    compile 'org.springframework.data:spring-data-rest-webmvc:3.1.8.RELEASE'
    testCompile 'org.springframework.boot:spring-boot-starter-test:2.1.5.RELEASE'
    compileOnly 'org.projectlombok:lombok:1.18.8'
}
```

2. 创建实体类

```java
@Entity
public class User {

    /**
     * 指定id为主键，并设置为自增长
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;


    private String name;

    private String password;

    private int age;

    private boolean sex;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

}
```

3. 创建UserRepository

```java
/**
 * path="user"： 映射路由
 * User：实体类
 * Long：主键
 * @author white
 *
 */
@RepositoryRestResource(path="user")
public interface UserRepository extends JpaRepository<User, Long>{

}
```

4. 创建启动类和核心配置文件 
   启动类

```java
@SpringBootApplication
public class SpringDataRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataRestApplication.class, args);
    }

}
```

在Resource目录下创建application.yml

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/test
    username: root
    password: root
```

## 2. 实战测试

1. 启动项目

SpringDataRestApplication类右键Run as即可 

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530202837.png)

看到这里Logo且项目为报错则启动成功

2. RESTFul接口测试

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

这里只是简单的编写了三个类，除去配置类和实体类，其实我们就写了两行代码，就实现了RESTFul风格的接口，这就是Spring Data REST的魅力所在。当然实际生成环境不会那么简单，还需要一些额外的处理，比如在返回的数据中，password这类字段是不应该返回的，DELETE 操作，我们实际需求不是硬删除，SAVE操作之前需要做相应的数据校验和数据格式的转换等等，这类定制化的操作，Spring Data REST也提供了相应的支持。在接下来的篇幅为会大家详细介绍。



