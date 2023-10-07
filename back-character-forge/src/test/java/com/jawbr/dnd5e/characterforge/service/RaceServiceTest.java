package com.jawbr.dnd5e.characterforge.service;

import com.jawbr.dnd5e.characterforge.dto.mapper.race.RaceDTOMapper;
import com.jawbr.dnd5e.characterforge.exception.RaceNotFoundException;
import com.jawbr.dnd5e.characterforge.model.entity.Race;
import com.jawbr.dnd5e.characterforge.repository.RaceRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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

    @Disabled
    @Test
    void findAllRaces() {
    }

    @Test
    void cannotFindAllRaces() {
        List<Race> raceList = new ArrayList<>();
        when(raceRepository.findAll()).thenReturn(raceList);

        assertThrows(RaceNotFoundException.class,
                () -> raceService.findAllRaces(),
                "No races found.");
    }
}