package com.jawbr.dnd5e.characterforge.service;

import com.jawbr.dnd5e.characterforge.dto.mapper.subRace.SubRaceDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.mapper.subRace.SubRaceDTOResponseMapper;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceOptionDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.FromOptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.OptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceAbilityScoreBonusDTO;
import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceDTO;
import com.jawbr.dnd5e.characterforge.exception.RaceNotFoundException;
import com.jawbr.dnd5e.characterforge.model.entity.AbilityScore;
import com.jawbr.dnd5e.characterforge.model.entity.Language;
import com.jawbr.dnd5e.characterforge.model.entity.LanguageOption;
import com.jawbr.dnd5e.characterforge.model.entity.Proficiency;
import com.jawbr.dnd5e.characterforge.model.entity.Race;
import com.jawbr.dnd5e.characterforge.model.entity.StartingProficiencyOption;
import com.jawbr.dnd5e.characterforge.model.entity.SubRace;
import com.jawbr.dnd5e.characterforge.model.entity.SubRaceAbilityScoreBonus;
import com.jawbr.dnd5e.characterforge.model.entity.Trait;
import com.jawbr.dnd5e.characterforge.model.entity.TypicalSpeakers;
import com.jawbr.dnd5e.characterforge.model.util.LanguageType;
import com.jawbr.dnd5e.characterforge.model.util.OptionType;
import com.jawbr.dnd5e.characterforge.model.util.ProficiencyType;
import com.jawbr.dnd5e.characterforge.model.util.Size;
import com.jawbr.dnd5e.characterforge.repository.SubRaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
class SubRaceServiceTest {

    @InjectMocks
    private SubRaceService subRaceService;

    @Mock
    private SubRaceRepository subRaceRepository;

    @Mock
    private SubRaceDTOMapper subRaceDTOMapper;

    @Mock
    private SubRaceDTOResponseMapper subRaceDTOResponseMapper;

    private SubRace subRace;
    private FindAllDTOResponse subRaceResponse;
    private SubRaceDTO subRaceDTO;

    @BeforeEach
    void init() {
        Race race = Race.builder()
                .indexName("elf")
                .raceName("Elf")
                .speed(30)
                .size(Size.Medium)
                .age_desc("Although elves reach physical maturity at about the same age as humans, the elven understanding of " +
                        "adulthood goes beyond physical growth to encompass worldly experience. An elf typically " +
                        "claims adulthood and an adult name around the age of 100 and can live to be 750 years old.")
                .alignment("Elves love freedom, variety, and self-expression, so they lean strongly toward the gentler aspects of chaos. " +
                        "They value and protect others' freedom as well as their own, and they are more often good than not.")
                .size_desc("Elves range from under 5 to over 6 feet tall and have slender builds. Your size is Medium.")
                .language_desc("You can speak, read, and write Common and Elvish. Elvish is fluid, with subtle intonations and intricate grammar. " +
                        "Elven literature is rich and varied, and their songs and poems are famous among other races. Many bards learn their " +
                        "language so they can add Elvish ballads to their repertoires.")
                .url("/api/races/elf")
                .build();

        Language languageCommon = Language.builder()
                .indexName("common")
                .name("Common")
                .type(LanguageType.Standard)
                .script("Common")
                .url("/api/languages/common")
                .typicalSpeakers(List.of(TypicalSpeakers.builder()
                        .speakerName("Human")
                        .build()))
                .build();

        Proficiency skill_perception = Proficiency.builder()
                .indexName("skill-perception")
                .type(ProficiencyType.Skills)
                .name("Skill: Perception")
                .url("/api/proficiencies/skill-perception")
                .races(List.of(race))
                .build();

        LanguageOption languageOptionExtraLang = LanguageOption.builder()
                .choose(1)
                .type(OptionType.language)
                .from(List.of(languageCommon))
                .build();

        StartingProficiencyOption proficiencyOption = StartingProficiencyOption.builder()
                .choose(2)
                .type(OptionType.proficiencies)
                .from(List.of(skill_perception))
                .build();

        Trait trait = Trait.builder()
                .indexName("keen-senses")
                .races(List.of(race))
                .traitName("Keen Senses")
                .desc("You have proficiency in the Perception skill.")
                .proficiencies(List.of(skill_perception))
                .proficiencyOptions(proficiencyOption)
                .languageOptions(languageOptionExtraLang)
                .url("/api/traits/keen-senses")
                .build();

        subRace = SubRace.builder()
                .id(1)
                .indexName("high-elf")
                .subRaceName("High Elf")
                .desc("As a high elf, you have a keen mind and a mastery of at least the basics of magic. " +
                        "In many fantasy gaming worlds, there are two kinds of high elves. " +
                        "One type is haughty and reclusive, believing themselves to be superior " +
                        "to non-elves and even other elves. The other type is more common and more " +
                        "friendly, and often encountered among humans and other races.")
                .url("/api/subraces/high-elf")
                .race(race)
                .abilityBonuses(Collections.singletonList(SubRaceAbilityScoreBonus.builder()
                        .abilityScore(AbilityScore.builder()
                                .indexName("dex")
                                .fullName("Dexterity")
                                .shortName("DEX")
                                .desc("desc")
                                .url("/api/ability-scores/dex")
                                .build())
                        .bonus(1)
                        .subRace(subRace)
                        .build()))
                .proficiencies(List.of(skill_perception))
                .languages(List.of(languageCommon))
                .languageOptions(languageOptionExtraLang)
                .traits(List.of(trait))
                .build();

        subRaceDTO = SubRaceDTO.builder()
                .index(subRace.getIndexName())
                .name(subRace.getSubRaceName())
                .desc(subRace.getDesc())
                .url(subRace.getUrl())
                .race(EntityReferenceDTO.builder()
                        .index(race.getIndexName())
                        .name(race.getRaceName())
                        .url(race.getUrl())
                        .build())
                .ability_bonuses(Collections.singletonList(SubRaceAbilityScoreBonusDTO.builder()
                        .ability_score(EntityReferenceDTO.builder()
                                .index("dex")
                                .name("DEX")
                                .url("/api/ability-scores/dex")
                                .build())
                        .bonus(2)
                        .build()))
                .starting_proficiencies(List.of(EntityReferenceDTO.builder()
                        .index(skill_perception.getIndexName())
                        .name(skill_perception.getName())
                        .url(skill_perception.getUrl())
                        .build()))
                .languages(List.of(EntityReferenceDTO.builder()
                        .name(languageCommon.getName())
                        .url(languageCommon.getUrl())
                        .index(languageCommon.getIndexName())
                        .build()))
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
                .racial_traits(List.of(EntityReferenceDTO.builder()
                        .index(trait.getIndexName())
                        .name(trait.getTraitName())
                        .url(trait.getUrl())
                        .build()))
                .build();

        EntityReferenceDTO subRaceForFindAll = EntityReferenceDTO.builder()
                .name(subRace.getSubRaceName())
                .index(subRace.getIndexName())
                .url(subRace.getUrl())
                .build();

        List<EntityReferenceDTO> subRaceList = new ArrayList<>();
        subRaceList.add(subRaceForFindAll);

        subRaceResponse = FindAllDTOResponse.builder()
                .count(subRaceList.size())
                .results(subRaceList)
                .build();
    }

    @Test
    void findAllSubRaces() {
        List<SubRace> subRaces = new ArrayList<>();
        subRaces.add(subRace);

        FindAllDTOResponse expected = subRaceResponse;

        when(subRaceRepository.findAll()).thenReturn(subRaces);
        when(subRaceDTOResponseMapper.apply(subRaces)).thenReturn(subRaceResponse);

        FindAllDTOResponse result = subRaceService.findAllSubRaces();

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void findSubRaceByIndexName() {
        SubRaceDTO expected = subRaceDTO;

        when(subRaceRepository.findByIndexName(subRace.getIndexName())).thenReturn(subRace);
        when(subRaceDTOMapper.apply(subRace)).thenReturn(subRaceDTO);

        SubRaceDTO result = subRaceService.findSubRaceByIndexName(subRace.getIndexName());

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void cannotFindAllSubRaces() {
        List<SubRace> subRaces = new ArrayList<>();
        when(subRaceRepository.findAll()).thenReturn(subRaces);

        assertThrows(RaceNotFoundException.class,
                () -> subRaceService.findAllSubRaces(),
                "No sub races found.");
    }

    @Test
    void cannotFindSubRaceByIndexName() {
        when(subRaceRepository.findByIndexName(subRace.getIndexName())).thenReturn(null);

        assertThrows(RaceNotFoundException.class,
                () -> subRaceService.findSubRaceByIndexName(subRace.getIndexName()),
                String.format("Subrace with index name '%s' not found.", subRace.getIndexName()));
    }
}