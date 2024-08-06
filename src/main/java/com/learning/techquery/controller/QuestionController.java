package com.learning.techquery.controller;

import com.learning.techquery.model.Question;
import com.learning.techquery.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class QuestionController {
    @Autowired
    private QuestionService service;

    @GetMapping("/question/{id}")
    public ResponseEntity<Object> getQuestion(@PathVariable("id") String id) {
        Optional<Question> questionOptional = service.getDataOfQuestion(id);

        if (questionOptional.isPresent()) {
            return new ResponseEntity<>(questionOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Question Not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/question")
    public ResponseEntity<String> createQuestion(@RequestBody Question question) {
        Question createdQuestion = service.createQuestionData(question);
        if (createdQuestion != null) {
            return new ResponseEntity<>("Question is created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Question is failed", HttpStatus.OK);
    }

    @PutMapping("/question/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable("id") String id, @RequestBody Question question) {
        Question updatedQuestion = service.updateQuestionData(id, question);
        if (updatedQuestion != null) {
            return new ResponseEntity<>("record is updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("record is not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") String id) {
        return new ResponseEntity<>(service.deleteData(id), HttpStatus.OK);
    }

    @PatchMapping ("/question/{id}")
    public ResponseEntity<String> editQuestion(@PathVariable("id") String id, @RequestBody Question question) {
        Question editedQuestion = service.editQuestionData(id, question);
        if (editedQuestion != null) {
            return new ResponseEntity<>("record  updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("record not found", HttpStatus.NOT_FOUND);
    }

}
