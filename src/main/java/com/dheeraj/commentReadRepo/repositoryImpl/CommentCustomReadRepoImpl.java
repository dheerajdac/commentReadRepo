package com.dheeraj.commentReadRepo.repositoryImpl;

import java.util.Optional;

import com.dheeraj.commentReadRepo.model.Comment;
import com.dheeraj.commentReadRepo.repository.CommentCustomReadRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.query.ColumnName;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CommentCustomReadRepoImpl implements CommentCustomReadRepo {

    @Autowired
    CassandraOperations op;

    @Override
    public Optional<Comment> findById(String id) {
        ColumnName isDeleted = ColumnName.from("isDeleted");
        Comment comment = this.op.selectOne(
            Query.query(
                Criteria.where("id").is(id))
                .and(Criteria.where(isDeleted).is(false)),
            Comment.class);        
        return Optional.ofNullable(comment);
    }

    
    
}
