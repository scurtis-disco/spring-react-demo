package com.example.scurtis.springreactdemo.timekeeping;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder(setterPrefix = "with")
@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class TimeCard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID timeCardUuid;
    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
    private Employee employee;
    @OneToMany
    private List<TimeCardEntry> timeCardEntries;
    private boolean isApproved;
}
