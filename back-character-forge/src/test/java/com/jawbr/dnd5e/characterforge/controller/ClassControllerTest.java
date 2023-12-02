package com.jawbr.dnd5e.characterforge.controller;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceOptionDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.FromOptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.OptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.theClass.ClassDTO;
import com.jawbr.dnd5e.characterforge.exception.ClassNotFoundException;
import com.jawbr.dnd5e.characterforge.model.util.HitDie;
import com.jawbr.dnd5e.characterforge.model.util.OptionType;
import com.jawbr.dnd5e.characterforge.service.ClassService;
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

@WebMvcTest(ClassController.class)
@AutoConfigureMockMvc
public class ClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClassService classService;

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
                .url("/api/classes/paladin")
                .build();
    }

    @Test
    void findAllClasses() throws Exception {
        when(classService.findAllClasses()).thenReturn(classResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count", is(classResponse.count())))
                .andExpect(jsonPath("$.results[0].index", is(classEntityReferenceDTO.index())))
                .andExpect(jsonPath("$.results[0].name", is(classEntityReferenceDTO.name())))
                .andExpect(jsonPath("$.results[0].url", is(classEntityReferenceDTO.url())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void findClassByIndexName() throws Exception {
        when(classService.findClassByIndexName(classDTO.index())).thenReturn(classDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + classDTO.index())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.index", is(classDTO.index())))
                .andExpect(jsonPath("$.name", is(classDTO.name())))
                .andExpect(jsonPath("$.hit_die", is(classDTO.hit_die())))
                // proficiency_choices
                .andExpect(jsonPath("$.proficiency_choices.choose", is(classDTO.proficiency_choices().choose())))
                .andExpect(jsonPath("$.proficiency_choices.type", is(classDTO.proficiency_choices().type().name())))
                .andExpect(jsonPath("$.proficiency_choices.desc", is(classDTO.proficiency_choices().desc())))
                .andExpect(jsonPath("$.proficiency_choices.from.options[0].item.index",
                        is(classDTO.proficiency_choices().from().options().get(0).item().index())))
                .andExpect(jsonPath("$.proficiency_choices.from.options[0].item.name",
                        is(classDTO.proficiency_choices().from().options().get(0).item().name())))
                .andExpect(jsonPath("$.proficiency_choices.from.options[0].item.url",
                        is(classDTO.proficiency_choices().from().options().get(0).item().url())))
                //
                .andExpect(jsonPath("$.proficiencies[0].index", is(classDTO.proficiencies().get(0).index())))
                .andExpect(jsonPath("$.proficiencies[0].name", is(classDTO.proficiencies().get(0).name())))
                .andExpect(jsonPath("$.proficiencies[0].url", is(classDTO.proficiencies().get(0).url())))
                .andExpect(jsonPath("$.url", is(classDTO.url())))
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
