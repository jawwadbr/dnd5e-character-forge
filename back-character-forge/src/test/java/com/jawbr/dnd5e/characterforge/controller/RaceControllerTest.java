package com.jawbr.dnd5e.characterforge.controller;

import com.jawbr.dnd5e.characterforge.dto.response.race.RaceAbilityBonusesDTO;
import com.jawbr.dnd5e.characterforge.dto.response.race.RaceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.race.RaceLanguagesDTO;
import com.jawbr.dnd5e.characterforge.dto.response.race.RacialAbilityScoreBonusDTO;
import com.jawbr.dnd5e.characterforge.model.util.Size;
import com.jawbr.dnd5e.characterforge.service.RaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RaceController.class)
@AutoConfigureMockMvc
class RaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RaceService raceService;

    private String PATH = "/api/races";

    private RaceDTO raceDTO;
    private RaceAbilityBonusesDTO raceAbilityBonusesDTO;
    private RacialAbilityScoreBonusDTO racialAbilityScoreBonusDTO;
    private RaceLanguagesDTO raceLanguagesDTO;

    @BeforeEach
    public void init() {
        racialAbilityScoreBonusDTO = RacialAbilityScoreBonusDTO.builder()
                .index("RacialAbilityScoreBonusDTO index")
                .name("RacialAbilityScoreBonusDTO name")
                .url("RacialAbilityScoreBonusDTO url")
                .build();

        raceAbilityBonusesDTO = RaceAbilityBonusesDTO.builder()
                .ability_score(racialAbilityScoreBonusDTO)
                .bonus(1)
                .build();

        raceLanguagesDTO = RaceLanguagesDTO.builder()
                .index("raceLanguagesDTO index")
                .name("raceLanguagesDTO name")
                .url("raceLanguagesDTO url")
                .build();

        List<RaceAbilityBonusesDTO> raceAbilityBonusesDTOS = new ArrayList<>();
        raceAbilityBonusesDTOS.add(raceAbilityBonusesDTO);

        List<RaceLanguagesDTO> languageDTOList = new ArrayList<>();
        languageDTOList.add(raceLanguagesDTO);

        raceDTO = RaceDTO.builder()
                .index("raceDTO index")
                .name("raceDTO name")
                .speed(30)
                .ability_bonuses(raceAbilityBonusesDTOS)
                .alignment("raceDTO alignment")
                .age("raceDTO age")
                .size(Size.Medium)
                .size_description("raceDTO size description")
                .languages(languageDTOList)
                .language_desc("raceDTO lang desc")
                .url("raceDTO url")
                .build();
    }

    // This test will need to be modified in the future to pass when new Race implementations are made
    @Test
    void findAllRaces() throws Exception {

        List<RaceDTO> raceDTOS = Collections.singletonList(raceDTO);

        when(raceService.findAllRaces()).thenReturn(raceDTOS);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].index", is(raceDTO.index())))
                .andExpect(jsonPath("$[0].name", is(raceDTO.name())))
                .andExpect(jsonPath("$[0].speed", is(raceDTO.speed())))
                .andExpect(jsonPath("$[0].ability_bonuses[0].ability_score.index",
                        is(raceAbilityBonusesDTO.ability_score().index())))
                .andExpect(jsonPath("$[0].ability_bonuses[0].ability_score.name",
                        is(raceAbilityBonusesDTO.ability_score().name())))
                .andExpect(jsonPath("$[0].ability_bonuses[0].ability_score.url",
                        is(raceAbilityBonusesDTO.ability_score().url())))
                .andExpect(jsonPath("$[0].ability_bonuses[0].bonus",
                        is(raceAbilityBonusesDTO.bonus())))
                .andExpect(jsonPath("$[0].alignment", is(raceDTO.alignment())))
                .andExpect(jsonPath("$[0].age", is(raceDTO.age())))
                .andExpect(jsonPath("$[0].size", is(raceDTO.size().name())))
                .andExpect(jsonPath("$[0].size_description", is(raceDTO.size_description())))
                .andExpect(jsonPath("$[0].languages[0].index", is(raceLanguagesDTO.index())))
                .andExpect(jsonPath("$[0].languages[0].name", is(raceLanguagesDTO.name())))
                .andExpect(jsonPath("$[0].languages[0].url", is(raceLanguagesDTO.url())))
                .andExpect(jsonPath("$[0].language_desc", is(raceDTO.language_desc())))
                .andExpect(jsonPath("$[0].url", is(raceDTO.url())))
                .andDo(MockMvcResultHandlers.print());

    }
}