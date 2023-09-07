package com.jawbr.dnd5e.characterforge.service;

import com.github.slugify.Slugify;
import com.jawbr.dnd5e.characterforge.dto.mapper.skill.SkillDTOMapper;
import com.jawbr.dnd5e.characterforge.repository.SkillRepository;
import com.jawbr.dnd5e.characterforge.dto.response.skill.SkillDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final SkillDTOMapper skillDTOMapper;
    private final Slugify slugify;

    private static final String URL = "/api/skills/";

    public SkillService(SkillRepository skillRepository, SkillDTOMapper skillDTOMapper) {
        this.skillRepository = skillRepository;
        this.skillDTOMapper = skillDTOMapper;
        this.slugify = Slugify.builder().build();
    }

    public List<SkillDTO> findAllSkills() {
        return Optional.of(skillRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream().map(skillDTOMapper).toList())
                .orElseThrow(() -> new RuntimeException("No skills found!"));
    }
}
