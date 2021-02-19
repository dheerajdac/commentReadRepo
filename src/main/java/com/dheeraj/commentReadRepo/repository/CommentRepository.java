package com.dheeraj.commentReadRepo.repository;

import java.util.List;

import com.dheeraj.commentReadRepo.model.Comment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String>{

    List<Comment> findAll();
    
    List<Comment> findByParentIdAndId(String parentId, String id);

	List<Comment> findByParentId(String parentId);
    
}
