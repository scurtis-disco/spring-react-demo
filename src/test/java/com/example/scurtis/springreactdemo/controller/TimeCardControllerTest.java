package com.example.scurtis.springreactdemo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.scurtis.springreactdemo.repository.TimeCardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(
        controllers = TimeCardController.class,
        properties = {}
)
@MockBean(
    value= {
        TimeCardRepository.class
    }
)
@AutoConfigureWebClient
@ActiveProfiles("test")
class TimeCardControllerTest {

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
}