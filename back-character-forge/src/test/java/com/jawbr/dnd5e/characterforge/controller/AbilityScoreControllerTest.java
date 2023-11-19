package com.jawbr.dnd5e.characterforge.controller;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.abilityScore.AbilityScoreDTO;
import com.jawbr.dnd5e.characterforge.exception.AbilityScoreNotFoundException;
import com.jawbr.dnd5e.characterforge.service.AbilityScoreService;
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
@WebMvcTest(AbilityScoreController.class)
@AutoConfigureMockMvc
class AbilityScoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AbilityScoreService abilityScoreService;

    private final String PATH = "/api/ability-scores";

    private EntityReferenceDTO abilityScoreSkillDTO;
    private AbilityScoreDTO abilityScoreDTO;

    @BeforeEach
    public void init() {
        abilityScoreSkillDTO = EntityReferenceDTO.builder()
                .index("skill-index")
                .name("Skill Index")
                .url("/api/skills/skill-index")
                .build();

        List<EntityReferenceDTO> abilityScoreSkillDTOS = new ArrayList<>();
        abilityScoreSkillDTOS.add(abilityScoreSkillDTO);

        abilityScoreDTO = AbilityScoreDTO.builder()
                .index("index")
                .short_name("ind")
                .full_name("Index")
                .desc("Index desc")
                .url("/api/ability-scores/index")
                .skills(abilityScoreSkillDTOS)
                .build();
    }

    @Disabled
    @Test
    void findAllAbilityScores() throws Exception {
        List<AbilityScoreDTO> abilityScoreDTOS = Collections.singletonList(abilityScoreDTO);

        //when(abilityScoreService.findAllAbilityScores()).thenReturn(abilityScoreDTOS);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].index", is(abilityScoreDTO.index())))
                .andExpect(jsonPath("$[0].short_name", is(abilityScoreDTO.short_name())))
                .andExpect(jsonPath("$[0].full_name", is(abilityScoreDTO.full_name())))
                .andExpect(jsonPath("$[0].desc", is(abilityScoreDTO.desc())))
                .andExpect(jsonPath("$[0].url", is(abilityScoreDTO.url())))
                .andExpect(jsonPath("$[0].skills[0].index", is(abilityScoreSkillDTO.index())))
                .andExpect(jsonPath("$[0].skills[0].name", is(abilityScoreSkillDTO.name())))
                .andExpect(jsonPath("$[0].skills[0].url", is(abilityScoreSkillDTO.url())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cannotFindAllAbility() throws Exception {
        when(abilityScoreService.findAllAbilityScores())
                .thenThrow(new AbilityScoreNotFoundException("No ability scores found."));

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("No ability scores found.")))
                .andDo(MockMvcResultHandlers.print());
    }

}
