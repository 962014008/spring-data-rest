package org.hackerandpainter.springdatarest.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * @Description
 * @Author Gao Hang Hang
 * @Date 2019-05-30 21:30
 **/
@Projection(name="virtual",types=User.class)
public interface VirtualUser {

    @Value("#{target.name} #{target.age}")
    String getFullInfo();

}