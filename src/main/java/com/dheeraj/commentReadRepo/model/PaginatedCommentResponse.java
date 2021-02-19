package com.dheeraj.commentReadRepo.model;

import java.nio.ByteBuffer;
import java.util.List;

import lombok.Data;

@Data
public class PaginatedCommentResponse {

    List<Comment> comments;

    ByteBuffer pagingState;
    
}
