package com.jawbr.dnd5e.characterforge.controller;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceOptionDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.FromOptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.OptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.race.RaceAbilityBonusesDTO;
import com.jawbr.dnd5e.characterforge.dto.response.race.RaceDTO;
import com.jawbr.dnd5e.characterforge.exception.RaceNotFoundException;
import com.jawbr.dnd5e.characterforge.model.util.OptionType;
import com.jawbr.dnd5e.characterforge.model.util.Size;
import com.jawbr.dnd5e.characterforge.service.RaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RaceController.class)
@AutoConfigureMockMvc
class RaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RaceService raceService;

    private final String PATH = "/api/races";

    private FindAllDTOResponse raceFindAllResponse;
    private EntityReferenceDTO raceForFindAll;
    private RaceDTO raceDTO;
    private RaceAbilityBonusesDTO raceAbilityBonusesDTO;

    @BeforeEach
    public void init() {

        raceForFindAll = EntityReferenceDTO.builder()
                .index("elf")
                .name("Elf")
                .url("/api/races/elf")
                .build();

        List<EntityReferenceDTO> raceList = new ArrayList<>();
        raceList.add(raceForFindAll);

        raceFindAllResponse = FindAllDTOResponse.builder()
                .count(raceList.size())
                .results(raceList)
                .build();

        //

        raceAbilityBonusesDTO = RaceAbilityBonusesDTO.builder()
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
                .index("elf")
                .name("Elf")
                .speed(30)
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
                .alignment("alignment")
                .age("age desc")
                .size(Size.Medium)
                .size_description("size desc")
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
                .language_desc("language desc")
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
                .url("/api/races/elf")
                .build();
    }

    @Test
    void findAllRaces() throws Exception {
        when(raceService.findAllRaces()).thenReturn(raceFindAllResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count", is(raceFindAllResponse.count())))
                .andExpect(jsonPath("$.results[0].index", is(raceForFindAll.index())))
                .andExpect(jsonPath("$.results[0].name", is(raceForFindAll.name())))
                .andExpect(jsonPath("$.results[0].url", is(raceForFindAll.url())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void findRaceByIndexName() throws Exception {
        when(raceService.findRaceByIndexName(raceDTO.index())).thenReturn(raceDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + raceDTO.index())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.index", is(raceDTO.index())))
                .andExpect(jsonPath("$.name", is(raceDTO.name())))
                .andExpect(jsonPath("$.speed", is(raceDTO.speed())))
                // ability bonuses
                .andExpect(jsonPath("$.ability_bonuses[0].ability_score.index",
                        is(raceAbilityBonusesDTO.ability_score().index())))
                .andExpect(jsonPath("$.ability_bonuses[0].ability_score.name",
                        is(raceAbilityBonusesDTO.ability_score().name())))
                .andExpect(jsonPath("$.ability_bonuses[0].ability_score.url",
                        is(raceAbilityBonusesDTO.ability_score().url())))
                .andExpect(jsonPath("$.ability_bonuses[0].bonus",
                        is(raceAbilityBonusesDTO.bonus())))
                //
                .andExpect(jsonPath("$.alignment", is(raceDTO.alignment())))
                .andExpect(jsonPath("$.age", is(raceDTO.age())))
                .andExpect(jsonPath("$.size", is(raceDTO.size().name())))
                .andExpect(jsonPath("$.size_description", is(raceDTO.size_description())))
                // languages
                .andExpect(jsonPath("$.languages[0].index", is(raceDTO.languages().get(0).index())))
                .andExpect(jsonPath("$.languages[0].name", is(raceDTO.languages().get(0).name())))
                .andExpect(jsonPath("$.languages[0].url", is(raceDTO.languages().get(0).url())))

                .andExpect(jsonPath("$.language_desc", is(raceDTO.language_desc())))
                // starting_proficiencies
                .andExpect(jsonPath("$.starting_proficiencies[0].index",
                        is(raceDTO.starting_proficiencies().get(0).index())))
                .andExpect(jsonPath("$.starting_proficiencies[0].name",
                        is(raceDTO.starting_proficiencies().get(0).name())))
                .andExpect(jsonPath("$.starting_proficiencies[0].url",
                        is(raceDTO.starting_proficiencies().get(0).url())))
                // subraces
                .andExpect(jsonPath("$.subraces[0].index",
                        is(raceDTO.subraces().get(0).index())))
                .andExpect(jsonPath("$.subraces[0].name",
                        is(raceDTO.subraces().get(0).name())))
                .andExpect(jsonPath("$.subraces[0].url",
                        is(raceDTO.subraces().get(0).url())))
                // trait
                .andExpect(jsonPath("$.traits[0].index",
                        is(raceDTO.traits().get(0).index())))
                .andExpect(jsonPath("$.traits[0].name",
                        is(raceDTO.traits().get(0).name())))
                .andExpect(jsonPath("$.traits[0].url",
                        is(raceDTO.traits().get(0).url())))
                // language_options
                .andExpect(jsonPath("$.language_options.choose",
                        is(raceDTO.language_options().choose())))
                .andExpect(jsonPath("$.language_options.type",
                        is(raceDTO.language_options().type().name())))
                .andExpect(jsonPath("$.language_options.from.options[0].item.index",
                        is(raceDTO.language_options().from().options().get(0).item().index())))
                .andExpect(jsonPath("$.language_options.from.options[0].item.name",
                        is(raceDTO.language_options().from().options().get(0).item().name())))
                .andExpect(jsonPath("$.language_options.from.options[0].item.url",
                        is(raceDTO.language_options().from().options().get(0).item().url())))
                // starting_proficiency_options
                .andExpect(jsonPath("$.starting_proficiency_options.choose",
                        is(raceDTO.starting_proficiency_options().choose())))
                .andExpect(jsonPath("$.starting_proficiency_options.type",
                        is(raceDTO.starting_proficiency_options().type().name())))
                .andExpect(jsonPath("$.starting_proficiency_options.from.options[0].item.index",
                        is(raceDTO.starting_proficiency_options().from().options().get(0).item().index())))
                .andExpect(jsonPath("$.starting_proficiency_options.from.options[0].item.name",
                        is(raceDTO.starting_proficiency_options().from().options().get(0).item().name())))
                .andExpect(jsonPath("$.starting_proficiency_options.from.options[0].item.url",
                        is(raceDTO.starting_proficiency_options().from().options().get(0).item().url())))
                // ability bonuses options
                .andExpect(jsonPath("$.ability_bonus_options.choose",
                        is(raceDTO.ability_bonus_options().choose())))
                .andExpect(jsonPath("$.ability_bonus_options.type",
                        is(raceDTO.ability_bonus_options().type().name())))
                .andExpect(jsonPath("$.ability_bonus_options.from.options[0].ability_score.index",
                        is(raceDTO.ability_bonus_options().from().options().get(0).ability_score().index())))
                .andExpect(jsonPath("$.ability_bonus_options.from.options[0].ability_score.name",
                        is(raceDTO.ability_bonus_options().from().options().get(0).ability_score().name())))
                .andExpect(jsonPath("$.ability_bonus_options.from.options[0].ability_score.url",
                        is(raceDTO.ability_bonus_options().from().options().get(0).ability_score().url())))
                .andExpect(jsonPath("$.url", is(raceDTO.url())))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void cannotFindAllRaces() throws Exception {
        when(raceService.findAllRaces())
                .thenThrow(new RaceNotFoundException("No races found."));

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("No races found.")))
                .andExpect(jsonPath("$.path", is(PATH)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cannotFindRaceByIndexName() throws Exception {
        when(raceService.findRaceByIndexName(raceDTO.index()))
                .thenThrow(new RaceNotFoundException(String.format("Race with index name '%s' not found.", raceDTO.index())));

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + raceDTO.index())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is(String.format("Race with index name '%s' not found.", raceDTO.index()))))
                .andExpect(jsonPath("$.path", is(PATH + "/" + raceDTO.index())))
                .andDo(MockMvcResultHandlers.print());
    }

}
