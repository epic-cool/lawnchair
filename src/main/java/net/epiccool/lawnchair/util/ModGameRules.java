package net.epiccool.lawnchair.util;

import net.epiccool.lawnchair.Lawnchair;
import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;

public class ModGameRules {
    public static boolean creeperExplosions = true;
    public static boolean silkyCreepers = false;
    public static boolean bedExplosions = true;
    public static boolean slimeSpawning = true; //doesn't work

    public static final CustomGameRuleCategory LAWNCHAIR =
            new CustomGameRuleCategory(
                    Identifier.of("lawnchair", "lawnchair"),
                    Text.of("gamerule.category.lawnchair")
            );

    public static final GameRules.Key<GameRules.BooleanRule> CREEPER_EXPLOSIONS =
            GameRuleRegistry.register("creeperExplosions",
                    ModGameRules.LAWNCHAIR,
                    GameRuleFactory.createBooleanRule(true));

    public static final GameRules.Key<GameRules.BooleanRule> SILKY_CREEPERS =
            GameRuleRegistry.register("silkyCreepers",
                    ModGameRules.LAWNCHAIR,
                    GameRuleFactory.createBooleanRule(false));

    public static final GameRules.Key<GameRules.BooleanRule> BED_EXPLOSIONS =
            GameRuleRegistry.register("bedExplosions",
                    ModGameRules.LAWNCHAIR,
                    GameRuleFactory.createBooleanRule(true));

    public static void Initialize() {
        Lawnchair.LOGGER.info("Registering Mod Game Rules for " + Lawnchair.MODID);
    }
}
