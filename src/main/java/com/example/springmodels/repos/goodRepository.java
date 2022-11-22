package com.example.springmodels.repos;

import com.example.springmodels.models.modelEmployee;
import com.example.springmodels.models.modelGood;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface goodRepository extends CrudRepository<modelGood, Long> {
    List<modelGood> findByGoodNameEquals(String goodName);
}
