package com.example.obrestdatajpa.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {
        
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTesRestTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTesRestTemplateBuilder = restTesRestTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTesRestTemplateBuilder);
    }

    @Test
    void testCreate() {

    }

    @Test
    void testHello() {
        ResponseEntity<String> response =
            testRestTemplate.getForEntity("/hola", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hola Mundo, que talco?", response.getBody());


    }
    @Test
    void testFindAll() {
        //testRestTemplate.getForEntity(null, null);

    }

    @Test
    void testFindOneById() {

    }
}
