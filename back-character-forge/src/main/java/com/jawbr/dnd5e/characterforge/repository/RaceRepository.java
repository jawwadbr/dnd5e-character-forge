package com.jawbr.dnd5e.characterforge.repository;

import com.jawbr.dnd5e.characterforge.model.entity.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<Race, Integer> {

    Race findByIndexName(String indexName);
}
