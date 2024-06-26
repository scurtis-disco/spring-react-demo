package com.example.scurtis.springreactdemo.controller;

import com.example.scurtis.springreactdemo.repository.TimeCardRepository;
import com.example.scurtis.springreactdemo.timekeeping.TimeCard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

@RestController
public class TimeCardController {

    private final TimeCardRepository timeCardRepository;

    private TimeCardController(TimeCardRepository timeCardRepository) {
        this.timeCardRepository = timeCardRepository;
    }

    @GetMapping("/timecards/{timecardId}")
    private ResponseEntity<?> findById(@PathVariable UUID timecardId) {
        Optional<TimeCard> timeCardOptional = timeCardRepository.findById(timecardId);
        return timeCardOptional.isPresent() ? ResponseEntity.ok(timeCardOptional.get()) : ResponseEntity.notFound().build();
    }

}
