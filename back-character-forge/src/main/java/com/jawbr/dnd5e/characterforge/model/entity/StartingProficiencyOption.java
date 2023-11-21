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
@Table(name = "starting_proficiency_option")
public class StartingProficiencyOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int choose;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OptionType type;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(
            name = "proficiencies_options",
            joinColumns = @JoinColumn(name = "proficiencies_option_id"),
            inverseJoinColumns = @JoinColumn(name = "proficiency_id")
    )
    private List<Proficiency> from;

    @Column(name = "descr", columnDefinition = "TEXT")
    private String desc;

}
