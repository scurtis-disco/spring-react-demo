package com.example.scurtis.springreactdemo.repository;

import com.example.scurtis.springreactdemo.timekeeping.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
