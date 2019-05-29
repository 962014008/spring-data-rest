package org.hackerandpainter.springdatarest.dao;

/**
 * @Description
 * @Author Gao Hang Hang
 * @Date 2019-05-25 03:45
 **/

import org.hackerandpainter.springdatarest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * path="user"： 映射路由
 * User：实体类
 * Long：主键
 *
 * @author white
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends JpaRepository<User, Long> {

}

