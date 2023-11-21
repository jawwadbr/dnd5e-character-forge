package com.jawbr.dnd5e.characterforge.model.entity;

import com.jawbr.dnd5e.characterforge.model.util.OptionType;
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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "ability_score_option")
public class AbilityScoreBonusesOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int choose;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OptionType type;

    @Column(name = "bonus", nullable = false, columnDefinition = "integer default 1")
    private Integer bonus;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(
            name = "ability_score_options",
            joinColumns = @JoinColumn(name = "ability_score_option_id"),
            inverseJoinColumns = @JoinColumn(name = "ability_score_id")
    )
    private List<AbilityScore> from;

    @Column(name = "descr", columnDefinition = "TEXT")
    private String desc;

}
