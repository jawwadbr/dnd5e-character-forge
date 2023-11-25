package com.jawbr.dnd5e.characterforge.controller;

import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceDTO;
import com.jawbr.dnd5e.characterforge.dto.response.EntityReferenceOptionDTO;
import com.jawbr.dnd5e.characterforge.dto.response.FindAllDTOResponse;
import com.jawbr.dnd5e.characterforge.dto.response.FromOptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.OptionSetDTO;
import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceAbilityScoreBonusDTO;
import com.jawbr.dnd5e.characterforge.dto.response.subRace.SubRaceDTO;
import com.jawbr.dnd5e.characterforge.exception.RaceNotFoundException;
import com.jawbr.dnd5e.characterforge.model.util.OptionType;
import com.jawbr.dnd5e.characterforge.service.SubRaceService;
import org.junit.jupiter.api.BeforeEach;
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

@WebMvcTest(SubRaceController.class)
@AutoConfigureMockMvc
class SubRaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubRaceService subRaceService;

    private final String PATH = "/api/subraces";

    private FindAllDTOResponse subRaceFindAllResponse;
    private EntityReferenceDTO subRaceForFindAll;
    private SubRaceDTO subRaceDTO;
    private SubRaceAbilityScoreBonusDTO subRaceAbilityScoreBonusDTO;

    @BeforeEach
    void init() {

        subRaceForFindAll = EntityReferenceDTO.builder()
                .name("High Elf")
                .index("high-elf")
                .url("/api/subraces/high-elf")
                .build();

        List<EntityReferenceDTO> list = new ArrayList<>();
        list.add(subRaceForFindAll);

        subRaceFindAllResponse = FindAllDTOResponse.builder()
                .count(list.size())
                .results(list)
                .build();

        subRaceAbilityScoreBonusDTO = SubRaceAbilityScoreBonusDTO.builder()
                .bonus(2)
                .ability_score(EntityReferenceDTO.builder()
                        .index("dex")
                        .name("DEX")
                        .url("/api/ability-scores/dex")
                        .build())
                .build();
        List<SubRaceAbilityScoreBonusDTO> abBonusList = new ArrayList<>();
        abBonusList.add(subRaceAbilityScoreBonusDTO);

        subRaceDTO = SubRaceDTO.builder()
                .index("high-elf")
                .name("High Elf")
                .desc("As a high elf, you have a keen mind and a mastery of at least the basics of magic. " +
                        "In many fantasy gaming worlds, there are two kinds of high elves. " +
                        "One type is haughty and reclusive, believing themselves to be superior " +
                        "to non-elves and even other elves. The other type is more common and more " +
                        "friendly, and often encountered among humans and other races.")
                .url("/api/subraces/high-elf")
                .race(EntityReferenceDTO.builder()
                        .index("elf")
                        .name("Elf")
                        .url("/api/races/elf")
                        .build())
                .ability_bonuses(abBonusList)
                .starting_proficiencies(Collections.singletonList(EntityReferenceDTO.builder()
                        .index("skill-perception")
                        .name("Skill: Perception")
                        .url("/api/proficiencies/skill-perception")
                        .build()))
                .languages(Collections.singletonList(EntityReferenceDTO.builder()
                        .index("elvish")
                        .name("Elvish")
                        .url("/api/languages/elvish")
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
                                        .build()))
                                .build())
                        .build())
                .racial_traits(Collections.singletonList(EntityReferenceDTO.builder()
                        .index("extra-language")
                        .name("Extra Language")
                        .url("/api/traits/extra-language")
                        .build()))
                .build();
    }

    @Test
    void findAllSubRaces() throws Exception {
        when(subRaceService.findAllSubRaces()).thenReturn(subRaceFindAllResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count", is(subRaceFindAllResponse.count())))
                .andExpect(jsonPath("$.results[0].index", is(subRaceForFindAll.index())))
                .andExpect(jsonPath("$.results[0].name", is(subRaceForFindAll.name())))
                .andExpect(jsonPath("$.results[0].url", is(subRaceForFindAll.url())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void findSubRaceByIndexName() throws Exception {
        when(subRaceService.findSubRaceByIndexName(subRaceDTO.index())).thenReturn(subRaceDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + subRaceDTO.index())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.index", is(subRaceDTO.index())))
                .andExpect(jsonPath("$.name", is(subRaceDTO.name())))
                .andExpect(jsonPath("$.race.index", is(subRaceDTO.race().index())))
                .andExpect(jsonPath("$.race.name", is(subRaceDTO.race().name())))
                .andExpect(jsonPath("$.race.url", is(subRaceDTO.race().url())))
                .andExpect(jsonPath("$.desc", is(subRaceDTO.desc())))
                // ability bonuses
                .andExpect(jsonPath("$.ability_bonuses[0].bonus",
                        is(subRaceDTO.ability_bonuses().get(0).bonus())))
                .andExpect(jsonPath("$.ability_bonuses[0].ability_score.index",
                        is(subRaceDTO.ability_bonuses().get(0).ability_score().index())))
                .andExpect(jsonPath("$.ability_bonuses[0].ability_score.name",
                        is(subRaceDTO.ability_bonuses().get(0).ability_score().name())))
                .andExpect(jsonPath("$.ability_bonuses[0].ability_score.url",
                        is(subRaceDTO.ability_bonuses().get(0).ability_score().url())))
                // starting_proficiencies
                .andExpect(jsonPath("$.starting_proficiencies[0].index",
                        is(subRaceDTO.starting_proficiencies().get(0).index())))
                .andExpect(jsonPath("$.starting_proficiencies[0].name",
                        is(subRaceDTO.starting_proficiencies().get(0).name())))
                .andExpect(jsonPath("$.starting_proficiencies[0].url",
                        is(subRaceDTO.starting_proficiencies().get(0).url())))
                // languages
                .andExpect(jsonPath("$.languages[0].index",
                        is(subRaceDTO.languages().get(0).index())))
                .andExpect(jsonPath("$.languages[0].name",
                        is(subRaceDTO.languages().get(0).name())))
                .andExpect(jsonPath("$.languages[0].url",
                        is(subRaceDTO.languages().get(0).url())))
                // language_options
                .andExpect(jsonPath("$.language_options.choose",
                        is(subRaceDTO.language_options().choose())))
                .andExpect(jsonPath("$.language_options.type",
                        is(subRaceDTO.language_options().type().name())))
                .andExpect(jsonPath("$.language_options.from.options[0].item.index",
                        is(subRaceDTO.language_options().from().options().get(0).item().index())))
                .andExpect(jsonPath("$.language_options.from.options[0].item.name",
                        is(subRaceDTO.language_options().from().options().get(0).item().name())))
                .andExpect(jsonPath("$.language_options.from.options[0].item.url",
                        is(subRaceDTO.language_options().from().options().get(0).item().url())))
                // racial_traits
                .andExpect(jsonPath("$.racial_traits[0].index",
                        is(subRaceDTO.racial_traits().get(0).index())))
                .andExpect(jsonPath("$.racial_traits[0].name",
                        is(subRaceDTO.racial_traits().get(0).name())))
                .andExpect(jsonPath("$.racial_traits[0].url",
                        is(subRaceDTO.racial_traits().get(0).url())))
                .andExpect(jsonPath("$.url", is(subRaceDTO.url())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cannotFindAllSubRaces() throws Exception {
        when(subRaceService.findAllSubRaces())
                .thenThrow(new RaceNotFoundException("No sub races found."));

        mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("No sub races found.")))
                .andExpect(jsonPath("$.path", is(PATH)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cannotFindSubRaceByIndexName() throws Exception {
        when(subRaceService.findSubRaceByIndexName(subRaceDTO.index()))
                .thenThrow(new RaceNotFoundException(String.format("Subrace with index name '%s' not found.", subRaceDTO.index())));

        mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + subRaceDTO.index())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message",
                        is(String.format("Subrace with index name '%s' not found.", subRaceDTO.index()))))
                .andExpect(jsonPath("$.path", is(PATH + "/" + subRaceDTO.index())))
                .andDo(MockMvcResultHandlers.print());
    }
}