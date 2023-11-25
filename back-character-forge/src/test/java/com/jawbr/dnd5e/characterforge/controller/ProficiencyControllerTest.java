package com.jawbr.dnd5e.characterforge.controller;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
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
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProficiencyController.class)
@AutoConfigureMockMvc
class ProficiencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProficiencyService proficiencyService;

    private final String PATH = "/api/proficiencies";

    private EntityReferenceDTO proficiencyForFindAll;
    private FindAllDTOResponse proficiencyResponse;
    private ProficiencyDTO proficiencyDTO;
    private EntityReferenceDTO race;

    @BeforeEach
    public void init() {
        proficiencyForFindAll = EntityReferenceDTO.builder()
                .index("skill-perception")
                .name("Skill: Perception")
                .url("/api/proficiencies/skill-perception")
                .build();

        List<EntityReferenceDTO> profList = new ArrayList<>();
        profList.add(proficiencyForFindAll);

        proficiencyResponse = FindAllDTOResponse.builder()
                .count(profList.size())
                .results(profList)
                .build();

        race = EntityReferenceDTO.builder()
                .index("elf")
                .name("Elf")
                .url("/api/races/elf")
                .build();

        List<EntityReferenceDTO> list = new ArrayList<>();
        list.add(race);

        proficiencyDTO = ProficiencyDTO.builder()
                .index("skill-perception")
                .type(ProficiencyType.Skills)
                .name("Skill: Perception")
                .races(list)
                .url("/api/proficiencies/skill-perception")
                .build();
    }

    @Test
    void findAllProficiencies() throws Exception {
        when(proficiencyService.findAllProficiencies()).thenReturn(proficiencyResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count", is(proficiencyResponse.count())))
                .andExpect(jsonPath("$.results[0].index", is(proficiencyForFindAll.index())))
                .andExpect(jsonPath("$.results[0].name", is(proficiencyForFindAll.name())))
                .andExpect(jsonPath("$.results[0].url", is(proficiencyForFindAll.url())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void findProficiencyByIndexName() throws Exception {
        when(proficiencyService.findByIndexName(proficiencyDTO.index())).thenReturn(proficiencyDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + proficiencyDTO.index())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.index", is(proficiencyDTO.index())))
                .andExpect(jsonPath("$.type", is(proficiencyDTO.type().getDisplayName())))
                .andExpect(jsonPath("$.name", is(proficiencyDTO.name())))
                .andExpect(jsonPath("$.races[0].index", is(race.index())))
                .andExpect(jsonPath("$.races[0].name", is(race.name())))
                .andExpect(jsonPath("$.races[0].url", is(race.url())))
                .andExpect(jsonPath("$.url", is(proficiencyDTO.url())))
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
                .andExpect(jsonPath("$.path", is(PATH)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cannotFindProficiencyByIndexName() throws Exception {
        when(proficiencyService.findByIndexName(proficiencyDTO.index()))
                .thenThrow(new ProficiencyNotFoundException(String.format("Proficiency with index name '%s' not found.", proficiencyDTO.index())));

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + proficiencyDTO.index())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is(String.format("Proficiency with index name '%s' not found.", proficiencyDTO.index()))))
                .andExpect(jsonPath("$.path", is(PATH + "/" + proficiencyDTO.index())))
                .andDo(MockMvcResultHandlers.print());
    }

}