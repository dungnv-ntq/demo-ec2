package com.dung.demoec2.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ESStudentRepository extends ElasticsearchRepository<ESStudentModel, Integer>{

    List<ESStudentModel> findByName(String name);
}
