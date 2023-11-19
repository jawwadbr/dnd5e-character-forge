package com.jawbr.dnd5e.characterforge.dto.mapper.skill;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.skill.SkillDTO;
import com.jawbr.dnd5e.characterforge.model.entity.AbilityScore;
import com.jawbr.dnd5e.characterforge.model.entity.Skill;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Method to map {@link Skill} entity to {@link SkillDTO}
 *
 * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
 */
@Service
public class SkillDTOMapper implements Function<Skill, SkillDTO> {
    @Override
    public SkillDTO apply(Skill skill) {
        return new SkillDTO(
                skill.getIndexName(),
                skill.getName(),
                skill.getSkillDesc(),
                EntityReferenceDTO.builder()
                        .index(skill.getAbilityScore().getIndexName())
                        .name(skill.getAbilityScore().getFullName())
                        .url(skill.getAbilityScore().getUrl())
                        .build(),
                skill.getUrl()
        );
    }

}
