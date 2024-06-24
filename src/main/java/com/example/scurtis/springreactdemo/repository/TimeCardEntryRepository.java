package com.example.scurtis.springreactdemo.repository;

import com.example.scurtis.springreactdemo.timekeeping.TimeCardEntry;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TimeCardEntryRepository extends CrudRepository<TimeCardEntry, UUID> {
}
