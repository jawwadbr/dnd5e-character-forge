package com.jawbr.dnd5e.characterforge.service;

import com.jawbr.dnd5e.characterforge.dto.mapper.proficiency.ProficiencyDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.proficiency.ProficiencyDTO;
import com.jawbr.dnd5e.characterforge.exception.ProficiencyNotFoundException;
import com.jawbr.dnd5e.characterforge.model.entity.Proficiency;
import com.jawbr.dnd5e.characterforge.model.entity.Race;
import com.jawbr.dnd5e.characterforge.model.util.ProficiencyType;
import com.jawbr.dnd5e.characterforge.model.util.Size;
import com.jawbr.dnd5e.characterforge.repository.ProficiencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@Disabled
@SpringBootTest
class ProficiencyServiceTest {

    @InjectMocks
    private ProficiencyService proficiencyService;

    @Mock
    private ProficiencyRepository proficiencyRepository;

    @Mock
    private ProficiencyDTOMapper proficiencyDTOMapper;

//    private Proficiency proficiency;
//    private ProficiencyDTO proficiencyDTO;
//
//    @BeforeEach
//    void init() {
//        Race race = Race.builder()
//                .indexName("elf")
//                .raceName("Elf")
//                .speed(30)
//                .size(Size.Medium)
//                .age_desc("Although elves reach physical maturity at about the same age as humans, the elven understanding of " +
//                        "adulthood goes beyond physical growth to encompass worldly experience. An elf typically " +
//                        "claims adulthood and an adult name around the age of 100 and can live to be 750 years old.")
//                .alignment("Elves love freedom, variety, and self-expression, so they lean strongly toward the gentler aspects of chaos. " +
//                        "They value and protect others' freedom as well as their own, and they are more often good than not.")
//                .size_desc("Elves range from under 5 to over 6 feet tall and have slender builds. Your size is Medium.")
//                .language_desc("You can speak, read, and write Common and Elvish. Elvish is fluid, with subtle intonations and intricate grammar. " +
//                        "Elven literature is rich and varied, and their songs and poems are famous among other races. Many bards learn their " +
//                        "language so they can add Elvish ballads to their repertoires.")
//                .url("/api/races/elf")
//                .build();
//
//        List<Race> raceList = new ArrayList<>();
//        raceList.add(race);
//
//        proficiency = Proficiency.builder()
//                .indexName("skill-perception")
//                .type(ProficiencyType.Skills)
//                .name("Skill: Perception")
//                .url("/api/proficiencies/skill-perception")
//                .races(raceList)
//                .build();
//
//        EntityReferenceDTO proficiencyRaceDTO = EntityReferenceDTO.builder()
//                .index("elf")
//                .name("Elf")
//                .url("/api/races/elf")
//                .build();
//
//        List<EntityReferenceDTO> proficiencyRaceDTOS = new ArrayList<>();
//        proficiencyRaceDTOS.add(proficiencyRaceDTO);
//
//        proficiencyDTO = ProficiencyDTO.builder()
//                .index("skill-perception")
//                .type(ProficiencyType.Skills)
//                .name("Skill: Perception")
//                .url("/api/proficiencies/skill-perception")
//                .races(proficiencyRaceDTOS)
//                .build();
//    }
//
//    @Test
//    void findAllProficiencies() {
//        List<Proficiency> proficiencyList = new ArrayList<>();
//        proficiencyList.add(proficiency);
//
//        List<ProficiencyDTO> expected = new ArrayList<>(Collections.singleton(proficiencyDTO));
//
//        when(proficiencyRepository.findAll()).thenReturn(proficiencyList);
//        when(proficiencyDTOMapper.apply(proficiency)).thenReturn(proficiencyDTO);
//
//        //List<ProficiencyDTO> result = proficiencyService.findAllProficiencies();
//
//        //assertNotNull(result);
//        //assertEquals(expected, result);
//    }
//
//    @Test
//    void cannotFindAllProficiencies() {
//        List<Proficiency> proficiencyList = new ArrayList<>();
//        when(proficiencyRepository.findAll()).thenReturn(proficiencyList);
//
//        assertThrows(ProficiencyNotFoundException.class,
//                () -> proficiencyService.findAllProficiencies(),
//                "No proficiencies found.");
//    }
}