package org.example.backend;

import org.example.backend.beanpostprocessor.SomeTestClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BackendApplicationTests {
    @Autowired
    SomeTestClass someTestClass;
    @Test
    void contextLoads() {
        System.out.println(someTestClass.getSomeValue());
//        assertThat(someTestClass.getSomeValue()).isEqualTo(110);
    }

}
