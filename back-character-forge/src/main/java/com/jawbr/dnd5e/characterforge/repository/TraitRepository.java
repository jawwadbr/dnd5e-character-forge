package com.jawbr.dnd5e.characterforge.repository;

import com.jawbr.dnd5e.characterforge.model.entity.Trait;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraitRepository extends JpaRepository<Trait, Integer> {

    Trait findByIndexName(String indexName);
}
