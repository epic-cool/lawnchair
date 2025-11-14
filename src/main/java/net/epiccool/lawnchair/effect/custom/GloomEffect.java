package net.epiccool.lawnchair.effect.custom;

import net.epiccool.lawnchair.Lawnchair;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;

public class GloomEffect extends StatusEffect {
    public static final Identifier GLOOM_OVERLAY_ID = Identifier.of(Lawnchair.MODID, "gloom_overlay");

    public GloomEffect() {
        super(StatusEffectCategory.HARMFUL, 0x000000);
    }
}
