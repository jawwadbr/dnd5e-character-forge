package com.jawbr.dnd5e.characterforge.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "trait")
public class Trait {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "index_name", nullable = false, unique = true, length = 128)
    private String indexName;

    // races that this trait is part
    @ManyToMany(mappedBy = "traits", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    private List<Race> races;

    // subraces that this trait is part
    @ManyToMany(mappedBy = "traits", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    private List<SubRace> subRaces;

    @Column(name = "trait_name", nullable = false)
    private String traitName;

    @Column(name = "trait_desc", columnDefinition = "TEXT", nullable = false)
    private String desc;

    // proficiencies
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(
            name = "trait_proficiency",
            joinColumns = @JoinColumn(name = "trait_id"),
            inverseJoinColumns = @JoinColumn(name = "proficiency_id")
    )
    private List<Proficiency> proficiencies;

    // proficiency_choices
    // trait_specific -- subtrait_options
    // language_options

    @Column(name = "url", nullable = false, unique = true, length = 128)
    private String url;
}
