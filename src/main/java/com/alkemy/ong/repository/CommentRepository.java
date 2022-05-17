package com.alkemy.ong.repository;

import com.alkemy.ong.entity.Comment;
import com.alkemy.ong.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    @Query("SELECT c.body FROM Comment c ORDER BY c.creationDate")
    List<Comment> findOrderedComments();
    
    @Query("UPDATE Comment c SET c.active = 0 WHERE c.id = :id")
    @Modifying
    void softDelete(@Param("id") Long id);

    List<Comment> findByNews(News news);
}
