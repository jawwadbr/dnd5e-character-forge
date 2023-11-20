package com.jawbr.dnd5e.characterforge.dto.mapper.trait;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.trait.TraitDTO;
import com.jawbr.dnd5e.characterforge.model.entity.Proficiency;
import com.jawbr.dnd5e.characterforge.model.entity.Race;
import com.jawbr.dnd5e.characterforge.model.entity.SubRace;
import com.jawbr.dnd5e.characterforge.model.entity.Trait;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class TraitDTOMapper implements Function<Trait, TraitDTO> {

    @Override
    public TraitDTO apply(Trait trait) {

        List<EntityReferenceDTO> subRacesList = new ArrayList<>();
        for(SubRace subRace : trait.getSubRaces()) {
            subRacesList.add(EntityReferenceDTO.builder()
                    .name(subRace.getSubRaceName())
                    .index(subRace.getIndexName())
                    .url(subRace.getUrl())
                    .build());
        }

        List<EntityReferenceDTO> raceList = new ArrayList<>();
        for(Race race : trait.getRaces()) {
            raceList.add(EntityReferenceDTO.builder()
                    .name(race.getRaceName())
                    .index(race.getIndexName())
                    .url(race.getUrl())
                    .build());
        }

        List<EntityReferenceDTO> proficiencyList = new ArrayList<>();
        for(Proficiency proficiency : trait.getProficiencies()) {
            proficiencyList.add(EntityReferenceDTO.builder()
                    .name(proficiency.getName())
                    .index(proficiency.getIndexName())
                    .url(proficiency.getUrl())
                    .build());
        }

        return TraitDTO.builder()
                .name(trait.getTraitName())
                .index(trait.getIndexName())
                .desc(trait.getDesc())
                .url(trait.getUrl())
                .subraces(subRacesList)
                .races(raceList)
                .proficiencies(proficiencyList)
                .build();
    }
}
