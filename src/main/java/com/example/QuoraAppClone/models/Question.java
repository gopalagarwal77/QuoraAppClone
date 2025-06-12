package com.example.QuoraAppClone.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Question extends BaseModel{

    private String title;
    private String content;

    @ManyToMany
    @JoinTable(
            name="questions_tags",
            joinColumns = @JoinColumn(name="question_id"),
            inverseJoinColumns = @JoinColumn(name="tag_id")
    )

    private Set<Tag> tags;

    @ManyToOne
    @JoinTable(name="user_id")
    private User user;
}
