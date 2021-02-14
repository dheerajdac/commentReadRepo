package com.dheeraj.commentReadRepo.controller;

import java.util.List;

import com.dheeraj.commentReadRepo.model.Comment;
import com.dheeraj.commentReadRepo.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    
    @Autowired
    CommentRepository repository;

    @GetMapping("/all")
    public List<Comment> getComments() {
        return this.repository.findAll();
    }
}
