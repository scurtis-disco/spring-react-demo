package com.example.scurtis.springreactdemo.timekeeping;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class TimeCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID timeCardUuid;
    private Long employeeId;
    @OneToMany
    @JoinColumn(name="timeCardUuid", referencedColumnName = "timeCardUuid")
    private List<TimeCardEntry> timeCardEntries;
    private boolean isApproved;
}
