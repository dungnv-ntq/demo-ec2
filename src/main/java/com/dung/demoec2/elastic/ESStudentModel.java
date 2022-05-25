package com.dung.demoec2.elastic;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "students")
@Data
@NoArgsConstructor
public class ESStudentModel {
    @Id
    private String id;

    private String name;
    private Integer age;
    private String address;
}
