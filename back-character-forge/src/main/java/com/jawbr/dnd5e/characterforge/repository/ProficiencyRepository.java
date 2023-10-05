package com.jawbr.dnd5e.characterforge.repository;

import com.jawbr.dnd5e.characterforge.model.entity.Proficiency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProficiencyRepository extends JpaRepository<Proficiency, Integer> {
}
