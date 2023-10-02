package ru.practicum.ewm.service.compilation.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.ewm.service.compilation.model.Compilation;

import java.util.List;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {
    @Query("SELECT c FROM Compilation AS c " +
            "WHERE (:pinned IS NULL OR c.pinned = :pinned)")
    List<Compilation> findAllByPinned(@Param("pinned") Boolean pinned, Pageable pageable);

}
