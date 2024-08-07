package com.example.scurtis.springreactdemo.timekeeping;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
//    @JsonBackReference
    private Employee employee;

    @OneToMany(mappedBy = "timeCard")
    @JsonManagedReference
    private List<TimeCardEntry> timeCardEntries;
    private boolean isApproved;
}
