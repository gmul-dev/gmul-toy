package com.gmul.toy.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class UserApiE2ETest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void weakPwResponse() {
        String reqBody = "{\"id\" : \"id\", \"pw\" : \"123\", \"email\" : \"a@a.com\"}";

        RequestEntity<String> request = RequestEntity.post(URI.create("/users"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(reqBody);

        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("WeakPasswordException"));
    }
}
