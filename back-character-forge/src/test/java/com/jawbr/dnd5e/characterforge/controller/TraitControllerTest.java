package com.jawbr.dnd5e.characterforge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceOptionDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.FromOptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.OptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.trait.TraitDTO;
import com.jawbr.dnd5e.characterforge.exception.TraitNotFoundException;
import com.jawbr.dnd5e.characterforge.model.util.OptionType;
import com.jawbr.dnd5e.characterforge.service.TraitService;
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
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TraitController.class)
@AutoConfigureMockMvc
public class TraitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TraitService traitService;

    @Autowired
    private ObjectMapper objectMapper;

    private final String PATH = "/api/traits";

    private FindAllDTOResponse traitResponse;
    private List<EntityReferenceDTO> traitsForFindAll;
    private TraitDTO traitDTO;

    @BeforeEach
    public void init() {

        traitsForFindAll = new ArrayList<>();

        traitsForFindAll.add(EntityReferenceDTO.builder()
                .index("high-elf-cantrip")
                .name("High Elf Cantrip")
                .url("/api/traits/high-elf-cantrip")
                .build());
        traitsForFindAll.add(EntityReferenceDTO.builder()
                .index("extra-language")
                .name("Extra Language")
                .url("/api/traits/extra-language")
                .build());

        traitResponse = FindAllDTOResponse.builder()
                .count(traitsForFindAll.size())
                .results(traitsForFindAll)
                .build();

        traitDTO = TraitDTO.builder()
                .index("high-elf-cantrip")
                .races(Collections.singletonList(EntityReferenceDTO.builder()
                        .index("elf")
                        .name("Elf")
                        .url("/api/races/elf")
                        .build()))
                .subraces(Collections.singletonList(EntityReferenceDTO.builder()
                        .index("high-elf")
                        .name("High Elf")
                        .url("/api/subraces/high-elf")
                        .build()))
                .name("High Elf Cantrip")
                .desc("desc")
                .proficiencies(Collections.singletonList(EntityReferenceDTO.builder()
                        .index("skill-perception")
                        .name("Skill: Perception")
                        .url("/api/proficiencies/skill-perception")
                        .build()))
                .proficiency_choices(OptionSetDTO.builder()
                        .choose(1)
                        .type(OptionType.proficiencies)
                        .from(FromOptionSetDTO.builder()
                                .options(Collections.singletonList(EntityReferenceOptionDTO.builder()
                                        .item(EntityReferenceDTO.builder()
                                                .index("skill-perception")
                                                .name("Skill: Perception")
                                                .url("/api/proficiencies/skill-perception")
                                                .build())
                                        .bonus(1)
                                        .build()))
                                .build())
                        .build())
                .language_options(OptionSetDTO.builder()
                        .choose(1)
                        .type(OptionType.language)
                        .from(FromOptionSetDTO.builder()
                                .options(Collections.singletonList(EntityReferenceOptionDTO.builder()
                                        .item(EntityReferenceDTO.builder()
                                                .index("common")
                                                .name("Common")
                                                .url("/api/languages/common")
                                                .build())
                                        .bonus(1)
                                        .build()))
                                .build())
                        .build())
                .url("/api/traits/high-elf-cantrip")
                .build();

    }

    @Test
    void findAllTraits() throws Exception {
        String traitResponseJson = objectMapper.writeValueAsString(traitResponse);

        when(traitService.findAllTraits()).thenReturn(traitResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(traitResponseJson))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void findTraitByIndexName() throws Exception {
        String traitDTOJson = objectMapper.writeValueAsString(traitDTO);

        when(traitService.findTraitByIndexName(traitDTO.index())).thenReturn(traitDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + traitDTO.index())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(traitDTOJson))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cannotFindAllTraits() throws Exception {
        when(traitService.findAllTraits())
                .thenThrow(new TraitNotFoundException("No traits found."));

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("No traits found.")))
                .andExpect(jsonPath("$.path", is(PATH)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cannotFindTraitByIndexName() throws Exception {
        when(traitService.findTraitByIndexName(traitDTO.index()))
                .thenThrow(new TraitNotFoundException(String.format("Trait with index name '%s' not found.", traitDTO.index())));

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + traitDTO.index())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message",
                        is(String.format("Trait with index name '%s' not found.", traitDTO.index()))))
                .andExpect(jsonPath("$.path", is(PATH + "/" + traitDTO.index())))
                .andDo(MockMvcResultHandlers.print());
    }
}
