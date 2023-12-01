package com.jawbr.dnd5e.characterforge.service;

import com.jawbr.dnd5e.characterforge.dto.mapper.language.LanguageDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.mapper.language.LanguageDTOResponseMapper;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.language.LanguageDTO;
import com.jawbr.dnd5e.characterforge.exception.LanguageNotFoundException;
import com.jawbr.dnd5e.characterforge.model.entity.Language;
import com.jawbr.dnd5e.characterforge.model.entity.TypicalSpeakers;
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

    @Mock
    private LanguageDTOResponseMapper languageDTOResponseMapper;

    private Language language;
    private FindAllDTOResponse languageFindAllDTOResponse;
    private LanguageDTO languageDTO;

    @BeforeEach
    void init() {
        language = Language.builder()
                .indexName("common")
                .name("Common")
                .language_desc("desc")
                .type(LanguageType.Standard)
                .script("Common")
                .url("/api/languages/common")
                .typicalSpeakers(List.of(TypicalSpeakers.builder()
                        .speakerName("Human")
                        .build()))
                .build();

        languageDTO = LanguageDTO.builder()
                .index(language.getIndexName())
                .name(language.getName())
                .desc(language.getLanguage_desc())
                .type(language.getType())
                .script(language.getScript())
                .url(language.getUrl())
                .typical_speakers(List.of(language.getTypicalSpeakers().get(0).getSpeakerName()))
                .build();

        EntityReferenceDTO languageEntityReferenceDTO = EntityReferenceDTO.builder()
                .index("common")
                .name("Common")
                .url("/api/languages/common")
                .build();

        List<EntityReferenceDTO> languageEntityReferenceDTOS = new ArrayList<>();
        languageEntityReferenceDTOS.add(languageEntityReferenceDTO);

        languageFindAllDTOResponse = FindAllDTOResponse.builder()
                .count(languageEntityReferenceDTOS.size())
                .results(languageEntityReferenceDTOS)
                .build();

    }

    @Test
    void findAllLanguages() {
        List<Language> languageList = new ArrayList<>();
        languageList.add(language);

        FindAllDTOResponse expected = languageFindAllDTOResponse;

        when(languageRepository.findAll()).thenReturn(languageList);
        when(languageDTOResponseMapper.apply(languageList)).thenReturn(languageFindAllDTOResponse);

        FindAllDTOResponse result = languageService.findAllLanguages();

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void findLanguageByIndexName() {
        LanguageDTO expected = languageDTO;

        when(languageRepository.findByIndexName(language.getIndexName())).thenReturn(language);
        when(languageDTOMapper.apply(language)).thenReturn(languageDTO);

        LanguageDTO result = languageService.findLanguageByIndexName(language.getIndexName());

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

    @Test
    void cannotFindLanguageByIndexName() {
        when(languageRepository.findByIndexName(language.getIndexName())).thenReturn(null);

        assertThrows(LanguageNotFoundException.class,
                () -> languageService.findLanguageByIndexName(language.getIndexName()),
                String.format("Language with index name '%s' not found.", language.getIndexName()));
    }
}