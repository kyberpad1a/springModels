package com.example.springmodels.repos;

import com.example.springmodels.models.modelPasport;
import org.springframework.data.repository.CrudRepository;

public interface pasportRepository extends CrudRepository<modelPasport, Long> {
    modelPasport findByPasportNumber(int number);
}
