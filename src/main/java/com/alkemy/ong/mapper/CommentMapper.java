package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.entity.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    
    Comment commentDtoToComment(CommentDto commentDto);
    CommentDto commentToCommentDto(Comment comment);
    List<CommentDto> commentsToCommentsDto(List<Comment> comments);
}
