package com.example.QuoraAppClone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Tag extends BaseModel {

    private String name;

    @ManyToMany(mappedBy = "followedTags")

    private Set<User> followers;

    @ManyToMany(mappedBy = "tags")
    private Set<Question> questions;
}
