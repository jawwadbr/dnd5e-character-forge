package com.jawbr.dnd5e.characterforge.model.util;

import lombok.Getter;

@Getter
public enum HitDie {
    d4(4),
    d6(6),
    d8(8),
    d10(10),
    d12(12),
    d20(20);

    private final int value;

    HitDie(int value) {
        this.value = value;
    }

}
