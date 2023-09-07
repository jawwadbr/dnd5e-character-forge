package com.jawbr.modal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "index_name", nullable = false)
    private String indexName;

    @Column(name = "skill_name", nullable = false)
    private String name;

    @Column(name = "skill_desc", columnDefinition = "TEXT", nullable = false)
    private String skillDesc;

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToMany(mappedBy = "skills")
    private List<AbilityScore> abilityScores;
}
