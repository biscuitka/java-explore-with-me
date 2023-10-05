package ru.practicum.ewm.service.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.service.comment.dto.CommentDto;
import ru.practicum.ewm.service.comment.dto.NewCommentDto;
import ru.practicum.ewm.service.comment.dto.UpdateCommentRequest;
import ru.practicum.ewm.service.comment.mapper.CommentMapper;
import ru.practicum.ewm.service.comment.model.Comment;
import ru.practicum.ewm.service.comment.repository.CommentRepository;
import ru.practicum.ewm.service.event.model.Event;
import ru.practicum.ewm.service.event.repository.EventRepository;
import ru.practicum.ewm.service.exception.NotFoundException;
import ru.practicum.ewm.service.user.model.User;
import ru.practicum.ewm.service.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;


    @Override
    public CommentDto createByAuthor(NewCommentDto commentDto, long userId, long eventId) {
        User author = getUserOrElseThrow(userId);
        Event event = getEventOrElseThrow(eventId);

        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setEvent(event);
        comment.setText(commentDto.getText());
        comment.setCreatedDate(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        return commentMapper.fromCommentToDto(savedComment);
    }

    @Override
    public CommentDto updateByAuthor(UpdateCommentRequest commentRequest, long userId) {
        getUserOrElseThrow(userId);
        Comment comment = getCommentOrElseThrow(commentRequest.getId());

        if (!comment.getAuthor().getId().equals(userId)) {
            throw new NotFoundException("Комментарий для данного пользователя не найден");
        }
        Optional.ofNullable(commentRequest.getText())
                .ifPresent(comment::setText);
        Comment updatedComment = commentRepository.save(comment);

        return commentMapper.fromCommentToDto(updatedComment);
    }

    @Override
    public List<CommentDto> getAllCommentsByAuthor(long userId, Pageable pageable) {
        List<Comment> comments = commentRepository.findAllByAuthorId(userId, pageable);
        return commentMapper.fromCommentListToDto(comments);
    }

    @Override
    public void deleteByCommentIdByAuthor(long userId, long commentId) {
        getUserOrElseThrow(userId);
        Comment comment = getCommentOrElseThrow(commentId);

        if (!comment.getAuthor().getId().equals(userId)) {
            throw new NotFoundException("Комментарий для данного пользователя не найден");
        }
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getAllCommentsByEvent(long eventId, Pageable pageable) {
        List<Comment> comments = commentRepository.findAllByEventId(eventId, pageable);
        return commentMapper.fromCommentListToDto(comments);
    }

    @Override
    public CommentDto updateByAdmin(UpdateCommentRequest commentRequest) {
        Comment comment = getCommentOrElseThrow(commentRequest.getId());

        Optional.ofNullable(commentRequest.getText())
                .ifPresent(comment::setText);
        Comment updatedComment = commentRepository.save(comment);

        return commentMapper.fromCommentToDto(updatedComment);
    }

    @Override
    public void deleteByCommentIdByAdmin(long commentId) {
        getCommentOrElseThrow(commentId);
        commentRepository.deleteById(commentId);
    }

    private Event getEventOrElseThrow(long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Event with id=" + eventId + " was not found"));
    }

    private User getUserOrElseThrow(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id=" + userId + " was not found"));
    }

    private Comment getCommentOrElseThrow(long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment with id=" + commentId + " was not found"));
    }
}
