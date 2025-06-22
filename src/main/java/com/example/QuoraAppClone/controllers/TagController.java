package com.example.QuoraAppClone.controllers;

import com.example.QuoraAppClone.dtos.TagDTO;
import com.example.QuoraAppClone.models.Tag;
import com.example.QuoraAppClone.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    private List<Tag> getAllTags(){
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id){
        Optional<Tag> tag =  tagService.getTagById(id);
        return tag.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTagById(@PathVariable Long id){
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public Tag createTag(@RequestBody TagDTO tagDTO){
        return tagService.createTag(tagDTO);
    }

}
