package com.dung.demoec2.elastic;

import com.dung.demoec2.Student;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ESService {
    @Autowired
    private ESStudentRepository esRepository;

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    public List<ESStudentModel> findAll(String name) {

        return esRepository.findByName(name);

    }

    public void save(ESStudentModel model) {
        esRepository.save(model);
    }

    public ESStudentModel toModel(Student student) {
        ESStudentModel model = new ESStudentModel();
        model.setName(student.getName());
        model.setAge(student.getAge());
        model.setAddress(student.getAddress());

        return model;
    }

}
