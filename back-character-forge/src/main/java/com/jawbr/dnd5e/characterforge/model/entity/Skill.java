package com.jawbr.dnd5e.characterforge.model.entity;

import jakarta.persistence.CascadeType;
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
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "index_name", nullable = false, unique = true, length = 128)
    private String indexName;

    @Column(name = "skill_name", nullable = false)
    private String name;

    @Column(name = "skill_desc", columnDefinition = "TEXT", nullable = false)
    private String skillDesc;

    @Column(name = "url", nullable = false, unique = true, length = 128)
    private String url;

    // Many Skills can belong to One AbilityScore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "ability_score_id_fk")
    private AbilityScore abilityScore;

}
