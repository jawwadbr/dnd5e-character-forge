package com.jawbr.dnd5e.characterforge.service;

import com.jawbr.dnd5e.characterforge.dto.mapper.trait.TraitDTOMapper;
import com.jawbr.dnd5e.characterforge.dto.mapper.trait.TraitDTOResponseMapper;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceOptionDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.FromOptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.OptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.trait.TraitDTO;
import com.jawbr.dnd5e.characterforge.exception.TraitNotFoundException;
import com.jawbr.dnd5e.characterforge.model.entity.Language;
import com.jawbr.dnd5e.characterforge.model.entity.LanguageOption;
import com.jawbr.dnd5e.characterforge.model.entity.Proficiency;
import com.jawbr.dnd5e.characterforge.model.entity.Race;
import com.jawbr.dnd5e.characterforge.model.entity.StartingProficiencyOption;
import com.jawbr.dnd5e.characterforge.model.entity.SubRace;
import com.jawbr.dnd5e.characterforge.model.entity.Trait;
import com.jawbr.dnd5e.characterforge.model.entity.TypicalSpeakers;
import com.jawbr.dnd5e.characterforge.model.util.LanguageType;
import com.jawbr.dnd5e.characterforge.model.util.OptionType;
import com.jawbr.dnd5e.characterforge.model.util.ProficiencyType;
import com.jawbr.dnd5e.characterforge.model.util.Size;
import com.jawbr.dnd5e.characterforge.repository.TraitRepository;
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
public class TraitServiceTest {

    @InjectMocks
    private TraitService traitService;

    @Mock
    private TraitRepository traitRepository;

    @Mock
    private TraitDTOMapper traitDTOMapper;

    @Mock
    private TraitDTOResponseMapper traitDTOResponseMapper;

    private Trait trait;
    private FindAllDTOResponse traitFindAllDTOResponse;
    private TraitDTO traitDTO;

    @BeforeEach
    void init() {

        Race elf = Race.builder()
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

        SubRace subrace = SubRace.builder()
                .subRaceName("High Elf")
                .race(elf)
                .desc("As a high elf, you have a keen mind and a mastery of at least the basics of magic. " +
                        "In many fantasy gaming worlds, there are two kinds of high elves. One type is haughty and reclusive, " +
                        "believing themselves to be superior to non-elves and even other elves. The other type is more common and more friendly, " +
                        "and often encountered among humans and other races.")
                .url("/api/subraces/high-elf")
                .indexName("high-elf")
                .build();

        Proficiency skill_perception = Proficiency.builder()
                .indexName("skill-perception")
                .type(ProficiencyType.Skills)
                .name("Skill: Perception")
                .url("/api/proficiencies/skill-perception")
                .races(List.of(elf))
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

        StartingProficiencyOption proficiencyOption = StartingProficiencyOption.builder()
                .choose(2)
                .type(OptionType.proficiencies)
                .from(List.of(skill_perception))
                .build();

        LanguageOption languageOptionExtraLang = LanguageOption.builder()
                .choose(1)
                .type(OptionType.language)
                .from(List.of(languageCommon))
                .build();

        trait = Trait.builder()
                .indexName("keen-senses")
                .races(List.of(elf))
                .subRaces(List.of(subrace))
                .traitName("Keen Senses")
                .desc("You have proficiency in the Perception skill.")
                .proficiencies(List.of(skill_perception))
                .proficiencyOptions(proficiencyOption)
                .languageOptions(languageOptionExtraLang)
                .url("/api/traits/keen-senses")
                .build();

        //

        EntityReferenceDTO traitEntityReferenceDTO = EntityReferenceDTO.builder()
                .index(trait.getIndexName())
                .name(trait.getTraitName())
                .url(trait.getUrl())
                .build();

        List<EntityReferenceDTO> entityReferenceDTOList = new ArrayList<>();
        entityReferenceDTOList.add(traitEntityReferenceDTO);

        traitFindAllDTOResponse = FindAllDTOResponse.builder()
                .count(entityReferenceDTOList.size())
                .results(entityReferenceDTOList)
                .build();

        traitDTO = TraitDTO.builder()
                .index(trait.getIndexName())
                .races(List.of(EntityReferenceDTO.builder()
                        .index(elf.getIndexName())
                        .name(elf.getRaceName())
                        .url(elf.getUrl())
                        .build()))
                .subraces(List.of(EntityReferenceDTO.builder()
                        .index(subrace.getIndexName())
                        .name(subrace.getSubRaceName())
                        .url(subrace.getUrl())
                        .build()))
                .name(trait.getTraitName())
                .desc(trait.getDesc())
                .proficiencies(List.of(EntityReferenceDTO.builder()
                        .index(skill_perception.getIndexName())
                        .name(skill_perception.getName())
                        .url(skill_perception.getUrl())
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
                .proficiency_choices(OptionSetDTO.builder()
                        .choose(1)
                        .type(OptionType.proficiencies)
                        .from(FromOptionSetDTO.builder()
                                .options(Collections.singletonList(EntityReferenceOptionDTO.builder()
                                        .item(EntityReferenceDTO.builder()
                                                .index("skill-perception")
                                                .name("Skill: Perception")
                                                .url("/api/proficiencies/skill-perception")
                                                .build())
                                        .bonus(1)
                                        .build()))
                                .build())
                        .build())
                .url(trait.getUrl())
                .build();
    }

    @Test
    void findAllTraits() {
        List<Trait> traits = new ArrayList<>();
        traits.add(trait);

        FindAllDTOResponse expected = traitFindAllDTOResponse;

        when(traitRepository.findAll()).thenReturn(traits);
        when(traitDTOResponseMapper.apply(traits)).thenReturn(traitFindAllDTOResponse);

        FindAllDTOResponse result = traitService.findAllTraits();

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void findTraitByIndexName() {
        TraitDTO expected = traitDTO;

        when(traitRepository.findByIndexName(trait.getIndexName())).thenReturn(trait);
        when(traitDTOMapper.apply(trait)).thenReturn(traitDTO);

        TraitDTO result = traitService.findTraitByIndexName(trait.getIndexName());

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void cannotFindAllTraits() {
        List<Trait> traits = new ArrayList<>();
        when(traitRepository.findAll()).thenReturn(traits);

        assertThrows(TraitNotFoundException.class,
                () -> traitService.findAllTraits(),
                "No traits found.");
    }

    @Test
    void cannotFindTraitByIndexName() {
        when(traitRepository.findByIndexName(trait.getIndexName())).thenReturn(null);

        assertThrows(TraitNotFoundException.class,
                () -> traitService.findTraitByIndexName(trait.getIndexName()),
                String.format("Trait with index name '%s' not found.", trait.getIndexName()));
    }
}
