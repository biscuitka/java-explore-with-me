package ru.practicum.ewm.service.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.service.comment.dto.CommentDto;
import ru.practicum.ewm.service.comment.dto.UpdateCommentRequest;
import ru.practicum.ewm.service.comment.service.CommentService;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping(path = "/admin/comments")
@RequiredArgsConstructor
public class CommentControllerAdmin {
    private final CommentService commentService;

    @PatchMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto update(@Valid @RequestBody UpdateCommentRequest commentRequest,
                             @PathVariable long commentId) {
        log.info("Обновление комментария администратором: {}", commentRequest);
        return commentService.updateByAdmin(commentRequest, commentId);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable long commentId) {
        log.info("Удаление комментария администратором по id: {}", commentId);
        commentService.deleteByCommentIdByAdmin(commentId);
    }
}
