package org.iot.patterns.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ServiceUtils {
    public <E, I> Map<I, E> createEntityMap(JpaRepository<E, I> repository, Function<E, I> idExtractor) {
        List<E> entities = repository.findAll();
        return entities.stream().collect(Collectors.toMap(idExtractor, Function.identity()));
    }
}
