package com.jawbr.dnd5e.characterforge.model.entity;

import com.jawbr.dnd5e.characterforge.model.util.ProficiencyType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "proficiency")
public class Proficiency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "index_name", nullable = false, unique = true, length = 128)
    private String indexName;

    @Column(name = "proficiency_name", nullable = false)
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ProficiencyType type;

    @Column(name = "url", nullable = false, unique = true, length = 128)
    private String url;

    // classes that this proficiency is part
    @ManyToMany(mappedBy = "proficiencies", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    private List<Class> classes;

    // races that this proficiency is part
    @ManyToMany(mappedBy = "proficiencies", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    private List<Race> races;

    // subRaces that this proficiency is part
    @ManyToMany(mappedBy = "proficiencies", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    private List<SubRace> subRaces;

    // reference that this proficiency is part
}
