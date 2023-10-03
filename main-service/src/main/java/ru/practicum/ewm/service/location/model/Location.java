package ru.practicum.ewm.service.location.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "locations")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    Long id;

    @Column(name = "location_lat")
    float lat;

    @Column(name = "location_lon")
    float lon;
}
