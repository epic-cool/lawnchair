package net.epiccool.lawnchair.stat;

import net.epiccool.lawnchair.Lawnchair;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.StatType;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModStats {
    public static void Initialize() {
        Lawnchair.LOGGER.info("Registering Mod Stats for " + Lawnchair.MODID);
    }

    private static Identifier register(String id, StatFormatter formatter) {
        Identifier identifier = Identifier.ofVanilla(id);
        Registry.register(Registries.CUSTOM_STAT, id, identifier);
        Stats.CUSTOM.getOrCreateStat(identifier, formatter);
        return identifier;
    }

    public static <T> StatType<T> registerType(String id, Registry<T> registry) {
        Text text = Text.translatable("stat_type.lawnchair." + id);
        return Registry.register(Registries.STAT_TYPE, id, new StatType<>(registry, text));
    }

//    public static final Identifier INTERACT_WITH_ALLOY_MIXER = register("interact_with_alloy_mixer", StatFormatter.DEFAULT);
}
