package com.dung.demoec2.elastic;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ESStudentRepository extends ElasticsearchRepository<ESStudentModel, Integer>{

    List<ESStudentModel> findByNameContainsAndAddressContains(String name, String Address);

    @Query("{\"match\":{\"name\":\"?0\"}}")
    List<ESStudentModel> findByNameUsingAnnotations(String name);
}
