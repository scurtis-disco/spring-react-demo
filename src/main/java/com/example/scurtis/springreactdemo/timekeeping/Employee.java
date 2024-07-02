package com.example.scurtis.springreactdemo.timekeeping;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Builder(setterPrefix = "with")
@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Employee {

    @Id
    @GeneratedValue
    private Long employeeId;

    @OneToMany
    private List<TimeCard> timeCards;
    private String firstName;
    private String lastName;
    private String description;

}
