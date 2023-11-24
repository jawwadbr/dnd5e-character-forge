package com.jawbr.dnd5e.characterforge.service;

import com.github.slugify.Slugify;
import com.jawbr.dnd5e.characterforge.dto.mapper.skill.SkillDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.mapper.skill.SkillDTOResponseMapper;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.skill.SkillDTO;
import com.jawbr.dnd5e.characterforge.exception.SkillNotFoundException;
import com.jawbr.dnd5e.characterforge.model.entity.Language;
import com.jawbr.dnd5e.characterforge.model.entity.Skill;
import com.jawbr.dnd5e.characterforge.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final SkillDTOMapper skillDTOMapper;
    private final SkillDTOResponseMapper skillDTOResponseMapper;
    private final Slugify slugify;

    private static final String URL = "/api/skills/";

    public SkillService(SkillRepository skillRepository,
                        SkillDTOMapper skillDTOMapper, SkillDTOResponseMapper skillDTOResponseMapper)
    {
        this.skillRepository = skillRepository;
        this.skillDTOMapper = skillDTOMapper;
        this.skillDTOResponseMapper = skillDTOResponseMapper;
        this.slugify = Slugify.builder().build();
    }

    /**
     * Method to find all Skills inside the database in alphabetical order
     *
     * @return a List containing all Skills mapped into SkillDTO
     * @throws SkillNotFoundException when no skills are found inside the database
     * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
     */
    public FindAllDTOResponse findAllSkills() {
        return Optional.of(skillRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream()
                        .sorted(Comparator.comparing(Skill::getName))
                        .collect(Collectors.toList()))
                .map(skillDTOResponseMapper)
                .orElseThrow(() -> new SkillNotFoundException("No skills found."));
    }

    /**
     * Method to find Skill inside the database using Index Name
     *
     * @return a Skill mapped into SkillDTO
     * @throws SkillNotFoundException when no skill is found inside the database
     * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
     */
    public SkillDTO findSkillByIndexName(String indexName) {
        return Optional.ofNullable(skillRepository.findByIndexName(indexName))
                .map(skillDTOMapper)
                .orElseThrow(() -> new SkillNotFoundException(
                        String.format("Skill with index name '%s' not found.", indexName)));
    }
}
