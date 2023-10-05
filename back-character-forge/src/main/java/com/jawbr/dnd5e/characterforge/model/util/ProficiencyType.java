package com.jawbr.dnd5e.characterforge.model.util;

import lombok.Getter;

@Getter
public enum ProficiencyType {
    Artisans_Tools("Artisan's Tools"),
    Armor("Armor"),
    Saving_Throws("Saving Throws"),
    Skills("Skills"),
    Gaming_Sets("Gaming Sets"),
    Weapons("Weapons");

    private final String displayName;

    ProficiencyType(String displayName) {
        this.displayName = displayName;
    }
}
