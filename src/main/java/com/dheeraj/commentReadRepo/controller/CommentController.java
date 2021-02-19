package com.dheeraj.commentReadRepo.controller;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.util.List;
import java.util.Optional;

import com.dheeraj.commentReadRepo.model.Comment;
import com.dheeraj.commentReadRepo.model.PaginatedCommentRequest;
import com.dheeraj.commentReadRepo.model.PaginatedCommentResponse;
import com.dheeraj.commentReadRepo.repository.CommentCustomReadRepo;
import com.dheeraj.commentReadRepo.repository.CommentRepository;

import org.codehaus.jackson.util.ByteArrayBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @GetMapping(value="/parentId/{parentId}/pageable/{size}")
    public PaginatedCommentResponse getCommentByParentIdPaginated(@PathVariable String parentId, @PathVariable Integer size){

        Slice<Comment> result =  this.repository.findByParentId(parentId, CassandraPageRequest.of(0, size));

        PaginatedCommentResponse response = new PaginatedCommentResponse();
        response.setComments(result.getContent());
        response.setPagingState(((CassandraPageRequest)result.getPageable()).getPagingState());

        return response;
    }

    @PostMapping(value="/parentId/{parentId}/pageable/{size}/pageState")
    public PaginatedCommentResponse getCommentByParentIdPaginated(@PathVariable String parentId, @PathVariable Integer size, @RequestBody PaginatedCommentRequest pagingState){

        Slice<Comment> result =  this.repository.findByParentId(parentId, CassandraPageRequest.of(PageRequest.of(0, size), pagingState.getPagingState()));

        PaginatedCommentResponse response = new PaginatedCommentResponse();
        response.setComments(result.getContent());
        response.setPagingState(((CassandraPageRequest)result.getPageable()).getPagingState());

        return response;
    }
}
