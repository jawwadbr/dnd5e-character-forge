package com.jawbr.dnd5e.characterforge.service;

import com.jawbr.dnd5e.characterforge.dto.mapper.language.LanguageDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.response.language.LanguageDTO;
import com.jawbr.dnd5e.characterforge.exception.LanguageNotFoundException;
import com.jawbr.dnd5e.characterforge.model.entity.Language;
import com.jawbr.dnd5e.characterforge.model.util.LanguageType;
import com.jawbr.dnd5e.characterforge.repository.LanguageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class LanguageServiceTest {

    @InjectMocks
    private LanguageService languageService;

    @Mock
    private LanguageRepository languageRepository;

    @Mock
    private LanguageDTOMapper languageDTOMapper;

    private Language language;
    private LanguageDTO languageDTO;

    @BeforeEach
    void init() {
        language = Language.builder()
                .id(1)
                .indexName("indexL")
                .name("nameL")
                .language_desc("langDesc")
                .type(LanguageType.Standard)
                .script("scriptL")
                .url("urlL")
                .build();

        languageDTO = LanguageDTO.builder()
                .index(language.getIndexName())
                .name(language.getName())
                .desc(language.getLanguage_desc())
                .type(language.getType())
                .script(language.getScript())
                .url(language.getUrl())
                .build();
    }

    @Test
    void findAllLanguages() {
        List<Language> languageList = new ArrayList<>();
        languageList.add(language);

        List<LanguageDTO> expected = new ArrayList<>();
        expected.add(languageDTO);

        when(languageRepository.findAll()).thenReturn(languageList);
        when(languageDTOMapper.apply(language)).thenReturn(languageDTO);

        List<LanguageDTO> result = languageService.findAllLanguages();

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void cannotFindAllLanguages() {
        List<Language> languageList = new ArrayList<>();
        when(languageRepository.findAll()).thenReturn(languageList);

        assertThrows(LanguageNotFoundException.class,
                () -> languageService.findAllLanguages(),
                "No languages found.");
    }
}