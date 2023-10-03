package ru.practicum.ewm.service.participationRequest.model;

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
@Table(name = "participation_requests")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParticipationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "request_requester_id")
    User requester;

    @ManyToOne
    @JoinColumn(name = "request_event_id")
    Event event;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    ParticipationRequestStatus status;

    @Column(name = "request_created_date")
    LocalDateTime created;
}
