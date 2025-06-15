package com.example.QuoraAppClone.services;

import com.example.QuoraAppClone.dtos.TagDTO;
import com.example.QuoraAppClone.models.Tag;
import com.example.QuoraAppClone.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private TagRepository tagRepository;

    public TagService(TagRepository tagRepository){
        this.tagRepository=tagRepository;
    }

    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }

    public Tag getTagById(long id ){
        return tagRepository.findById(id).orElse(null);
    }

    public Tag createTag(TagDTO tagDto){
       Tag tag = new Tag();
       tag.setName(tagDto.getName());
       return tagRepository.save(tag);
    }

    public void deleteTag(long id){
        tagRepository.deleteById(id);
    }
}
