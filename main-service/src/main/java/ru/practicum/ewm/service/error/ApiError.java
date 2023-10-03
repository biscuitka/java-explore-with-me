package ru.practicum.ewm.service.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import ru.practicum.ewm.service.constants.UtilConstants;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiError {
    HttpStatus status;
    String reason;
    String message;
    private StackTraceElement[] errors;
    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = UtilConstants.DATETIME_FORMAT)
    private LocalDateTime errorTime;
}
