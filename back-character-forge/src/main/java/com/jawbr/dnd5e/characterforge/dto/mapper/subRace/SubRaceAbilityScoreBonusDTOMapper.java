package com.jawbr.dnd5e.characterforge.dto.mapper.subRace;

import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceAbilityScoreBonusDTO;
import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceAbilityScoreDTO;
import com.jawbr.dnd5e.characterforge.model.entity.SubRaceAbilityScoreBonus;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SubRaceAbilityScoreBonusDTOMapper implements Function<SubRaceAbilityScoreBonus, SubRaceAbilityScoreBonusDTO> {

    @Override
    public SubRaceAbilityScoreBonusDTO apply(SubRaceAbilityScoreBonus subRaceAbilityScoreBonus) {
        return new SubRaceAbilityScoreBonusDTO(
                SubRaceAbilityScoreDTO.builder()
                        .index(subRaceAbilityScoreBonus.getAbilityScore().getIndexName())
                        .name(subRaceAbilityScoreBonus.getAbilityScore().getShortName())
                        .url(subRaceAbilityScoreBonus.getAbilityScore().getUrl())
                        .build(),
                subRaceAbilityScoreBonus.getBonus()
        );
    }
}
