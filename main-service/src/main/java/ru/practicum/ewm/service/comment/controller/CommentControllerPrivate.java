package ru.practicum.ewm.service.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.service.comment.dto.CommentDto;
import ru.practicum.ewm.service.comment.dto.NewCommentDto;
import ru.practicum.ewm.service.comment.dto.UpdateCommentRequest;
import ru.practicum.ewm.service.comment.service.CommentService;
import ru.practicum.ewm.service.constants.HeaderConstants;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Slf4j
@Validated
@RequestMapping(path = "/users/{userId}/comments")
@RequiredArgsConstructor
public class CommentControllerPrivate {
    private final CommentService commentService;

    @PostMapping("/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(@Valid @RequestBody NewCommentDto commentDto,
                             @PathVariable long userId,
                             @PathVariable long eventId) {
        log.info("Создание комментария: {}", commentDto);
        return commentService.createByAuthor(commentDto, userId, eventId);
    }

    @PatchMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto update(@Valid @RequestBody UpdateCommentRequest commentRequest,
                             @PathVariable long userId,
                             @PathVariable long commentId) {
        log.info("Обновление комментария автором: {}", commentRequest);
        return commentService.updateByAuthor(commentRequest, userId, commentId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllComments(@PathVariable long userId,
                                           @RequestParam(defaultValue = HeaderConstants.DEFAULT_FROM_VALUE) @Min(0) int from,
                                           @RequestParam(defaultValue = HeaderConstants.DEFAULT_SIZE_VALUE) int size) {
        Pageable pageable = PageRequest.of(from / size, size, Sort.by("createdDate").descending());
        log.info("Запрос комментариев автором");
        return commentService.getAllCommentsByAuthor(userId, pageable);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable long userId,
                           @PathVariable long commentId) {
        commentService.deleteByCommentIdByAuthor(userId, commentId);
    }

}
