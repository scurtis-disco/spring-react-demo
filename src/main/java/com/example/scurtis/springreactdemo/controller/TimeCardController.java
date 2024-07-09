package com.example.scurtis.springreactdemo.controller;

import com.example.scurtis.springreactdemo.repository.TimeCardRepository;
import com.example.scurtis.springreactdemo.timekeeping.TimeCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
public class TimeCardController {

    private final TimeCardRepository timeCardRepository;

    private TimeCardController(TimeCardRepository timeCardRepository) {
        this.timeCardRepository = timeCardRepository;
    }

    @GetMapping("/timecards/{timecardId}")
    private ResponseEntity<?> findById(@PathVariable UUID timecardId) {
        log.info("attempting to find timecard UUID -> {}", timecardId.toString());
        timeCardRepository.findAll().forEach(tc -> {
            log.info("found time card: {}", tc.getTimeCardUuid().toString());
        });
        Optional<TimeCard> timeCardOptional = timeCardRepository.findById(timecardId);
        return timeCardOptional.isPresent() ? ResponseEntity.ok(timeCardOptional.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping("/timecards")
    private TimeCard createTimeCard(@RequestBody TimeCard timeCard) {
        log.info("attempting to create a new time card: {}", timeCard.toString());
        return timeCardRepository.save(timeCard);
    }
}
