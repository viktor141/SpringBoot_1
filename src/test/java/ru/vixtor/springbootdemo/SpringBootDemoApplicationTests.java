package ru.vixtor.springbootdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootDemoApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    private static final GenericContainer<?> prod = new GenericContainer<>("prodapp:latest").withExposedPorts(8081);
    private static final GenericContainer<?> dev = new GenericContainer<>("devapp:latest").withExposedPorts(8080);

    @BeforeAll
    public static void setUp() {
        prod.start();
        dev.start();
    }


    @Test
    void prod() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + prod.getMappedPort(8081) + "/profile", String.class);
        Assertions.assertEquals("Production", forEntity.getBody());
    }

    @Test
    void dev() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + dev.getMappedPort(8080) + "/profile", String.class);
        Assertions.assertEquals("Dev", forEntity.getBody());
    }
}
