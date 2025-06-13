package com.example.QuoraAppClone.repositories;

import com.example.QuoraAppClone.models.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    Page<Question> findQuestionsByTags(Set<Long> tagIds, Pageable pageable);
}
