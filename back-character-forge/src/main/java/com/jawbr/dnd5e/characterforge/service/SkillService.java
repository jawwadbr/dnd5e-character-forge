package com.jawbr.dnd5e.characterforge.service;

import com.github.slugify.Slugify;
import com.jawbr.dnd5e.characterforge.dto.mapper.skill.SkillDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.mapper.skill.SkillDTOResponseMapper;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.exception.SkillNotFoundException;
import com.jawbr.dnd5e.characterforge.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
     * Method to find all Skills inside the database
     *
     * @return a List containing all Skills mapped into SkillDTO
     * @throws SkillNotFoundException when no skills are found inside the database
     * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
     */
    public FindAllDTOResponse findAllSkills() {
        return Optional.of(skillRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(skillDTOResponseMapper)
                .orElseThrow(() -> new SkillNotFoundException("No skills found."));
    }
}
