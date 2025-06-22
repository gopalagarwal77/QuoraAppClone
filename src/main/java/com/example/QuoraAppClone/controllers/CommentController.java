package com.example.QuoraAppClone.controllers;

import com.example.QuoraAppClone.dtos.CommentDTO;
import com.example.QuoraAppClone.models.Comment;
import com.example.QuoraAppClone.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/answer/{answerId}")
    public List<Comment> getAllCommentByAnswerId(@PathVariable Long answerId, @RequestParam int page, @RequestParam int size){
        return commentService.getAllCommentByAnswerId(answerId,page,size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id){
        Optional<Comment> comment = commentService.getCommentById(id);
        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/comment/{commentId}")
    public List<Comment> getRepliesByAnswerId(@PathVariable Long commentId ,@RequestParam int page ,@RequestParam int size){
        return commentService.getRepliesByAnswerId(commentId,page,size);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id){
         commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentDTO commentDTO){
        return commentService.createComment(commentDTO);
    }

}
