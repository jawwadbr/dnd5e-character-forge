package com.jawbr.dnd5e.characterforge.service;

import com.jawbr.dnd5e.characterforge.dto.mapper.theClass.ClassDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.mapper.theClass.ClassDTOResponseMapper;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.theClass.ClassDTO;
import com.jawbr.dnd5e.characterforge.exception.ClassNotFoundException;
import com.jawbr.dnd5e.characterforge.model.entity.Class;
import com.jawbr.dnd5e.characterforge.repository.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class ClassService {

    private final ClassRepository classRepository;
    private final ClassDTOResponseMapper classDTOResponseMapper;
    private final ClassDTOMapper classDTOMapper;

    private static final String URL = "/api/classes/";

    public ClassService(ClassRepository classRepository, ClassDTOResponseMapper classDTOResponseMapper, ClassDTOMapper classDTOMapper) {
        this.classRepository = classRepository;
        this.classDTOResponseMapper = classDTOResponseMapper;
        this.classDTOMapper = classDTOMapper;
    }

    public FindAllDTOResponse findAllClasses() {
        return Optional.of(classRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream()
                        .sorted(Comparator.comparing(Class::getClassName))
                        .collect(Collectors.toList()))
                .map(classDTOResponseMapper)
                .orElseThrow(() -> new ClassNotFoundException("No classes found."));
    }

    public ClassDTO findClassByIndexName(String indexName) {
        return Optional.ofNullable(classRepository.findByIndexName(indexName))
                .map(classDTOMapper)
                .orElseThrow(() -> new ClassNotFoundException(
                        String.format("Class with index name '%s' not found.", indexName)));
    }

}
