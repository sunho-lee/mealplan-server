package com.example.broccoli.user;

import com.example.broccoli.BroccoliApplication;
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
        User user1 = repository.save(new User("1234"));
        User user2 = repository.save(new User("444"));
        User user3 = repository.save(new User("1"));
        User user4 = repository.save(new User("1234"));

        assertNotNull(user1);
        assertNotNull(user2);
        assertNotNull(user3);
        assertNotNull(user4);
    }

    @Test
    void findAll() {
        List<User> users = repository.findAll();
        log.info("유저 리스트 :");
        for (User user : users){
            log.info(user.toString());
        }
        log.info("");
        assertEquals(users.size(), 4);
    }

    @Test
    void findByEmail() {
        List<User> users = repository.findByEmail("1");
        assertEquals(users.size(), 1);
        for (User user: users){
           assertEquals(user.getEmail(), "1");
        }

        users = repository.findByEmail("1234");
        assertEquals(users.size(), 2);
        for (User user : users){
            assertEquals(user.getEmail(), "1234");
        }

        users = repository.findByEmail("23232323");
        assertEquals(users.size(), 0);
    }

    @Test
    void findById() {
        List<User> users = repository.findAll();
        User existUser= users.get(0);
        Optional<User> foundUser = repository.findById(existUser.getId());
        foundUser.ifPresent(value -> assertEquals(value.getId(), existUser.getId()));
    }
}