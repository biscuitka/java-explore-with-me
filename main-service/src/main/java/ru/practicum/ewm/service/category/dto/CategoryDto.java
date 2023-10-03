package ru.practicum.ewm.service.category.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDto {
    Long id;

    @NotBlank(message = "Поле name обязательно для заполнения")
    @Size(min = 1, max = 50)
    String name;
}
