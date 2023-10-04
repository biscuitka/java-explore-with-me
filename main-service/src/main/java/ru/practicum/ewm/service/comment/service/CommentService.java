package ru.practicum.ewm.service.comment.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.ewm.service.comment.dto.CommentDto;
import ru.practicum.ewm.service.comment.dto.NewCommentDto;
import ru.practicum.ewm.service.comment.dto.UpdateCommentRequest;

import java.util.List;

public interface CommentService {
    CommentDto createByAuthor(NewCommentDto commentDto, long userId, long eventId);

    CommentDto updateByAuthor(UpdateCommentRequest commentRequest, long userId, long commentId);

    List<CommentDto> getAllCommentsByAuthor(long userId, Pageable pageable);

    void deleteByCommentIdByAuthor(long userId, long commentId);

    List<CommentDto> getAllCommentsByEvent(long eventId, Pageable pageable);

    CommentDto updateByAdmin(UpdateCommentRequest commentRequest, long commentId);

    void deleteByCommentIdByAdmin(long commentId);
}
