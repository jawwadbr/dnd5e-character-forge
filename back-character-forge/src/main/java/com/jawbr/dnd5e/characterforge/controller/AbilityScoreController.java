package com.jawbr.dnd5e.characterforge.controller;

import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.abilityScore.AbilityScoreDTO;
import com.jawbr.dnd5e.characterforge.service.AbilityScoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ability-scores")
public class AbilityScoreController {

    private final AbilityScoreService abilityScoreService;

    public AbilityScoreController(AbilityScoreService abilityScoreService) {
        this.abilityScoreService = abilityScoreService;
    }

    @GetMapping
    public FindAllDTOResponse findAllAbilityScores() {
        return abilityScoreService.findAllAbilityScores();
    }

    @GetMapping("/{indexName}")
    public AbilityScoreDTO findAbilityScoreByIndexName(@PathVariable String indexName) {
        return abilityScoreService.findAbilityScoresByIndexName(indexName);
    }
}
