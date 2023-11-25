package com.jawbr.dnd5e.characterforge.controller;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TraitController.class)
@AutoConfigureMockMvc
public class TraitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TraitService traitService;

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
        when(traitService.findAllTraits()).thenReturn(traitResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count", is(traitResponse.count())))
                .andExpect(jsonPath("$.results").isArray())
                .andExpect(jsonPath("$.results[0].index", is(traitsForFindAll.get(0).index())))
                .andExpect(jsonPath("$.results[0].name", is(traitsForFindAll.get(0).name())))
                .andExpect(jsonPath("$.results[0].url", is(traitsForFindAll.get(0).url())))
                .andExpect(jsonPath("$.results[1].index", is(traitsForFindAll.get(1).index())))
                .andExpect(jsonPath("$.results[1].name", is(traitsForFindAll.get(1).name())))
                .andExpect(jsonPath("$.results[1].url", is(traitsForFindAll.get(1).url())))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void findTraitByIndexName() throws Exception {
        when(traitService.findTraitByIndexName(traitDTO.index())).thenReturn(traitDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + traitDTO.index())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.index", is(traitDTO.index())))
                .andExpect(jsonPath("$.races[0].index",
                        is(traitDTO.races().get(0).index())))
                .andExpect(jsonPath("$.races[0].name",
                        is(traitDTO.races().get(0).name())))
                .andExpect(jsonPath("$.races[0].url",
                        is(traitDTO.races().get(0).url())))
                .andExpect(jsonPath("$.subraces[0].index",
                        is(traitDTO.subraces().get(0).index())))
                .andExpect(jsonPath("$.subraces[0].name",
                        is(traitDTO.subraces().get(0).name())))
                .andExpect(jsonPath("$.subraces[0].url",
                        is(traitDTO.subraces().get(0).url())))
                .andExpect(jsonPath("$.name", is(traitDTO.name())))
                .andExpect(jsonPath("$.desc", is(traitDTO.desc())))
                .andExpect(jsonPath("$.proficiencies[0].index",
                        is(traitDTO.proficiencies().get(0).index())))
                .andExpect(jsonPath("$.proficiencies[0].name",
                        is(traitDTO.proficiencies().get(0).name())))
                .andExpect(jsonPath("$.proficiencies[0].url",
                        is(traitDTO.proficiencies().get(0).url())))
                .andExpect(jsonPath("$.proficiency_choices.choose",
                        is(traitDTO.proficiency_choices().choose())))
                .andExpect(jsonPath("$.proficiency_choices.type",
                        is(traitDTO.proficiency_choices().type().name())))
                .andExpect(jsonPath("$.proficiency_choices.from.options[0].item.index",
                        is(traitDTO.proficiency_choices().from().options().get(0).item().index())))
                .andExpect(jsonPath("$.proficiency_choices.from.options[0].item.name",
                        is(traitDTO.proficiency_choices().from().options().get(0).item().name())))
                .andExpect(jsonPath("$.proficiency_choices.from.options[0].item.url",
                        is(traitDTO.proficiency_choices().from().options().get(0).item().url())))
                .andExpect(jsonPath("$.language_options.choose",
                        is(traitDTO.language_options().choose())))
                .andExpect(jsonPath("$.language_options.type",
                        is(traitDTO.language_options().type().name())))
                .andExpect(jsonPath("$.language_options.from.options[0].item.index",
                        is(traitDTO.language_options().from().options().get(0).item().index())))
                .andExpect(jsonPath("$.language_options.from.options[0].item.name",
                        is(traitDTO.language_options().from().options().get(0).item().name())))
                .andExpect(jsonPath("$.language_options.from.options[0].item.url",
                        is(traitDTO.language_options().from().options().get(0).item().url())))
                .andExpect(jsonPath("$.url", is(traitDTO.url())))
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
