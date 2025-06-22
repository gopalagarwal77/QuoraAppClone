package com.example.QuoraAppClone.services;

import com.example.QuoraAppClone.dtos.AnswerDTO;
import com.example.QuoraAppClone.models.Answer;
import com.example.QuoraAppClone.models.Question;
import com.example.QuoraAppClone.models.User;
import com.example.QuoraAppClone.repositories.AnswerRepository;
import com.example.QuoraAppClone.repositories.QuestionRepository;
import com.example.QuoraAppClone.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;
    private UserRepository userRepository;

    public AnswerService(AnswerRepository answerRepository , QuestionRepository questionRepository ,UserRepository userRepository){
        this.answerRepository=answerRepository;
        this.questionRepository=questionRepository;
        this.userRepository=userRepository;
    }

    public List<Answer> getAnswerByQuestionId(Long questionId, int page , int size){
        return answerRepository.findByQuestionId(questionId, PageRequest.of(page,size)).getContent();
    }

    public Optional<Answer> getAnswerById(Long id){
        return answerRepository.findById(id);
    }

    public void deleteAnswer(Long id){
         answerRepository.deleteById(id);
    }

    public Answer createAnswer(AnswerDTO answerDTO){
        Answer answer = new Answer();
        answer.setContent(answerDTO.getContent());

        Optional<Question> question = questionRepository.findById(answerDTO.getQuestionId());
        question.ifPresent(answer::setQuestion);

        Optional<User> user = userRepository.findById(answerDTO.getUserId());
        user.ifPresent(answer::setUser);

        return answerRepository.save(answer);
    }

}
