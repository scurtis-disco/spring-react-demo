package com.example.scurtis.springreactdemo.timekeeping;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder(setterPrefix = "with")
@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class TimeCardEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID timeCardEntryUuid;
    private long startDayTime;
    private long endDayTime;
    @ManyToOne
    @JoinColumn(name = "timeCardUuid", referencedColumnName = "timeCardUuid")
    private TimeCard timeCard;

}
