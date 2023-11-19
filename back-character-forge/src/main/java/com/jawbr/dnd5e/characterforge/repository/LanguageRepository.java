package com.jawbr.dnd5e.characterforge.repository;

import com.jawbr.dnd5e.characterforge.model.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {

    Language findByIndexName(String indexName);
}
