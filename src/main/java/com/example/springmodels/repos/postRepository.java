package com.example.springmodels.repos;

import com.example.springmodels.models.modelPost;
import org.springframework.data.repository.CrudRepository;

public interface postRepository extends CrudRepository<modelPost, Long> {
    modelPost findByPostName(String postName);
}
