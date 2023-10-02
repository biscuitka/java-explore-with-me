package ru.practicum.ewm.service.compilation.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.ewm.service.compilation.dto.CompilationDto;
import ru.practicum.ewm.service.compilation.dto.NewCompilationDto;
import ru.practicum.ewm.service.compilation.dto.UpdateCompilationRequest;

import java.util.List;

public interface CompilationService {
    List<CompilationDto> getAllCompilations(Boolean pinned, Pageable pageable);

    CompilationDto getCompilationById(Long compId);

    CompilationDto createCompilation(NewCompilationDto newCompilationDto);

    CompilationDto updateCompilation(Long compId, UpdateCompilationRequest compilationRequest);

    void deleteCompilationById(Long compId);
}
