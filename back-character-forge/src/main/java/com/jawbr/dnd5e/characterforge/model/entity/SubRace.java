package com.jawbr.dnd5e.characterforge.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "sub_race")
public class SubRace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "index_name", nullable = false)
    private String indexName;

    @Column(name = "sub_race_name", nullable = false)
    private String subRaceName;

    @Column(name = "sub_race_descr", columnDefinition = "TEXT", nullable = false)
    private String desc;

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    private Race race;

    // starting proficiencies
    // Languages
    // languages options
    // racial traits
}
