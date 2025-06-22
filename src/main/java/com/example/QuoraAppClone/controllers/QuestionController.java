package com.example.QuoraAppClone.controllers;

import com.example.QuoraAppClone.dtos.QuestionDTO;
import com.example.QuoraAppClone.models.Question;
import com.example.QuoraAppClone.repositories.QuestionRepository;
import com.example.QuoraAppClone.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public List<Question> getAllQuestions(@RequestParam int page, @RequestParam int size) {
        return questionService.getAllQuestions(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id){
        Optional<Question> question =  questionService.getQuestionById(id);
        return question.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public Question createQuestion(@RequestBody QuestionDTO questionDTO){
        return questionService.createQuestion(questionDTO);
    }

}
