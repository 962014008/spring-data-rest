package org.hackerandpainter.springdatarest.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * @Description
 * @Author Gao Hang Hang
 * @Date 2019-05-25 03:40
 **/
@Entity
@Table(name = "user")
@Data
public class User {

    /**
     * 指定id为主键，并设置为自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private String name;

    private String password;

    private int age;

    private boolean sex;

}