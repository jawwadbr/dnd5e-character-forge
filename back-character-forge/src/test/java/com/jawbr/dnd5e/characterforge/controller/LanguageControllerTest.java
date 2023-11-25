package com.jawbr.dnd5e.characterforge.controller;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.language.LanguageDTO;
import com.jawbr.dnd5e.characterforge.exception.LanguageNotFoundException;
import com.jawbr.dnd5e.characterforge.model.util.LanguageType;
import com.jawbr.dnd5e.characterforge.service.LanguageService;
import org.hamcrest.Matchers;
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

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LanguageController.class)
@AutoConfigureMockMvc
class LanguageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LanguageService languageService;

    private final String PATH = "/api/languages";

    private EntityReferenceDTO languageForFindAll;
    private FindAllDTOResponse languageResponse;
    private LanguageDTO languageDTO;

    @BeforeEach
    public void init() {
        languageDTO = LanguageDTO.builder()
                .index("common")
                .name("Common")
                .desc("desc")
                .type(LanguageType.Standard)
                .script("Common")
                .typical_speakers(List.of("Human"))
                .url("/api/languages/common")
                .build();

        languageForFindAll = EntityReferenceDTO.builder()
                .index(languageDTO.index())
                .name(languageDTO.name())
                .url(languageDTO.url())
                .build();

        List<EntityReferenceDTO> list = new ArrayList<>();
        list.add(languageForFindAll);

        languageResponse = FindAllDTOResponse.builder()
                .count(list.size())
                .results(list)
                .build();
    }

    @Test
    void findAllLanguages() throws Exception {
        when(languageService.findAllLanguages()).thenReturn(languageResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count", is(languageResponse.count())))
                .andExpect(jsonPath("$.results[0].index", Matchers.is(languageForFindAll.index())))
                .andExpect(jsonPath("$.results[0].name", Matchers.is(languageForFindAll.name())))
                .andExpect(jsonPath("$.results[0].url", Matchers.is(languageForFindAll.url())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void findLanguageByIndexName() throws Exception {

        when(languageService.findLanguageByIndexName(languageDTO.index())).thenReturn(languageDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + languageDTO.index())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.index", is(languageDTO.index())))
                .andExpect(jsonPath("$.name", is(languageDTO.name())))
                .andExpect(jsonPath("$.desc", is(languageDTO.desc())))
                .andExpect(jsonPath("$.type", is(languageDTO.type().name())))
                .andExpect(jsonPath("$.script", is(languageDTO.script())))
                .andExpect(jsonPath("$.typical_speakers").isArray())
                .andExpect(jsonPath("$.typical_speakers[0]", is(languageDTO.typical_speakers().get(0))))
                .andExpect(jsonPath("$.url", is(languageDTO.url())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cannotFindAllLanguages() throws Exception {
        when(languageService.findAllLanguages())
                .thenThrow(new LanguageNotFoundException("No languages found."));

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("No languages found.")))
                .andExpect(jsonPath("$.path", Matchers.is(PATH)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cannotFindLanguageByIndexName() throws Exception {
        when(languageService.findLanguageByIndexName(languageDTO.index()))
                .thenThrow(new LanguageNotFoundException(String.format("Language with index name '%s' not found.", languageDTO.index())));

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + languageDTO.index())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is(String.format("Language with index name '%s' not found.", languageDTO.index()))))
                .andExpect(jsonPath("$.path", Matchers.is(PATH + "/" + languageDTO.index())))
                .andDo(MockMvcResultHandlers.print());
    }

}
