package ru.practicum.ewm.service.compilation.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.service.event.model.Event;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "compilations")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Compilation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compilation_id")
    Long id;

    @Column(name = "compilation_title")
    String title;

    @Column(name = "compilation_is_pinned")
    Boolean pinned;

    @ManyToMany
    @JoinTable(
            name = "compilations_events",
            joinColumns = @JoinColumn(name = "compilation_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    List<Event> events;
}