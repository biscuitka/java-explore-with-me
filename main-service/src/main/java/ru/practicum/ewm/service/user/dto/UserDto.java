package ru.practicum.ewm.service.user.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long id;

    @NotBlank(message = "Поле name обязательно для заполнения")
    @Size(min = 2, max = 250)
    String name;

    @NotBlank(message = "Поле email обязательно для заполнения")
    @Email(regexp = "^([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+.[a-zA-Z]{2,})$", message = "Введен некорректный email")
    @Size(min = 6, max = 254)
    String email;
}
