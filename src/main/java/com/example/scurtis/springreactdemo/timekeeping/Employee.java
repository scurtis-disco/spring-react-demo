package com.example.scurtis.springreactdemo.timekeeping;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder(setterPrefix = "with")
public class Employee {

    @Id
    @GeneratedValue
    private Long employeeId;

    @OneToMany
    @JoinColumn(name="employeeId")
    private List<TimeCard> timeCards;
    private String firstName;
    private String lastName;
    private String description;

}
