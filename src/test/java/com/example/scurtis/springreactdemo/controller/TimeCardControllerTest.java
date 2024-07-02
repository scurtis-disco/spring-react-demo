package com.example.scurtis.springreactdemo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.scurtis.springreactdemo.timekeeping.TimeCard;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ActiveProfiles("test")
@Sql(value = "classpath:data/records/data.sql")
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TimeCardControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    private TimeCardController timeCardController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void contextLoads() {
        assertThat(timeCardController).isNotNull();
    }

    @Test
    void shouldGetTimecardById() {
        Map<String, Object> params = new HashMap<>();
        params.put("timecardId", UUID.fromString("54292efb-750d-41dc-a521-ce1ea1f4242e"));
        log.info("root URI is :: {}", testRestTemplate.getRootUri());
        String fullUri = String.format("%s%s", testRestTemplate.getRootUri(), "/timecards/{timecardId}");
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<TimeCard> response = testRestTemplate.exchange(fullUri,
            HttpMethod.GET,
            entity,
            TimeCard.class,
            UUID.fromString("54292efb-750d-41dc-a521-ce1ea1f4242e")
        );
//        ResponseEntity<TimeCard> response = testRestTemplate.getForEntity("/timecards/{timecardId}", TimeCard.class, params);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldCreateNewTimeCard() {

    }

}