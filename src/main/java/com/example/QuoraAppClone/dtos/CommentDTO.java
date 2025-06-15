package com.example.QuoraAppClone.dtos;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String content;
    private long answerId;
    private long parentCommentId;
    private long userId;
}
