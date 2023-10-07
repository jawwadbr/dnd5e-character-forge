package com.jawbr.dnd5e.characterforge.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "sub_race_ability_score_bonus")
public class SubRaceAbilityScoreBonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "subrace_id", nullable = false)
    private SubRace subRace;

    @ManyToOne
    @JoinColumn(name = "ability_score_id", nullable = false)
    private AbilityScore abilityScore;

    @Column(name = "bonus", nullable = false)
    private int bonus;
}
