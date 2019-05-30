# Spring Data REST入门（四）：自定义查询

## 1. 自定义查询方法 

通常会有这样的需求，根据给定的字段查找相应表中的数据对象。比如在前几篇博客中定义的User实体来，需要一个按照name值查到与之对应的数据对象返回，只需要在UserRopository中定义如下代码：

```java
    /**
     * 根据用户名称查找用户
     */
    @RestResource(path="name",rel="name")
    public User findByName(@Param("name") String name);
```

一行非常简单的代码的代码，满足了我们的需求。我们并没有做任何实现，只是声明了一个findByName的方法而已，方法签名已经告诉Spring Data Jpa足够的信息来创建这个方法的实现了。 

http://localhost:8080/api/user/search/name?name=white

![image-20190530215932408](/Users/gaohanghang/Library/Application Support/typora-user-images/image-20190530215932408.png)

JPA自动生成的查询sql

```sql
Hibernate: select user0_.id as id1_0_, user0_.age as age2_0_, user0_.name as name3_0_, user0_.password as password4_0_, user0_.sex as sex5_0_ from user user0_ where user0_.name=?
```

```
当创建Repository实现的时候，Spring Data会检查Repository接口的所有方法，解析方法的名称，并基于被持久化的对象来试图推测方法的目的。本质上，Spring Data定义了一组小型的领域特定语言（domain-specific language ，DSL），在这里，持久化的细节都是通过Repository方法的签名来描述的。
Spring Data能够知道这个方法是要查找User的，因为我们使用User对JpaRepository进行了参数化。方法名findByName确定该方法需要根据name属性相匹配来查找User，而name是作为参数传递到方法中来的。
findByName()方法非常简单，但是Spring Data也能处理更加有意思的方法名称。Repository方法是由一个动词、一个可选的主题（Subject）、关键词By以及一个断言所组成。在findByName()这个样例中，动词是find，断言是name，主题并没有指定，暗含的主题是User。
Spring Data允许在方法名中使用四种动词：get、read、find和count。其中，动词get、read和find是同义的，这三个动词对应的Repository方法都会查询数据并返回对象。而动词count则会返回匹配对象的数量，而不是对象本身。
在断言中，会有一个或多个限制结果的条件。每个条件必须引用一个属性，并且还可以指定一种比较操作。如果省略比较操作符的话，那么这暗指是一种相等比较操作。不过，我们也可以选择其他的比较操作，包括如下的种类：
```

```
IsAfter、After、IsGreaterThan、GreaterThan 
IsGreaterThanEqual、GreaterThanEqual 
IsBefore、Before、IsLessThan、LessThan 
IsLessThanEqual、LessThanEqual 
IsBetween、Between 
IsNull、Null 
IsNotNull、NotNull 
IsIn、In 
IsNotIn、NotIn 
IsStartingWith、StartingWith、StartsWith 
IsEndingWith、EndingWith、EndsWith 
IsContaining、Containing、Contains 
IsLike、Like 
IsNotLike、NotLike 
IsTrue、True 
IsFalse、False 
Is、Equals 
IsNot、Not 
```

要对比的属性值就是方法的参数。

## 2. 模糊查询

```java
    @RestResource(path="nameStartsWith",rel="nameStartsWith")
    public List<User> findByNameStartsWith(@Param("name") String name);
```

需求查询以name为white开始的用户，则 

http://localhost:8080/api/user/search/nameStartsWith?name=white 

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530220445.png)

## 3. 忽略大小写查询

要处理String类型的属性时，如果需要忽略大小写则可以在方法签名中加入IgnoringCase，这样在 
执行对比的时候就会不再考虑字符是大写还是小写。例如，要在name属性上忽略大小写，那么可以将方法签名改成如下的形式：

http://localhost:8080/api/user/search/nameStartsWith?name=White

```java
    @RestResource(path="nameStartsWith",rel="nameStartsWith")
    public List<User> findByNameStartsWithIgnoringCase(@Param("name") String name);
```

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530220921.png)

## 3. 多条件查询

如果需要匹配多个添加则用And和Or连接，比如：

http://localhost:8080/api/user/search/nameAndAge?name=white&age=12

```java
@RestResource(path="nameAndAge",rel="nameAndAge")
public List<User> findByNameAndAge(@Param("name")String name ,@Param("age")int age);
```

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530221334.png)

## 4. 排序

可以在方法名称的结尾处添加OrderBy，实现结果集排序。比如可以按照User的Age降序排列

**数据**

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530221744.png)

http://localhost:8080/api/user/search/nameStartsWith?name=white

```java
@RestResource(path="nameStartsWith",rel="nameStartsWith")
public List<User> findByNameStartsWithOrderByAgeDesc(@Param("name") String name);
```
![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190530221600.png)

这里只是初步体验了所能声明的方法种类，Spring Data JPA会为我们实现这些方法。现在，我们只需知道通过使用属性名和关键字构建Repository方法签名，就能让Spring Data JPA生成方法实现，完成几乎所有能够想象到的查询。不过，Spring Data这个小型的DSL依旧有其局限性，有时候通过方法名称表达预期的查询很烦琐，甚至无法实现。如果遇到这种情形的话，Spring Data能够让我们通过@Query注解来解决问题。