package com.jawbr.dnd5e.characterforge.controller;

import com.jawbr.dnd5e.characterforge.dto.response.proficiency.ProficiencyDTO;
import com.jawbr.dnd5e.characterforge.exception.ProficiencyNotFoundException;
import com.jawbr.dnd5e.characterforge.model.util.ProficiencyType;
import com.jawbr.dnd5e.characterforge.service.ProficiencyService;
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
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@WebMvcTest(ProficiencyController.class)
@AutoConfigureMockMvc
class ProficiencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProficiencyService proficiencyService;

    private final String PATH = "/api/proficiencies";

    private ProficiencyDTO proficiencyDTO;
    private ProficiencyRaceDTO proficiencyRaceDTO;

    @BeforeEach
    public void init() {
        proficiencyRaceDTO = ProficiencyRaceDTO.builder()
                .index("elf")
                .name("Elf")
                .url("/api/races/elf")
                .build();

        List<ProficiencyRaceDTO> proficiencyRaceDTOS = new ArrayList<>();
        proficiencyRaceDTOS.add(proficiencyRaceDTO);

        proficiencyDTO = ProficiencyDTO.builder()
                .index("skill-perception")
                .type(ProficiencyType.Skills)
                .name("Skill: Perception")
                .url("/api/proficiencies/skill-perception")
                .races(proficiencyRaceDTOS)
                .build();
    }

    @Test
    void findAllProficiencies() throws Exception {
        List<ProficiencyDTO> proficiencyDTOS = Collections.singletonList(proficiencyDTO);

        //when(proficiencyService.findAllProficiencies()).thenReturn(proficiencyDTOS);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].index", is(proficiencyDTO.index())))
                .andExpect(jsonPath("$[0].name", is(proficiencyDTO.name())))
                .andExpect(jsonPath("$[0].url", is(proficiencyDTO.url())))
                .andExpect(jsonPath("$[0].type", is(proficiencyDTO.type().getDisplayName())))
                .andExpect(jsonPath("$[0].races[0].index", is(proficiencyRaceDTO.index())))
                .andExpect(jsonPath("$[0].races[0].name", is(proficiencyRaceDTO.name())))
                .andExpect(jsonPath("$[0].races[0].url", is(proficiencyRaceDTO.url())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cannotFindAllProficiencies() throws Exception {
        when(proficiencyService.findAllProficiencies())
                .thenThrow(new ProficiencyNotFoundException("No proficiencies found."));

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("No proficiencies found.")))
                .andDo(MockMvcResultHandlers.print());
    }
}