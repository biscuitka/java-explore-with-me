package ru.practicum.ewm.service.compilation.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.service.compilation.dto.CompilationDto;
import ru.practicum.ewm.service.compilation.dto.NewCompilationDto;
import ru.practicum.ewm.service.compilation.dto.UpdateCompilationRequest;
import ru.practicum.ewm.service.compilation.service.CompilationService;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping(path = "/admin/compilations")
@RequiredArgsConstructor
public class CompilationControllerAdmin {
    private final CompilationService compilationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto create(@Valid @RequestBody NewCompilationDto newCompilationDto) {
        log.info("Создание подборки событий: {}", newCompilationDto);
        return compilationService.createCompilation(newCompilationDto);
    }

    @PatchMapping("/{compId}")
    @ResponseStatus(HttpStatus.OK)
    public CompilationDto updateCompilation(@PathVariable long compId,
                                            @Valid @RequestBody UpdateCompilationRequest compilationRequest) {
        log.info("Обновление подборки событий: {}", compilationRequest);
        return compilationService.updateCompilation(compId, compilationRequest);
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable long compId) {
        log.info("Удаление подборки по id: {}", compId);
        compilationService.deleteCompilationById(compId);
    }
}
