package com.jawbr.dnd5e.characterforge.controller;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.skill.SkillDTO;
import com.jawbr.dnd5e.characterforge.exception.SkillNotFoundException;
import com.jawbr.dnd5e.characterforge.service.SkillService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SkillController.class)
@AutoConfigureMockMvc
class SkillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SkillService skillService;

    private final String PATH = "/api/skills";

    private FindAllDTOResponse skillResponse;
    private EntityReferenceDTO skillForFindAll;
    private SkillDTO skillDTO;
    private EntityReferenceDTO abilityScoreForSkill;

    @BeforeEach
    public void init() {

        skillForFindAll = EntityReferenceDTO.builder()
                .index("acrobatics")
                .name("Acrobatics")
                .url("/api/skills/acrobatics")
                .build();

        List<EntityReferenceDTO> list = new ArrayList<>();
        list.add(skillForFindAll);

        skillResponse = FindAllDTOResponse.builder()
                .count(list.size())
                .results(list)
                .build();

        abilityScoreForSkill = EntityReferenceDTO.builder()
                .index("dex")
                .name("Dexterity")
                .url("/api/ability-scores/dex")
                .build();

        skillDTO = SkillDTO.builder()
                .name(skillForFindAll.name())
                .index(skillForFindAll.index())
                .url(skillForFindAll.url())
                .desc("desc")
                .ability_score(abilityScoreForSkill)
                .build();
    }

    @Test
    void findAllSkills() throws Exception {
        when(skillService.findAllSkills()).thenReturn(skillResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count", is(skillResponse.count())))
                .andExpect(jsonPath("$.results[0].index", is(skillForFindAll.index())))
                .andExpect(jsonPath("$.results[0].name", is(skillForFindAll.name())))
                .andExpect(jsonPath("$.results[0].url", is(skillForFindAll.url())))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void findSkillByIndexName() throws Exception {
        when(skillService.findSkillByIndexName(skillDTO.index())).thenReturn(skillDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + skillDTO.index())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.index", is(skillDTO.index())))
                .andExpect(jsonPath("$.name", is(skillDTO.name())))
                .andExpect(jsonPath("$.desc", is(skillDTO.desc())))
                .andExpect(jsonPath("$.url", is(skillDTO.url())))
                .andExpect(jsonPath("$.ability_score.index", is(abilityScoreForSkill.index())))
                .andExpect(jsonPath("$.ability_score.name", is(abilityScoreForSkill.name())))
                .andExpect(jsonPath("$.ability_score.url", is(abilityScoreForSkill.url())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cannotFindAllSkills() throws Exception {
        when(skillService.findAllSkills())
                .thenThrow(new SkillNotFoundException("No skills found."));

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("No skills found.")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cannotFindSkillByIndexName() throws Exception {
        when(skillService.findSkillByIndexName(skillDTO.index()))
                .thenThrow(new SkillNotFoundException(String.format("Skill with index name '%s' not found.", skillDTO.index())));

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + skillDTO.index())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is(String.format("Skill with index name '%s' not found.", skillDTO.index()))))
                .andExpect(jsonPath("$.path", is(PATH + "/" + skillDTO.index())))
                .andDo(MockMvcResultHandlers.print());
    }

}
