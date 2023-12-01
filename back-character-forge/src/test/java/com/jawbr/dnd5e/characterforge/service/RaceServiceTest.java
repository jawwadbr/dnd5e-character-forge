package com.jawbr.dnd5e.characterforge.service;

import com.jawbr.dnd5e.characterforge.dto.mapper.race.RaceDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.mapper.race.RaceDTOResponseMapper;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceOptionDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.FromOptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.OptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.race.RaceAbilityBonusesDTO;
import com.jawbr.dnd5e.characterforge.dto.response.race.RaceDTO;
import com.jawbr.dnd5e.characterforge.exception.RaceNotFoundException;
import com.jawbr.dnd5e.characterforge.model.entity.AbilityScore;
import com.jawbr.dnd5e.characterforge.model.entity.AbilityScoreBonusesOption;
import com.jawbr.dnd5e.characterforge.model.entity.Language;
import com.jawbr.dnd5e.characterforge.model.entity.LanguageOption;
import com.jawbr.dnd5e.characterforge.model.entity.Proficiency;
import com.jawbr.dnd5e.characterforge.model.entity.Race;
import com.jawbr.dnd5e.characterforge.model.entity.RaceAbilityScoreBonus;
import com.jawbr.dnd5e.characterforge.model.entity.StartingProficiencyOption;
import com.jawbr.dnd5e.characterforge.model.entity.SubRace;
import com.jawbr.dnd5e.characterforge.model.entity.Trait;
import com.jawbr.dnd5e.characterforge.model.entity.TypicalSpeakers;
import com.jawbr.dnd5e.characterforge.model.util.LanguageType;
import com.jawbr.dnd5e.characterforge.model.util.OptionType;
import com.jawbr.dnd5e.characterforge.model.util.Size;
import com.jawbr.dnd5e.characterforge.repository.RaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class RaceServiceTest {

    @InjectMocks
    private RaceService raceService;

    @Mock
    private RaceRepository raceRepository;

    @Mock
    private RaceDTOMapper raceDTOMapper;

    @Mock
    private RaceDTOResponseMapper raceDTOResponseMapper;

    private Race race;
    private FindAllDTOResponse raceFindAllDTOResponse;
    private RaceDTO raceDTO;

    @BeforeEach
    void init() {

        race = Race.builder()
                .indexName("elf")
                .raceName("Elf")
                .speed(30)
                .abilityBonuses(Collections.singletonList(RaceAbilityScoreBonus.builder()
                        .abilityScore(AbilityScore.builder()
                                .indexName("dex")
                                .fullName("Dexterity")
                                .shortName("DEX")
                                .desc("desc")
                                .url("/api/ability-scores/dex")
                                .build())
                        .bonus(1)
                        .race(race)
                        .build()))
                .abilityBonusesOptions(AbilityScoreBonusesOption.builder()
                        .type(OptionType.ability_bonuses)
                        .choose(1)
                        .from(List.of(AbilityScore.builder()
                                .indexName("dex")
                                .fullName("Dexterity")
                                .shortName("DEX")
                                .desc("desc")
                                .url("/api/ability-scores/dex")
                                .build()))
                        .build())
                .alignment("alignment")
                .age_desc("age desc")
                .size(Size.Medium)
                .size_desc("size desc")
                .proficiencies(Collections.singletonList(Proficiency.builder()
                        .indexName("skill-perception")
                        .name("Skill: Perception")
                        .url("/api/proficiencies/skill-perception")
                        .build()))
                .proficiencyOptions(StartingProficiencyOption.builder()
                        .type(OptionType.proficiencies)
                        .choose(1)
                        .from(List.of(Proficiency.builder()
                                .indexName("skill-perception")
                                .name("Skill: Perception")
                                .url("/api/proficiencies/skill-perception")
                                .build()))
                        .build())
                .languages(Collections.singletonList(Language.builder()
                        .indexName("elvish")
                        .name("Elvish")
                        .url("/api/languages/elvish")
                        .build()))
                .language_desc("language desc")
                .languageOptions(LanguageOption.builder()
                        .choose(1)
                        .type(OptionType.language)
                        .from(List.of(Language.builder()
                                .indexName("common")
                                .name("Common")
                                .language_desc("desc")
                                .type(LanguageType.Standard)
                                .script("Common")
                                .url("/api/languages/common")
                                .typicalSpeakers(List.of(TypicalSpeakers.builder()
                                        .speakerName("Human")
                                        .build()))
                                .build()))
                        .build())
                .traits(Collections.singletonList(Trait.builder()
                        .indexName("extra-language")
                        .traitName("Extra Language")
                        .url("/api/traits/extra-language")
                        .build()))
                .subRaces(Collections.singletonList(SubRace.builder()
                        .indexName("high-elf")
                        .subRaceName("High Elf")
                        .url("/api/subraces/high-elf")
                        .build()))
                .url("/api/races/elf")
                .build();

        //

        RaceAbilityBonusesDTO raceAbilityBonusesDTO = RaceAbilityBonusesDTO.builder()
                .bonus(2)
                .ability_score(EntityReferenceDTO.builder()
                        .index("dex")
                        .name("DEX")
                        .url("/api/ability-scores/dex")
                        .build())
                .build();
        List<RaceAbilityBonusesDTO> abBonusList = new ArrayList<>();
        abBonusList.add(raceAbilityBonusesDTO);

        raceDTO = RaceDTO.builder()
                .index(race.getIndexName())
                .name(race.getRaceName())
                .speed(race.getSpeed())
                .ability_bonuses(abBonusList)
                .ability_bonus_options(OptionSetDTO.builder()
                        .choose(2)
                        .type(OptionType.ability_bonuses)
                        .from(FromOptionSetDTO.builder()
                                .options(Collections.singletonList(EntityReferenceOptionDTO.builder()
                                        .ability_score(EntityReferenceDTO.builder()
                                                .index("dex")
                                                .name("DEX")
                                                .url("/api/ability-scores/dex")
                                                .build())
                                        .bonus(1)
                                        .build()))
                                .build())
                        .build())
                .alignment(race.getAlignment())
                .age(race.getAge_desc())
                .size(race.getSize())
                .size_description(race.getSize_desc())
                .starting_proficiencies(Collections.singletonList(EntityReferenceDTO.builder()
                        .index("skill-perception")
                        .name("Skill: Perception")
                        .url("/api/proficiencies/skill-perception")
                        .build()))
                .starting_proficiency_options(OptionSetDTO.builder()
                        .choose(2)
                        .type(OptionType.proficiencies)
                        .from(FromOptionSetDTO.builder()
                                .options(Collections.singletonList(EntityReferenceOptionDTO.builder()
                                        .item(EntityReferenceDTO.builder()
                                                .index("skill-perception")
                                                .name("Skill: Perception")
                                                .url("/api/proficiencies/skill-perception")
                                                .build())
                                        .build()))
                                .build())
                        .build())
                .languages(Collections.singletonList(EntityReferenceDTO.builder()
                        .index("elvish")
                        .name("Elvish")
                        .url("/api/languages/elvish")
                        .build()))
                .language_desc(race.getLanguage_desc())
                .language_options(OptionSetDTO.builder()
                        .choose(1)
                        .type(OptionType.language)
                        .from(FromOptionSetDTO.builder()
                                .options(Collections.singletonList(EntityReferenceOptionDTO.builder()
                                        .item(EntityReferenceDTO.builder()
                                                .index("common")
                                                .name("Common")
                                                .url("/api/languages/common")
                                                .build())
                                        .bonus(1)
                                        .build()))
                                .build())
                        .build())
                .traits(Collections.singletonList(EntityReferenceDTO.builder()
                        .index("extra-language")
                        .name("Extra Language")
                        .url("/api/traits/extra-language")
                        .build()))
                .subraces(Collections.singletonList(EntityReferenceDTO.builder()
                        .index("high-elf")
                        .name("High Elf")
                        .url("/api/subraces/high-elf")
                        .build()))
                .url(race.getUrl())
                .build();

        EntityReferenceDTO raceForFindAll = EntityReferenceDTO.builder()
                .name(raceDTO.name())
                .index(raceDTO.index())
                .url(raceDTO.url())
                .build();

        List<EntityReferenceDTO> raceList = new ArrayList<>();
        raceList.add(raceForFindAll);

        raceFindAllDTOResponse = FindAllDTOResponse.builder()
                .count(raceList.size())
                .results(raceList)
                .build();
    }

    @Test
    void findAllRaces() {
        List<Race> raceList = new ArrayList<>();
        raceList.add(race);

        FindAllDTOResponse expected = raceFindAllDTOResponse;

        when(raceRepository.findAll()).thenReturn(raceList);
        when(raceDTOResponseMapper.apply(raceList)).thenReturn(raceFindAllDTOResponse);

        FindAllDTOResponse result = raceService.findAllRaces();

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void findRaceByIndexName() {
        RaceDTO expected = raceDTO;

        when(raceRepository.findByIndexName(race.getIndexName())).thenReturn(race);
        when(raceDTOMapper.apply(race)).thenReturn(raceDTO);

        RaceDTO result = raceService.findRaceByIndexName(race.getIndexName());

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void cannotFindAllRaces() {
        List<Race> raceList = new ArrayList<>();
        when(raceRepository.findAll()).thenReturn(raceList);

        assertThrows(RaceNotFoundException.class,
                () -> raceService.findAllRaces(),
                "No races found.");
    }

    @Test
    void cannotFindRaceByIndexName() {
        when(raceRepository.findByIndexName(race.getIndexName())).thenReturn(null);

        assertThrows(RaceNotFoundException.class,
                () -> raceService.findRaceByIndexName(race.getIndexName()),
                String.format("Race with index name '%s' not found.", race.getIndexName()));
    }
}
