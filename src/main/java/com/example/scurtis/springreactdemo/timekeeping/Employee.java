package com.example.scurtis.springreactdemo.timekeeping;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonIgnore
    private List<TimeCard> timeCards;
    private String firstName;
    private String lastName;
    private String description;

}
