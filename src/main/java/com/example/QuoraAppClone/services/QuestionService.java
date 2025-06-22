package com.example.QuoraAppClone.services;

import com.example.QuoraAppClone.dtos.QuestionDTO;
import com.example.QuoraAppClone.models.Question;
import com.example.QuoraAppClone.models.Tag;
import com.example.QuoraAppClone.models.User;
import com.example.QuoraAppClone.repositories.QuestionRepository;
import com.example.QuoraAppClone.repositories.TagRepository;
import com.example.QuoraAppClone.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;
    private UserRepository userRepository;
    private TagRepository tagRepository;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    public List<Question> getAllQuestions(int offset, int limit) {
        return questionRepository.findAll(PageRequest.of(offset, limit)).getContent();
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public Optional<Question> getQuestionById(long id) {
        return questionRepository.findById(id);
    }

    public Question createQuestion(QuestionDTO questionDto) {
        Question question = new Question();
        question.setTitle(questionDto.getTitle());
        question.setContent(questionDto.getContent());

        Optional<User> user = userRepository.findById(questionDto.getUserId());
        user.ifPresent(question::setUser);    // set the user on the question object

        Set<Tag> tags = questionDto.getTagIds().stream()
                .map(tagRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(java.util.stream.Collectors.toSet());

        question.setTags(tags);

        return questionRepository.save(question);
    }

//    public List<Question> getQuestionsByTagId(Long id){
//        questionRepository.findQuestionByTags
//    }
}
