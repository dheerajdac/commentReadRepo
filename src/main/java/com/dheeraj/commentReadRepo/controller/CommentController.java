package com.dheeraj.commentReadRepo.controller;

import java.util.List;
import java.util.Optional;

import com.dheeraj.commentReadRepo.model.Comment;
import com.dheeraj.commentReadRepo.repository.CommentCustomReadRepo;
import com.dheeraj.commentReadRepo.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentRepository repository;

    @Autowired
    CommentCustomReadRepo customRepo;

    @GetMapping("/all")
    public List<Comment> getComments() {
        return this.repository.findAll();
    }

    //To do
    @GetMapping(value="/{id}")
    public Optional<Comment> getMethodName(@PathVariable String id) {
        return this.repository.findById(id);
    }
    

    @GetMapping("/{id}/{parentId}")
    public List<Comment> getCommentById(@PathVariable String parentId, @PathVariable String id) {
        return this.repository.findByParentIdAndId(parentId, id);
    }

    @GetMapping(value="/parentId/{parentId}")
    public List<Comment> getCommentByParentId(@PathVariable String parentId) {
        return this.repository.findByParentId(parentId);
    }
    
}
