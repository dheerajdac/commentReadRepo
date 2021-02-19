package com.dheeraj.commentReadRepo.repository;

import java.util.Optional;

import com.dheeraj.commentReadRepo.model.Comment;

public interface CommentCustomReadRepo {
    
    public Optional<Comment> findById(String id);

}
