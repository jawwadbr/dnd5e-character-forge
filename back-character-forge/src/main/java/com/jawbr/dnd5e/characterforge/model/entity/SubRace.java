package com.jawbr.dnd5e.characterforge.model.entity;

import com.jawbr.dnd5e.characterforge.model.util.RaceEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "sub_race")
public class SubRace implements RaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "index_name", nullable = false, unique = true, length = 128)
    private String indexName;

    @Column(name = "sub_race_name", nullable = false)
    private String subRaceName;

    @Column(name = "sub_race_descr", columnDefinition = "TEXT", nullable = false)
    private String desc;

    @Column(name = "url", nullable = false, unique = true, length = 128)
    private String url;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    private Race race;

    @ManyToMany(mappedBy = "subRace", cascade = CascadeType.ALL)
    private List<SubRaceAbilityScoreBonus> abilityBonuses;

    // starting proficiencies
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(
            name = "sub-race_proficiency",
            joinColumns = @JoinColumn(name = "sub-race_id"),
            inverseJoinColumns = @JoinColumn(name = "proficiency_id")
    )
    private List<Proficiency> proficiencies;

    // Languages
    // languages options
    // racial traits

    @Override
    public String getEntityName() {
        return getSubRaceName();
    }
}
