package com.jawbr.dnd5e.characterforge.repository;

import com.jawbr.dnd5e.characterforge.model.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
