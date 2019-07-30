package org.hackerandpainter.springdatarest.dao;

/**
 * @Description
 * @Author Gao Hang Hang
 * @Date 2019-05-25 03:45
 **/

import org.hackerandpainter.springdatarest.entity.ListUser;
import org.hackerandpainter.springdatarest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * path="user"： 映射路由
 * User：实体类
 * Long：主键
 *
 * @author white
 */
@RepositoryRestResource(path="user",excerptProjection= User.class)
//@RepositoryRestResource(path="user",excerptProjection= ListUser.class)
public interface UserRepository extends JpaRepository<User, Long> {

    @RestResource(exported = false)
    @Override
    void deleteById(Long aLong);

    /**
     * 根据用户名称查找用户
     */
    @RestResource(path="name",rel="name")
    public User findByName(@Param("name") String name);

    //@RestResource(path="nameStartsWith",rel="nameStartsWith")
    //public List<User> findByNameStartsWith(@Param("name") String name);

    //@RestResource(path="nameStartsWith",rel="nameStartsWith")
    //public List<User> findByNameStartsWithIgnoringCase(@Param("name") String name);

    @RestResource(path="nameAndAge",rel="nameAndAge")
    public List<User> findByNameAndAge(@Param("name")String name ,@Param("age")int age);

    @RestResource(path="nameStartsWith",rel="nameStartsWith")
    public List<User> findByNameStartsWithOrderByAgeDesc(@Param("name") String name);
}

