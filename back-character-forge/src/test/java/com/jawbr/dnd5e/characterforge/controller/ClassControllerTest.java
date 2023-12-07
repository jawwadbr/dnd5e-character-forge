package com.jawbr.dnd5e.characterforge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceOptionDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.FromOptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.OptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.theClass.ClassDTO;
import com.jawbr.dnd5e.characterforge.dto.response.theClass.MultiClassingPrerequisitesDTO;
import com.jawbr.dnd5e.characterforge.dto.response.theClass.PrerequisitesDTO;
import com.jawbr.dnd5e.characterforge.exception.ClassNotFoundException;
import com.jawbr.dnd5e.characterforge.model.util.HitDie;
import com.jawbr.dnd5e.characterforge.model.util.OptionType;
import com.jawbr.dnd5e.characterforge.service.ClassService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

@WebMvcTest(ClassController.class)
@AutoConfigureMockMvc
public class ClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClassService classService;

    @Autowired
    private ObjectMapper objectMapper;

    private final String PATH = "/api/classes";

    private EntityReferenceDTO classEntityReferenceDTO;
    private FindAllDTOResponse classResponse;
    private ClassDTO classDTO;

    @BeforeEach
    void init() {
        classEntityReferenceDTO = EntityReferenceDTO.builder()
                .index("paladin")
                .name("Paladin")
                .url("/api/classes/paladin")
                .build();

        List<EntityReferenceDTO> classList = new ArrayList<>();
        classList.add(classEntityReferenceDTO);

        classResponse = FindAllDTOResponse.builder()
                .count(classList.size())
                .results(classList)
                .build();

        //

        EntityReferenceDTO proficiencyEntityReferenceDTO = EntityReferenceDTO.builder()
                .index("skill-perception")
                .name("Skill: Perception")
                .url("/api/proficiencies/skill-perception")
                .build();

        EntityReferenceDTO savingThrowsDTO = EntityReferenceDTO.builder()
                .name("CHA")
                .index("cha")
                .url("/api/ability-scores/cha")
                .build();

        classDTO = ClassDTO.builder()
                .index("paladin")
                .name("Paladin")
                .hit_die(HitDie.d10.getValue())
                .proficiency_choices(OptionSetDTO.builder()
                        .desc("desc")
                        .type(OptionType.proficiencies)
                        .choose(2)
                        .from(FromOptionSetDTO.builder()
                                .options(List.of(EntityReferenceOptionDTO.builder()
                                        .item(proficiencyEntityReferenceDTO)
                                        .build()))
                                .build())
                        .build())
                .proficiencies(List.of(proficiencyEntityReferenceDTO))
                .saving_throws(List.of(savingThrowsDTO))
                .multi_classing(MultiClassingPrerequisitesDTO.builder()
                        .prerequisites(List.of(PrerequisitesDTO.builder()
                                .ability_score(savingThrowsDTO)
                                .minimum_score(13)
                                .build()))
                        .build())
                .url("/api/classes/paladin")
                .build();
    }

    @Test
    void findAllClasses() throws Exception {
        String classResponseJson = objectMapper.writeValueAsString(classResponse);

        when(classService.findAllClasses()).thenReturn(classResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(classResponseJson))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void findClassByIndexName() throws Exception {
        String classDTOJson = objectMapper.writeValueAsString(classDTO);

        when(classService.findClassByIndexName(classDTO.index())).thenReturn(classDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + classDTO.index())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(classDTOJson))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cannotFindAllClasses() throws Exception {
        when(classService.findAllClasses())
                .thenThrow(new ClassNotFoundException("No classes found."));

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("No classes found.")))
                .andExpect(jsonPath("$.path", is(PATH)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cannotFindClassByIndexName() throws Exception {
        when(classService.findClassByIndexName(classDTO.index()))
                .thenThrow(new ClassNotFoundException(String.format("Class with index name '%s' not found.", classDTO.index())));

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + classDTO.index())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is(String.format("Class with index name '%s' not found.", classDTO.index()))))
                .andExpect(jsonPath("$.path", is(PATH + "/" + classDTO.index())))
                .andDo(MockMvcResultHandlers.print());

    }
}
