package com.example.QuoraAppClone.repositories;

import com.example.QuoraAppClone.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {
    @Query("SELECT q FROM Question q JOIN q.tags t where t.id in :tagIds")
    Page<Answer> findByQuestionId(Long questionId , Pageable pageable);
}
