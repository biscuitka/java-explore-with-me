package ru.practicum.ewm.service.comment.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.service.event.model.Event;
import ru.practicum.ewm.service.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comments")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "comment_author_id")
    User author;

    @ManyToOne
    @JoinColumn(name = "comment_event_id")
    Event event;

    @Column(name = "comment_text")
    String text;

    @Column(name = "comment_created_date")
    LocalDateTime createdDate;
}
