package ru.practicum.ewm.service.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.ewm.service.exception.BadRequestException;
import ru.practicum.ewm.service.exception.ConflictException;
import ru.practicum.ewm.service.exception.InvalidTimeException;
import ru.practicum.ewm.service.exception.NotFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(MethodArgumentNotValidException exp) {
        log.error("Ошибка валидации", exp);
        return ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .reason("Incorrectly made request.")
                .message(exp.getMessage())
                .errorTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBadRequestException(final BadRequestException exp) {
        log.error("Запрос составлен некорректно", exp);
        return ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .reason("Incorrectly made request.")
                .message(exp.getMessage())
                .errorTime(LocalDateTime.now())
                .build();
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiError handleInvalidTimeException(final InvalidTimeException exp) {
        log.error("Ошибка установки даты", exp);
        return ApiError.builder()
                .status(HttpStatus.FORBIDDEN)
                .reason("For the requested operation the conditions are not met.")
                .message(exp.getMessage())
                .errorTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(final NotFoundException exp) {
        log.error("Объект не найден", exp);
        return ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .reason("The required object was not found.")
                .message(exp.getMessage())
                .errorTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleThrowable(final Throwable exp) {
        log.error("Ошибка сервера", exp);
        return ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .reason(exp.getClass() + " Internal server error.")
                .message(exp.getMessage())
                .errors(exp.getStackTrace())
                .errorTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleConflictException(final ConflictException exp) {
        log.error("Для запрошенной операции условия не выполнены", exp);
        return ApiError.builder()
                .status(HttpStatus.CONFLICT)
                .reason("For the requested operation the conditions are not met.")
                .message(exp.getMessage())
                .errorTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleRequestParameterException(MissingServletRequestParameterException exp) {
        log.error("Запрос составлен некорректно", exp);
        return ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .reason("Incorrectly made request.")
                .message(exp.getMessage())
                .errorTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleDataIntegrityViolationException(DataIntegrityViolationException exp) {
        log.error("Ограничение целостности было нарушено", exp);
        return ApiError.builder()
                .status(HttpStatus.CONFLICT)
                .reason("Integrity constraint has been violated.")
                .message(exp.getMessage())
                .errorTime(LocalDateTime.now())
                .build();
    }

}
