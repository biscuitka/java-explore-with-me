package ru.practicum.ewm.stats.server.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "endpoints_hits")
public class EndpointHit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hit_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "app_id", nullable = false)
    Application app;

    @Column(name = "hit_uri")
    String uri;

    @Column(name = "hit_ip")
    String ip;

    @Column(name = "hit_timestamp")
    LocalDateTime timestamp;

}
