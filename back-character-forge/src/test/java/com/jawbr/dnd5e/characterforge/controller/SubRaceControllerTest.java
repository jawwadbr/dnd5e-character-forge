package com.jawbr.dnd5e.characterforge.controller;

import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceAbilityScoreBonusDTO;
import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceAbilityScoreDTO;
import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceRacialDTO;
import com.jawbr.dnd5e.characterforge.exception.RaceNotFoundException;
import com.jawbr.dnd5e.characterforge.service.SubRaceService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SubRaceController.class)
@AutoConfigureMockMvc
class SubRaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubRaceService subRaceService;

    private final String PATH = "/api/subraces";

    private SubRaceDTO subRaceDTO;
    private SubRaceRacialDTO subRaceRacialDTO;
    private SubRaceAbilityScoreBonusDTO subRaceAbilityScoreBonusDTO;

    @BeforeEach
    void init() {

        SubRaceAbilityScoreDTO subRaceAbilityScoreDTO = SubRaceAbilityScoreDTO.builder()
                .name("dex")
                .index("dex")
                .url("url score")
                .build();

        subRaceAbilityScoreBonusDTO = SubRaceAbilityScoreBonusDTO.builder()
                .ability_score(subRaceAbilityScoreDTO)
                .bonus(2)
                .build();

        subRaceRacialDTO = SubRaceRacialDTO.builder()
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
                .ability_bonuses(Collections.singletonList(subRaceAbilityScoreBonusDTO))
                .build();
    }

    @Test
    void findAllSubRaces() throws Exception {
        List<SubRaceDTO> subRaceDTOS = Collections.singletonList(subRaceDTO);

        when(subRaceService.findAllSubRaces()).thenReturn(subRaceDTOS);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].index", is(subRaceDTO.index())))
                .andExpect(jsonPath("$[0].name", is(subRaceDTO.name())))
                .andExpect(jsonPath("$[0].desc", is(subRaceDTO.desc())))
                .andExpect(jsonPath("$[0].url", is(subRaceDTO.url())))
                .andExpect(jsonPath("$[0].race.index", is(subRaceRacialDTO.index())))
                .andExpect(jsonPath("$[0].race.name", is(subRaceRacialDTO.name())))
                .andExpect(jsonPath("$[0].race.url", is(subRaceRacialDTO.url())))
                .andExpect(jsonPath("$[0].ability_bonuses[0].ability_score.index",
                        Is.is(subRaceAbilityScoreBonusDTO.ability_score().index())))
                .andExpect(jsonPath("$[0].ability_bonuses[0].ability_score.name",
                        Is.is(subRaceAbilityScoreBonusDTO.ability_score().name())))
                .andExpect(jsonPath("$[0].ability_bonuses[0].ability_score.url",
                        Is.is(subRaceAbilityScoreBonusDTO.ability_score().url())))
                .andExpect(jsonPath("$[0].ability_bonuses[0].bonus",
                        Is.is(subRaceAbilityScoreBonusDTO.bonus())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cannotFindAllSubRaces() throws Exception {
        when(subRaceService.findAllSubRaces())
                .thenThrow(new RaceNotFoundException("No sub races found."));

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("No sub races found.")))
                .andDo(MockMvcResultHandlers.print());
    }
}