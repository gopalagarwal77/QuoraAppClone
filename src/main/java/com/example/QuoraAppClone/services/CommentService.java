package com.example.QuoraAppClone.services;

import com.example.QuoraAppClone.dtos.CommentDTO;
import com.example.QuoraAppClone.models.Answer;
import com.example.QuoraAppClone.models.Comment;
import com.example.QuoraAppClone.models.User;
import com.example.QuoraAppClone.repositories.AnswerRepository;
import com.example.QuoraAppClone.repositories.CommentRepository;
import com.example.QuoraAppClone.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private AnswerRepository answerRepository;
    private UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,AnswerRepository answerRepository,UserRepository userRepository){
        this.commentRepository=commentRepository;
        this.answerRepository=answerRepository;
        this.userRepository=userRepository;
    }

    public List<Comment> getAllCommentByAnswerId(long answerId , int page , int size){
        return commentRepository.findByAnswerId(answerId, PageRequest.of(page,size)).getContent();
    }

    public void deleteComment(long id){
        commentRepository.deleteById(id);
    }

    public Optional<Comment> getCommentById(Long id){
        return commentRepository.findById(id);
    }

    public List<Comment> getRepliesByAnswerId(Long commentId , int page , int size){
        return commentRepository.findByParentCommentId(commentId, PageRequest.of(page,size)).getContent();
    }

    public Comment createComment(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());

        Optional<Answer> answer = answerRepository.findById(commentDTO.getAnswerId());
        answer.ifPresent(comment::setAnswer);

        if (commentDTO.getParentCommentId() != null) {
            Optional<Comment> parentComment = commentRepository.findById(commentDTO.getParentCommentId());
            parentComment.ifPresent(comment::setParentComment);
        }

        if(commentDTO.getUserId()!=null){
            Optional<User> user = userRepository.findById(commentDTO.getUserId());
            user.ifPresent(comment::setUser);
        }

        return commentRepository.save(comment);

    }

}
