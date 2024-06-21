package com.example.scurtis.springreactdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeCardController {

    @GetMapping("/timecards/{timecardId}")
    private ResponseEntity<?> findById(@PathVariable Long timecardId) {
        return null;
    }
}
