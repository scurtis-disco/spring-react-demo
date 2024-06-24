package com.example.scurtis.springreactdemo.timekeeping;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Project {

    @Id
    private String id;
    private String name;
    private String code;
    private String description;

}
