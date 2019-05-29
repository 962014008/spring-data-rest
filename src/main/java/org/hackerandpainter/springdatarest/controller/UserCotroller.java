package org.hackerandpainter.springdatarest.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description
 * @Author Gao Hang Hang
 * @Date 2019-05-25 04:33
 **/
@RestController
public class UserCotroller {

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
