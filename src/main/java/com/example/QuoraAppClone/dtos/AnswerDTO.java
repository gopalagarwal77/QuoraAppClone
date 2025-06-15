package com.example.QuoraAppClone.dtos;

import lombok.Data;

@Data
public class AnswerDTO {
    private Long id;
    private String content;
    private long userId;
    private Long questionId;
}
