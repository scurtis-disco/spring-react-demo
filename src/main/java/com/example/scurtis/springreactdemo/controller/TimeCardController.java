package com.example.scurtis.springreactdemo.controller;

import com.example.scurtis.springreactdemo.repository.TimeCardRepository;
import com.example.scurtis.springreactdemo.timekeeping.TimeCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
public class TimeCardController {

    private final TimeCardRepository timeCardRepository;

    private TimeCardController(TimeCardRepository timeCardRepository) {
        this.timeCardRepository = timeCardRepository;
    }

    @GetMapping(
            value = "/timecards/{timecardId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    private ResponseEntity<?> findTimeCardById(@PathVariable UUID timecardId) {
        log.debug("attempting to find timecard UUID -> {}", timecardId.toString());
        Optional<TimeCard> timeCardOptional = timeCardRepository.findById(timecardId);
        return timeCardOptional.isPresent() ? ResponseEntity.ok(timeCardOptional.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping(
        value = "/timecards",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    private ResponseEntity<TimeCard> createTimeCard(@RequestBody TimeCard timeCard, UriComponentsBuilder uriComponentsBuilder) {
        log.info("attempting to create a new time card: {}", timeCard.toString());
        TimeCard created = timeCardRepository.save(timeCard);
        URI locationOfTimeCard = uriComponentsBuilder
                .path("/timecards/{id}")
                .buildAndExpand(created.getTimeCardUuid())
                .toUri();
        return ResponseEntity.created(locationOfTimeCard).build();
    }
}
