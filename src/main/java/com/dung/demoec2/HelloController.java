package com.dung.demoec2;

import com.amazonaws.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class HelloController {
    @Autowired
    private StudentRepository repository;

    @Autowired
    private FileService fileService;

    @GetMapping("/hello")
    public String hello() {
        return "hello world from amazon ec2";
    }


    @GetMapping("/students")
    public List<Student> getAll() {
        return repository.findAll();
    }

    @PostMapping("/students")
    public Student create(@RequestBody Student student) {
        return repository.save(student);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.uploadFile(file);
        return ResponseEntity.ok("upload success");
    }
}
