package com.jawbr.dnd5e.characterforge.service;

import com.jawbr.dnd5e.characterforge.dto.mapper.theClass.ClassDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.mapper.theClass.ClassDTOResponseMapper;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceOptionDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.FromOptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.OptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.theClass.ClassDTO;
import com.jawbr.dnd5e.characterforge.exception.ClassNotFoundException;
import com.jawbr.dnd5e.characterforge.model.entity.Class;
import com.jawbr.dnd5e.characterforge.model.entity.Proficiency;
import com.jawbr.dnd5e.characterforge.model.entity.StartingProficiencyOption;
import com.jawbr.dnd5e.characterforge.model.util.HitDie;
import com.jawbr.dnd5e.characterforge.model.util.OptionType;
import com.jawbr.dnd5e.characterforge.repository.ClassRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClassServiceTest {

    @InjectMocks
    private ClassService classService;

    @Mock
    private ClassRepository classRepository;

    @Mock
    private ClassDTOResponseMapper classDTOResponseMapper;

    @Mock
    private ClassDTOMapper classDTOMapper;

    private Class theClass;
    private FindAllDTOResponse classResponse;
    private ClassDTO classDTO;

    @BeforeEach
    void init() {

        Proficiency proficiency = Proficiency.builder()
                .indexName("skill-perception")
                .name("Skill: Perception")
                .url("/api/proficiencies/skill-perception")
                .build();

        theClass = Class.builder()
                .indexName("paladin")
                .className("Paladin")
                .hitDie(HitDie.d10)
                .proficiencyChoices(StartingProficiencyOption.builder()
                        .type(OptionType.proficiencies)
                        .choose(1)
                        .from(List.of(proficiency))
                        .build())
                .proficiencies(List.of(proficiency))
                .url("/api/classes/paladin")
                .build();

        //

        EntityReferenceDTO classForResponse = EntityReferenceDTO.builder()
                .name(theClass.getClassName())
                .index(theClass.getIndexName())
                .url(theClass.getUrl())
                .build();

        List<EntityReferenceDTO> classEntityReferenceDTOList = new ArrayList<>();
        classEntityReferenceDTOList.add(classForResponse);

        classResponse = FindAllDTOResponse.builder()
                .count(classEntityReferenceDTOList.size())
                .results(classEntityReferenceDTOList)
                .build();

        //

        EntityReferenceDTO proficiencyEntityReferenceDTO = EntityReferenceDTO.builder()
                .index("skill-perception")
                .name("Skill: Perception")
                .url("/api/proficiencies/skill-perception")
                .build();

        classDTO = ClassDTO.builder()
                .index(theClass.getIndexName())
                .name(theClass.getClassName())
                .hit_die(theClass.getHitDie().getValue())
                .proficiency_choices(OptionSetDTO.builder()
                        .desc("desc")
                        .type(OptionType.proficiencies)
                        .choose(2)
                        .from(FromOptionSetDTO.builder()
                                .options(List.of(EntityReferenceOptionDTO.builder()
                                        .item(proficiencyEntityReferenceDTO)
                                        .build()))
                                .build())
                        .build())
                .proficiencies(List.of(proficiencyEntityReferenceDTO))
                .url(theClass.getUrl())
                .build();
    }

    @Test
    void findAllClasses() {
        List<Class> classList = new ArrayList<>();
        classList.add(theClass);

        FindAllDTOResponse expected = classResponse;

        when(classRepository.findAll()).thenReturn(classList);
        when(classDTOResponseMapper.apply(classList)).thenReturn(classResponse);

        FindAllDTOResponse result = classService.findAllClasses();

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void findClassByIndexName() {
        ClassDTO expected = classDTO;

        when(classRepository.findByIndexName(theClass.getIndexName())).thenReturn(theClass);
        when(classDTOMapper.apply(theClass)).thenReturn(classDTO);

        ClassDTO result = classService.findClassByIndexName(theClass.getIndexName());

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void cannotFindAllClasses() {
        List<Class> classList = new ArrayList<>();
        when(classRepository.findAll()).thenReturn(classList);

        assertThrows(ClassNotFoundException.class,
                () -> classService.findAllClasses(),
                "No classes found.");
    }

    @Test
    void cannotFindClassByIndexName() {
        when(classRepository.findByIndexName(theClass.getIndexName())).thenReturn(null);

        assertThrows(ClassNotFoundException.class,
                () -> classService.findClassByIndexName(theClass.getIndexName()),
                String.format("Class with index name '%s' not found.", theClass.getIndexName()));
    }
}
