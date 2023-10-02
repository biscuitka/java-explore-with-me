package ru.practicum.ewm.service.compilation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.ewm.service.compilation.dto.CompilationDto;
import ru.practicum.ewm.service.compilation.dto.NewCompilationDto;
import ru.practicum.ewm.service.compilation.model.Compilation;
import ru.practicum.ewm.service.event.model.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompilationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events", source = "events")
    Compilation fromNewDtoToCompilation(NewCompilationDto dto, List<Event> events);

    CompilationDto fromCompilationToDto(Compilation compilation);

    List<CompilationDto> fromCompilationListToDto(List<Compilation> compilations);
}
