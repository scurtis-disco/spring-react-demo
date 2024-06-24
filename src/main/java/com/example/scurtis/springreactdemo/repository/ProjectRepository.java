package com.example.scurtis.springreactdemo.repository;

import com.example.scurtis.springreactdemo.timekeeping.Project;
import org.springframework.data.repository.CrudRepository;
public interface ProjectRepository extends CrudRepository<Project, String> {
}
