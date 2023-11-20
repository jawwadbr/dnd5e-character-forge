package com.jawbr.dnd5e.characterforge.service;

import com.jawbr.dnd5e.characterforge.dto.mapper.trait.TraitDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.mapper.trait.TraitDTOResponseMapper;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.trait.TraitDTO;
import com.jawbr.dnd5e.characterforge.exception.TraitNotFoundException;
import com.jawbr.dnd5e.characterforge.repository.TraitRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TraitService {

    private final TraitRepository traitRepository;
    private final TraitDTOMapper traitDTOMapper;
    private final TraitDTOResponseMapper traitDTOResponseMapper;

    private static final String URL = "/api/traits/";

    public TraitService(TraitRepository traitRepository, TraitDTOMapper traitDTOMapper, TraitDTOResponseMapper traitDTOResponseMapper) {
        this.traitRepository = traitRepository;
        this.traitDTOMapper = traitDTOMapper;
        this.traitDTOResponseMapper = traitDTOResponseMapper;
    }

    /**
     * Method to find all traits inside the database
     *
     * @return a List containing all traits mapped into FindAllDTOResponse
     * @throws TraitNotFoundException when no traits are found inside the database
     * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
     */
    public FindAllDTOResponse findAllTraits() {
        return Optional.of(traitRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(traitDTOResponseMapper)
                .orElseThrow(() -> new TraitNotFoundException("No traits found."));
    }

    /**
     * Method to trait inside the database using Index Name
     *
     * @return a trait mapped into FindAllDTOResponse
     * @throws TraitNotFoundException when no trait is found inside the database
     * @author <a href="https://www.linkedin.com/in/bradley-sperling/">Bradley Jawwad</a>
     */
    public TraitDTO findTraitByIndexName(String indexName) {
        return Optional.ofNullable(traitRepository.findByIndexName(indexName))
                .map(traitDTOMapper)
                .orElseThrow(() -> new TraitNotFoundException(
                        String.format("Trait with index name '%s' not found.", indexName)));
    }
}
