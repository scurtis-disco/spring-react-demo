package com.example.scurtis.springreactdemo.timekeeping;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Builder(setterPrefix = "with")
public class TimeCardEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID timeCardEntryUuid;
    private long startDayTime;
    private long startEndTime;
    private UUID timeCardUuid;

}
