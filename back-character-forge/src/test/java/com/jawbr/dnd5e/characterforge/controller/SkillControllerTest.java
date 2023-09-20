package com.jawbr.dnd5e.characterforge.controller;

import com.jawbr.dnd5e.characterforge.dto.response.skill.SkillAbilityScoreDTO;
import com.jawbr.dnd5e.characterforge.dto.response.skill.SkillDTO;
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

import java.util.Collections;
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

    private String PATH = "/api/skills";

    private SkillDTO skillDTO;
    private SkillAbilityScoreDTO abilityScoreDTO;

    @BeforeEach
    public void init() {

        abilityScoreDTO = SkillAbilityScoreDTO.builder()
                .index("indexAb")
                .name("nameAb")
                .url("urlAb")
                .build();

        skillDTO = SkillDTO.builder()
                .index("index")
                .name("name")
                .desc("desc")
                .url("url")
                .ability_score(abilityScoreDTO)
                .build();
    }

    @Test
    void findAllSkills() throws Exception {

        List<SkillDTO> skillDTOList = Collections.singletonList(skillDTO);

        when(skillService.findAllSkills()).thenReturn(skillDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].index", is(skillDTO.index())))
                .andExpect(jsonPath("$[0].name", is(skillDTO.name())))
                .andExpect(jsonPath("$[0].desc", is(skillDTO.desc())))
                .andExpect(jsonPath("$[0].url", is(skillDTO.url())))
                .andExpect(jsonPath("$[0].ability_score.index", is(abilityScoreDTO.index())))
                .andExpect(jsonPath("$[0].ability_score.name", is(abilityScoreDTO.name())))
                .andExpect(jsonPath("$[0].ability_score.url", is(abilityScoreDTO.url())))
                .andDo(MockMvcResultHandlers.print());

    }
}