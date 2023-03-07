package com.example.broccoli.auth;

import com.example.broccoli.BroccoliApplication;
import com.example.broccoli.auth.repository.UserRepository;
import com.example.broccoli.auth.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(BroccoliApplication.class);

    @Autowired private UserRepository repository;

    @BeforeEach
    void setup(){
        User user1 = repository.save(new User("1234", "1234", true, true, null ));
        User user2 = repository.save(new User("444", "1234", true, true, null));
        User user3 = repository.save(new User("1", "1234", true, true, null));
        User user4 = repository.save(new User("1234", "1234", true, true, null));

        assertNotNull(user1);
        assertNotNull(user2);
        assertNotNull(user3);
        assertNotNull(user4);
    }

    @Test
    void findAll() {
        List<User> userList = repository.findAll();
        log.info("유저 리스트 :");
        for (User user : userList){
            log.info(user.toString());
        }
        log.info("");
        assertEquals(userList.size(), 4);
    }

    @Test
    void findById() {
        List<User> users = repository.findAll();
        User existUser = users.get(0);
        Optional<User> foundUser = repository.findById(existUser.getId());
        foundUser.ifPresent(value -> assertEquals(value.getId(), existUser.getId()));
    }
}