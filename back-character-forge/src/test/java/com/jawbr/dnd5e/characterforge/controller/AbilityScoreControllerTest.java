package com.jawbr.dnd5e.characterforge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.abilityScore.AbilityScoreDTO;
import com.jawbr.dnd5e.characterforge.exception.AbilityScoreNotFoundException;
import com.jawbr.dnd5e.characterforge.service.AbilityScoreService;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AbilityScoreController.class)
@AutoConfigureMockMvc
class AbilityScoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AbilityScoreService abilityScoreService;

    @Autowired
    private ObjectMapper objectMapper;

    private final String PATH = "/api/ability-scores";

    private EntityReferenceDTO abilityScoreForFindAll;
    private FindAllDTOResponse abilityScoreResponse;
    private AbilityScoreDTO abilityScoreDTO;
    private EntityReferenceDTO abilitySkills;

    @BeforeEach
    public void init() {
        abilityScoreForFindAll = EntityReferenceDTO.builder()
                .name("DEX")
                .index("dex")
                .url("/api/ability-scores/dex")
                .build();

        List<EntityReferenceDTO> findAllResponseAbilityScores = new ArrayList<>();
        findAllResponseAbilityScores.add(abilityScoreForFindAll);

        abilityScoreResponse = FindAllDTOResponse.builder()
                .count(findAllResponseAbilityScores.size())
                .results(findAllResponseAbilityScores)
                .build();

        abilitySkills = EntityReferenceDTO.builder()
                .name("Acrobatics")
                .index("acrobatics")
                .url("/api/skills/acrobatics")
                .build();

        List<EntityReferenceDTO> skillList = new ArrayList<>();
        skillList.add(abilitySkills);

        abilityScoreDTO = AbilityScoreDTO.builder()
                .index("dex")
                .full_name("Dexterity")
                .short_name("DEX")
                .desc("desc")
                .url("/api/ability-scores/dex")
                .skills(skillList)
                .build();
    }

    @Test
    void findAllAbilityScores() throws Exception {
        String abilityScoreResponseJson = objectMapper.writeValueAsString(abilityScoreResponse);

        when(abilityScoreService.findAllAbilityScores()).thenReturn(abilityScoreResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(abilityScoreResponseJson))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void findAbilityScoreByIndexName() throws Exception {
        String abilityScoreDTOJson = objectMapper.writeValueAsString(abilityScoreDTO);

        when(abilityScoreService.findAbilityScoresByIndexName(abilityScoreDTO.index())).thenReturn(abilityScoreDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + abilityScoreDTO.index())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(abilityScoreDTOJson))
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
                .andExpect(jsonPath("$.path", is(PATH)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cannotFindAbilityByIndexName() throws Exception {
        when(abilityScoreService.findAbilityScoresByIndexName(abilityScoreDTO.index()))
                .thenThrow(new AbilityScoreNotFoundException(String.format("Ability score with index name '%s' not found.", abilityScoreDTO.index())));

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + abilityScoreDTO.index())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is(String.format("Ability score with index name '%s' not found.", abilityScoreDTO.index()))))
                .andExpect(jsonPath("$.path", is(PATH + "/" + abilityScoreDTO.index())))
                .andDo(MockMvcResultHandlers.print());
    }

}
