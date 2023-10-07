package com.jawbr.dnd5e.characterforge.service;

import com.jawbr.dnd5e.characterforge.dto.mapper.subRace.SubRaceDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceAbilityScoreBonusDTO;
import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceAbilityScoreDTO;
import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceRacialDTO;
import com.jawbr.dnd5e.characterforge.exception.RaceNotFoundException;
import com.jawbr.dnd5e.characterforge.model.entity.AbilityScore;
import com.jawbr.dnd5e.characterforge.model.entity.Race;
import com.jawbr.dnd5e.characterforge.model.entity.SubRace;
import com.jawbr.dnd5e.characterforge.model.entity.SubRaceAbilityScoreBonus;
import com.jawbr.dnd5e.characterforge.model.util.Size;
import com.jawbr.dnd5e.characterforge.repository.SubRaceRepository;
import org.junit.jupiter.api.BeforeEach;
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

@SpringBootTest
class SubRaceServiceTest {

    @InjectMocks
    private SubRaceService subRaceService;

    @Mock
    private SubRaceRepository subRaceRepository;

    @Mock
    private SubRaceDTOMapper subRaceDTOMapper;

    private SubRace subRace;
    private SubRaceDTO subRaceDTO;

    @BeforeEach
    void init() {
        Race race = Race.builder()
                .indexName("elf")
                .raceName("Elf")
                .speed(30)
                .size(Size.Medium)
                .age_desc("Although elves reach physical maturity at about the same age as humans, the elven understanding of " +
                        "adulthood goes beyond physical growth to encompass worldly experience. An elf typically " +
                        "claims adulthood and an adult name around the age of 100 and can live to be 750 years old.")
                .alignment("Elves love freedom, variety, and self-expression, so they lean strongly toward the gentler aspects of chaos. " +
                        "They value and protect others' freedom as well as their own, and they are more often good than not.")
                .size_desc("Elves range from under 5 to over 6 feet tall and have slender builds. Your size is Medium.")
                .language_desc("You can speak, read, and write Common and Elvish. Elvish is fluid, with subtle intonations and intricate grammar. " +
                        "Elven literature is rich and varied, and their songs and poems are famous among other races. Many bards learn their " +
                        "language so they can add Elvish ballads to their repertoires.")
                .url("/api/races/elf")
                .build();

        subRace = SubRace.builder()
                .id(1)
                .indexName("high-elf")
                .subRaceName("High Elf")
                .desc("As a high elf, you have a keen mind and a mastery of at least the basics of magic. " +
                        "In many fantasy gaming worlds, there are two kinds of high elves. " +
                        "One type is haughty and reclusive, believing themselves to be superior " +
                        "to non-elves and even other elves. The other type is more common and more " +
                        "friendly, and often encountered among humans and other races.")
                .url("/api/subraces/high-elf")
                .race(race)
                .abilityBonuses(Collections.singletonList(SubRaceAbilityScoreBonus.builder()
                        .abilityScore(AbilityScore.builder()
                                .id(1)
                                .indexName("indexA")
                                .shortName("shortNameA")
                                .fullName("fullNameA")
                                .desc("descA")
                                .url("urlA")
                                .build())
                        .subRace(subRace)
                        .bonus(2)
                        .build()))
                .build();

        SubRaceRacialDTO subRaceRacialDTO = SubRaceRacialDTO.builder()
                .index("elf")
                .name("Elf")
                .url("/api/races/elf")
                .build();

        subRaceDTO = SubRaceDTO.builder()
                .index("high-elf")
                .name("High Elf")
                .desc("As a high elf, you have a keen mind and a mastery of at least the basics of magic. " +
                        "In many fantasy gaming worlds, there are two kinds of high elves. " +
                        "One type is haughty and reclusive, believing themselves to be superior " +
                        "to non-elves and even other elves. The other type is more common and more " +
                        "friendly, and often encountered among humans and other races.")
                .url("/api/subraces/high-elf")
                .race(subRaceRacialDTO)
                .ability_bonuses(Collections.singletonList(SubRaceAbilityScoreBonusDTO.builder()
                        .ability_score(SubRaceAbilityScoreDTO.builder()
                                .index("indexA")
                                .name("shortNameA")
                                .url("urlA")
                                .build())
                        .bonus(2)
                        .build()))
                .build();
    }

    @Test
    void findAllSubRaces() {
        List<SubRace> subRaces = new ArrayList<>();
        subRaces.add(subRace);

        List<SubRaceDTO> expected = new ArrayList<>(Collections.singleton(subRaceDTO));

        when(subRaceRepository.findAll()).thenReturn(subRaces);
        when(subRaceDTOMapper.apply(subRace)).thenReturn(subRaceDTO);

        List<SubRaceDTO> result = subRaceService.findAllSubRaces();

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void cannotFindAllSubRaces() {
        List<SubRace> subRaces = new ArrayList<>();
        when(subRaceRepository.findAll()).thenReturn(subRaces);

        assertThrows(RaceNotFoundException.class,
                () -> subRaceService.findAllSubRaces(),
                "No sub races found.");
    }
}