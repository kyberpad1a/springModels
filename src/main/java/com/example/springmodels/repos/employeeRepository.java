package com.example.springmodels.repos;

import com.example.springmodels.models.modelEmployee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface employeeRepository extends CrudRepository<modelEmployee, Long> {
    List<modelEmployee> findByEmployeeInitialsContains(String employeeInitials);
}
