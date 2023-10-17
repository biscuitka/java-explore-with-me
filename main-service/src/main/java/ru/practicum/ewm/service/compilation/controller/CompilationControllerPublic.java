package ru.practicum.ewm.service.compilation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.service.compilation.dto.CompilationDto;
import ru.practicum.ewm.service.compilation.service.CompilationService;
import ru.practicum.ewm.service.constants.HeaderConstants;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/compilations")
@Validated
@RequiredArgsConstructor
public class CompilationControllerPublic {
    private final CompilationService compilationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompilationDto> getAll(@RequestParam(required = false) Boolean pinned,
                                       @RequestParam(defaultValue = HeaderConstants.DEFAULT_FROM_VALUE) @Min(0) int from,
                                       @RequestParam(defaultValue = HeaderConstants.DEFAULT_SIZE_VALUE) int size) {
        Pageable pageable = PageRequest.of(from / size, size);
        log.info("Запрос всех подборок событий");
        return compilationService.getAllCompilations(pinned, pageable);
    }

    @GetMapping("/{compId}")
    @ResponseStatus(HttpStatus.OK)
    public CompilationDto getById(@PathVariable Long compId) {
        log.info("Запрос подборки событий по id: {}", compId);
        return compilationService.getCompilationById(compId);
    }
}
