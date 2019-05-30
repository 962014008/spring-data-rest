package org.hackerandpainter.springdatarest.entity;

import org.springframework.data.rest.core.config.Projection;

/**
 * @Description
 * @Author Gao Hang Hang
 * @Date 2019-05-30 21:27
 **/
@Projection(name="list",types=User.class)
public interface ListUser {
    String getName();
    long getId();
}
