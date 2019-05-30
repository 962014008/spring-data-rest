package org.hackerandpainter.springdatarest;

import org.hackerandpainter.springdatarest.dao.UserRepository;
import org.hackerandpainter.springdatarest.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataRestApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void contextLoads() {
        List<User> all = userRepository.findAll();
        System.out.println(all);
    }

}
