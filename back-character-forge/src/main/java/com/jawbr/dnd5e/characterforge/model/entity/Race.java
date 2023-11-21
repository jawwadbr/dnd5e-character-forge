package com.jawbr.dnd5e.characterforge.model.entity;

import com.jawbr.dnd5e.characterforge.model.util.Size;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "race")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "index_name", nullable = false, unique = true, length = 128)
    private String indexName;

    @Column(name = "race_name", nullable = false)
    private String raceName;

    @Column(name = "speed", nullable = false)
    private int speed;

    @Column(name = "age_desc", columnDefinition = "TEXT", nullable = false)
    private String age_desc;

    @Column(name = "alignment", columnDefinition = "TEXT", nullable = false)
    private String alignment;

    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    private Size size;

    @Column(name = "size_desc", columnDefinition = "TEXT", nullable = false)
    private String size_desc;

    @Column(name = "language_desc", columnDefinition = "TEXT", nullable = false)
    private String language_desc;

    @Column(name = "url", nullable = false, unique = true, length = 128)
    private String url;

    @ManyToMany(mappedBy = "race", cascade = CascadeType.ALL)
    private List<RaceAbilityScoreBonus> abilityBonuses;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(
            name = "race_languages",
            joinColumns = @JoinColumn(name = "race_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<Language> languages;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(
            name = "race_proficiency",
            joinColumns = @JoinColumn(name = "race_id"),
            inverseJoinColumns = @JoinColumn(name = "proficiency_id")
    )
    private List<Proficiency> proficiencies;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "subrace_id")
    private List<SubRace> subRaces;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(
            name = "race_trait",
            joinColumns = @JoinColumn(name = "race_id"),
            inverseJoinColumns = @JoinColumn(name = "trait_id")
    )
    private List<Trait> traits;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private LanguageOption languageOptions;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AbilityScoreBonusesOption abilityBonusesOptions;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private StartingProficiencyOption proficiencyOptions;

}
