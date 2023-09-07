package com.jawbr.dnd5e.characterforge.repository;

import com.jawbr.dnd5e.characterforge.model.entity.AbilityScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbilityScoreRepository extends JpaRepository<AbilityScore, Integer> {
}
