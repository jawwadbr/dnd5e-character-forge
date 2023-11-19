package com.jawbr.dnd5e.characterforge.controller;

import com.jawbr.dnd5e.characterforge.dto.response.language.LanguageDTO;
import com.jawbr.dnd5e.characterforge.exception.LanguageNotFoundException;
import com.jawbr.dnd5e.characterforge.model.util.LanguageType;
import com.jawbr.dnd5e.characterforge.service.LanguageService;
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

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@WebMvcTest(LanguageController.class)
@AutoConfigureMockMvc
class LanguageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LanguageService languageService;

    private final String PATH = "/api/languages";

    private LanguageDTO languageDTO;

    @BeforeEach
    public void init() {
        languageDTO = LanguageDTO.builder()
                .index("lang index")
                .name("lang name")
                .desc("desc")
                .type(LanguageType.Standard)
                .script("script type")
                .url("url")
                .build();
    }

    @Test
    void findAllLanguages() throws Exception {
        List<LanguageDTO> languageDTOList = Collections.singletonList(languageDTO);

        //when(languageService.findAllLanguages()).thenReturn(languageDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].index", is(languageDTO.index())))
                .andExpect(jsonPath("$[0].name", is(languageDTO.name())))
                .andExpect(jsonPath("$[0].desc", is(languageDTO.desc())))
                .andExpect(jsonPath("$[0].type", is(languageDTO.type().name())))
                .andExpect(jsonPath("$[0].script", is(languageDTO.script())))
                .andExpect(jsonPath("$[0].url", is(languageDTO.url())))
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
                .andDo(MockMvcResultHandlers.print());
    }

}
