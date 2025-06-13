package com.example.QuoraAppClone.repositories;

import com.example.QuoraAppClone.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Page<Comment> findByAnswerId(long answerId, Pageable pageable);
    Page<Comment> findByParentCommentId(long parentCommentId, Pageable pageable);
}
