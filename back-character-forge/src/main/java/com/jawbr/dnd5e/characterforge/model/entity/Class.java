package com.jawbr.dnd5e.characterforge.model.entity;

import com.jawbr.dnd5e.characterforge.model.util.HitDie;
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
@Table(name = "class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "index_name", nullable = false, unique = true, length = 128)
    private String indexName;

    @Column(name = "class_name", nullable = false)
    private String className;

    @Column(name = "hit_die", nullable = false)
    @Enumerated(EnumType.STRING)
    private HitDie hitDie;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private StartingProficiencyOption proficiencyChoices;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(
            name = "class_proficiency",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "proficiency_id")
    )
    private List<Proficiency> proficiencies;

    // saving throws

    // starting equips
    // starting equips options

    // class levels
    // multiclassing - might be done later or not even be implemented
    // subclasses
    // spellcasting
    // spells

    @Column(name = "url", nullable = false, unique = true, length = 128)
    private String url;

}
