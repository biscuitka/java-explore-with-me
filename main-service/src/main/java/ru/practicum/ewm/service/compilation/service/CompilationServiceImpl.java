package ru.practicum.ewm.service.compilation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.service.compilation.dto.CompilationDto;
import ru.practicum.ewm.service.compilation.dto.NewCompilationDto;
import ru.practicum.ewm.service.compilation.dto.UpdateCompilationRequest;
import ru.practicum.ewm.service.compilation.mapper.CompilationMapper;
import ru.practicum.ewm.service.compilation.model.Compilation;
import ru.practicum.ewm.service.compilation.repository.CompilationRepository;
import ru.practicum.ewm.service.event.model.Event;
import ru.practicum.ewm.service.event.repository.EventRepository;
import ru.practicum.ewm.service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {
    private final CompilationRepository compilationRepository;
    private final EventRepository eventRepository;
    private final CompilationMapper compilationMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CompilationDto> getAllCompilations(Boolean pinned, Pageable pageable) {
        List<Compilation> compilations = compilationRepository.findAllByPinned(pinned, pageable);
        return compilationMapper.fromCompilationListToDto(compilations);
    }

    @Override
    @Transactional(readOnly = true)
    public CompilationDto getCompilationById(Long compId) {
        Compilation compilation = getCompilationOrElseThrow(compId);
        return compilationMapper.fromCompilationToDto(compilation);
    }

    @Override
    public CompilationDto createCompilation(NewCompilationDto newCompilationDto) {
        List<Long> eventIds = newCompilationDto.getEvents();
        List<Event> events;

        if (eventIds != null && !eventIds.isEmpty()) {
            events = eventRepository.findAllById(eventIds);
        } else {
            events = new ArrayList<>();
        }

        if (newCompilationDto.getPinned() == null) {
            newCompilationDto.setPinned(false);
        }

        Compilation savedCompilation = compilationRepository
                .save(compilationMapper.fromNewDtoToCompilation(newCompilationDto, events));

        return compilationMapper.fromCompilationToDto(savedCompilation);
    }

    @Override
    public CompilationDto updateCompilation(Long compId, UpdateCompilationRequest compilationRequest) {
        Compilation compilation = getCompilationOrElseThrow(compId);

        if (compilationRequest.getEvents() != null) {
            compilation.setEvents(eventRepository.findAllById(compilationRequest.getEvents()));
        }
        Optional.ofNullable(compilationRequest.getTitle())
                .ifPresent(compilation::setTitle);

        Optional.ofNullable(compilationRequest.getPinned())
                .ifPresent(compilation::setPinned);

        Compilation updatedCompilation = compilationRepository.save(compilation);

        return compilationMapper.fromCompilationToDto(updatedCompilation);
    }

    @Override
    public void deleteCompilationById(Long compId) {
        getCompilationById(compId);
        compilationRepository.deleteById(compId);

    }

    private Compilation getCompilationOrElseThrow(long compId) {
        return compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException("Compilation with id=" + compId + " was not found"));
    }
}
