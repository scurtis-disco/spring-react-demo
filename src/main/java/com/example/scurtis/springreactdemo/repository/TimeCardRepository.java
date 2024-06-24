package com.example.scurtis.springreactdemo.repository;

import com.example.scurtis.springreactdemo.timekeeping.TimeCard;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TimeCardRepository extends CrudRepository<TimeCard, UUID> {
}
