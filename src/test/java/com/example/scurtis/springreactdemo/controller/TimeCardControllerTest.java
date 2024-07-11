package com.example.scurtis.springreactdemo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.scurtis.springreactdemo.timekeeping.TimeCard;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ActiveProfiles("test")
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TimeCardControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    private TimeCardController timeCardController;

    @Value("classpath:data/timecard/new-tc.json")
    Resource resourceFile;

    @Value("classpath:data/timecard/new-tc-no-employee.json")
    Resource nonExistentEmployeeFile;

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
    void shouldCreateNewTimeCard() throws IOException {
        String fullUri = String.format("%s%s", testRestTemplate.getRootUri(), "/timecards");
        String jsonResource = resourceFile.getContentAsString(Charset.defaultCharset());
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(jsonResource, headers);

        ResponseEntity<TimeCard> response = testRestTemplate.postForEntity(
                fullUri,
                entity,
                TimeCard.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI tcLocation = response.getHeaders().getLocation();
        log.debug("location is :: {}", tcLocation.getPath());
        // retrieve the newly created timecard
        TimeCard saved = testRestTemplate.getForEntity(tcLocation, TimeCard.class).getBody();

        assertThat(saved).isNotNull();
        assertThat(saved.isApproved()).isEqualTo(false);
        assertThat(saved.getTimeCardEntries()).isEmpty();
        assertThat(saved.getEmployee().getEmployeeId()).isEqualTo(7654321);
        assertThat(saved.getEmployee().getFirstName()).isEqualTo("Joe");
        assertThat(saved.getEmployee().getLastName()).isEqualTo("Somebody");

    }

    @Test
    void shouldNotCreate_nonExistentEmployee() throws IOException {
        String fullUri = String.format("%s%s", testRestTemplate.getRootUri(), "/timecards");
        String jsonResource = nonExistentEmployeeFile.getContentAsString(Charset.defaultCharset());
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(jsonResource, headers);

        ResponseEntity<TimeCard> response = testRestTemplate.postForEntity(
                fullUri,
                entity,
                TimeCard.class
        );
        // TODO - DO WE CHANGE THE RETURN TO ADD SOMETHING MORE SPECIFIC HERE?
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

    }


}