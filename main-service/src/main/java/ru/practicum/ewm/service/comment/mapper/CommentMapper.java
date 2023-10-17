package ru.practicum.ewm.service.comment.mapper;

import org.mapstruct.Mapper;
import ru.practicum.ewm.service.comment.dto.CommentDto;
import ru.practicum.ewm.service.comment.model.Comment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDto fromCommentToDto(Comment comment);

    List<CommentDto> fromCommentListToDto(List<Comment> comments);
}
