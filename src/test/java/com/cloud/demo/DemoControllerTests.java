package com.cloud.demo;


import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DemoControllerTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    void get_index_test(){

        String url = "http://localhost:" + port + "/"; // http://localhost:8080/
        System.out.println(url);
        
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(url, String.class); // pass
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        Assertions.assertNotNull(responseEntity.getBody());
    }
    @Test
    void post_rule_test(){

        String url = "http://localhost:" + port + "/rule";
        
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String,String> requesMap = new LinkedMultiValueMap<>();
        requesMap.add("rule1","aa");
        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<>(requesMap, responseHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(url,httpEntity, String.class ); 
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        Assertions.assertNotNull(responseEntity.getBody());
    }
    @Test
    void run_pig_test(){

        String url = "http://localhost:" + port + "/runPigetest/test.txt";
        
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(url, String.class); // pass
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        Assertions.assertNotNull(responseEntity.getBody());
    }
}