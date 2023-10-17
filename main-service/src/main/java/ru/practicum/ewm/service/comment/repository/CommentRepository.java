package ru.practicum.ewm.service.comment.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.service.comment.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByEventId(long eventId, Pageable pageable);

    List<Comment> findAllByEventId(long eventId);

    List<Comment> findAllByAuthorId(Long authorId, Pageable pageable);
}
