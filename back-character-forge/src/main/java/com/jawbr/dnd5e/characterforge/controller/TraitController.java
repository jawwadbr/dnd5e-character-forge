package com.jawbr.dnd5e.characterforge.controller;

import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.trait.TraitDTO;
import com.jawbr.dnd5e.characterforge.service.TraitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/traits")
public class TraitController {

    private final TraitService traitService;

    public TraitController(TraitService traitService) {
        this.traitService = traitService;
    }

    @GetMapping
    public FindAllDTOResponse findAllTraits() {
        return traitService.findAllTraits();
    }

    @GetMapping("/{indexName}")
    public TraitDTO findTraitByIndexName(@PathVariable String indexName) {
        return traitService.findTraitByIndexName(indexName);
    }
}
