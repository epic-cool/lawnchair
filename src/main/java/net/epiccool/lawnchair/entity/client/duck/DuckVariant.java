package net.epiccool.lawnchair.entity.client.duck;

import net.epiccool.lawnchair.Lawnchair;
import net.minecraft.util.Identifier;

public enum DuckVariant {
    MALE,
    FEMALE;

    public Identifier getId() {
        return switch (this) {
            case MALE -> Identifier.of(Lawnchair.MODID, "male");
            case FEMALE -> Identifier.of(Lawnchair.MODID, "female");
        };
    }
}
