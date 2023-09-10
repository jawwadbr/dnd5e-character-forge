package com.jawbr.dnd5e.characterforge.service;

import com.github.slugify.Slugify;
import com.jawbr.dnd5e.characterforge.dto.mapper.language.LanguageDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.response.language.LanguageDTO;
import com.jawbr.dnd5e.characterforge.exception.LanguageNotFoundException;
import com.jawbr.dnd5e.characterforge.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final LanguageDTOMapper languageDTOMapper;
    private final Slugify slugify;

    private static final String URL = "/api/languages/";

    public LanguageService(LanguageRepository languageRepository,
                           LanguageDTOMapper languageDTOMapper)
    {
        this.languageRepository = languageRepository;
        this.languageDTOMapper = languageDTOMapper;
        this.slugify = Slugify.builder().build();
    }

    /**
     * Method to find all languages inside the database
     *
     * @return a List containing all languages mapped into LanguageDTO
     * @throws LanguageNotFoundException when no languages are found inside the database
     * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
     */
    public List<LanguageDTO> findAllLanguages() {
        return Optional.of(languageRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream().map(languageDTOMapper).toList())
                .orElseThrow(() -> new LanguageNotFoundException("No languages found."));
    }
}
