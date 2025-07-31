package com.demo.utility_class;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.util.ResponseEntityUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class ResponseEntityUtilTestTest {

   @Test
    void testBuildResponse() {
        String message = "Hello, World!";
        HttpStatus status = HttpStatus.OK;

        ResponseEntity<String> response = ResponseEntityUtil.buildResponse(message, status);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello, World!", response.getBody());
    }

}
