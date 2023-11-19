package com.jawbr.dnd5e.characterforge.controller;

import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.proficiency.ProficiencyDTO;
import com.jawbr.dnd5e.characterforge.service.ProficiencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/proficiencies")
public class ProficiencyController {

    private final ProficiencyService proficiencyService;

    public ProficiencyController(ProficiencyService proficiencyService) {
        this.proficiencyService = proficiencyService;
    }

    @GetMapping
    public FindAllDTOResponse findAllProficiencies() {
        return proficiencyService.findAllProficiencies();
    }

    @GetMapping("/{indexName}")
    public ProficiencyDTO findProficiencyByIndexName(@PathVariable String indexName) {
        return proficiencyService.findByIndexName(indexName);
    }
}
