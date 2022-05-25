package com.dung.demoec2;

import com.dung.demoec2.elastic.ESService;
import com.dung.demoec2.elastic.ESStudentModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class HelloController {
    @Autowired
    private StudentRepository repository;

    @Autowired
    private ESService esService;

    @Autowired
    private FileService fileService;

    @GetMapping("/hello")
    public String hello() {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");
        return "hello dungnv45, welcome to amazon ec2!!!";
    }


    @GetMapping("/students")
    public List<ESStudentModel> getAll(@RequestParam(required = false) String name) {
        return esService.findAll(name);
    }

    @PostMapping("/students")
    public Student create(@RequestBody Student student) {
        esService.save(esService.toModel(student));
        return repository.save(student);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String url = fileService.uploadFile(file);
        return ResponseEntity.ok(url);
    }
}
