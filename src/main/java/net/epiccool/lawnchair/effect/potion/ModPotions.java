package net.epiccool.lawnchair.effect.potion;

import net.epiccool.lawnchair.Lawnchair;
import net.epiccool.lawnchair.effect.ModEffects;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPotions {
    public static final Potion STICKY_POTION =
            Registry.register(
                    Registries.POTION,
                    Identifier.of(Lawnchair.MODID, "sticky"),
                    new Potion("sticky",
                            new StatusEffectInstance(
                                    ModEffects.STICKY,
                                    3600,
                                    0)));

    public static final Potion STICKY_POTION_LONG =
            Registry.register(
                    Registries.POTION,
                    Identifier.of(Lawnchair.MODID, "sticky_long"),
                    new Potion("sticky_long",
                            new StatusEffectInstance(
                                    ModEffects.STICKY,
                                    9600,
                                    0)));

    public static final Potion STICKY_POTION_STRONG =
            Registry.register(
                    Registries.POTION,
                    Identifier.of(Lawnchair.MODID, "sticky_strong"),
                    new Potion("sticky_strong",
                            new StatusEffectInstance(
                                    ModEffects.STICKY,
                                    1800,
                                    1)));

    public static final Potion STICKY_POTION_STRONG1 =
            Registry.register(
                    Registries.POTION,
                    Identifier.of(Lawnchair.MODID, "sticky_strong1"),
                    new Potion("sticky_strong1",
                            new StatusEffectInstance(
                                    ModEffects.STICKY,
                                    1800,
                                    2)));

    public static final Potion STICKY_POTION_STRONG2 =
            Registry.register(
                    Registries.POTION,
                    Identifier.of(Lawnchair.MODID, "sticky_strong2"),
                    new Potion("sticky_strong2",
                            new StatusEffectInstance(
                                    ModEffects.STICKY,
                                    1800,
                                    3)));

    public static final Potion STICKY_POTION_STRONG3 =
            Registry.register(
                    Registries.POTION,
                    Identifier.of(Lawnchair.MODID, "sticky_strong3"),
                    new Potion("sticky_strong3",
                            new StatusEffectInstance(
                                    ModEffects.STICKY,
                                    1800,
                                    4)));

    public static void Initialize() {
        Lawnchair.LOGGER.info("Registering Mod Potions for " + Lawnchair.MODID);

        //recipe
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(
                    //Input
                    Potions.AWKWARD,
                    //Ingredient
                    Items.SLIME_BALL,
                    //Output
                    Registries.POTION.getEntry(STICKY_POTION)
            );

            builder.registerPotionRecipe(
                    //Input
                    Registries.POTION.getEntry(STICKY_POTION),
                    //Ingredient
                    Items.REDSTONE,
                    //Output
                    Registries.POTION.getEntry(STICKY_POTION_LONG)
            );

            builder.registerPotionRecipe(
                    //Input
                    Registries.POTION.getEntry(STICKY_POTION),
                    //Ingredient
                    Items.GLOWSTONE_DUST,
                    //Output
                    Registries.POTION.getEntry(STICKY_POTION_STRONG)
            );

            builder.registerPotionRecipe(
                    //Input
                    Registries.POTION.getEntry(STICKY_POTION_STRONG),
                    //Ingredient
                    Items.GLOWSTONE_DUST,
                    //Output
                    Registries.POTION.getEntry(STICKY_POTION_STRONG1)
            );

            builder.registerPotionRecipe(
                    //Input
                    Registries.POTION.getEntry(STICKY_POTION_STRONG1),
                    //Ingredient
                    Items.GLOWSTONE_DUST,
                    //Output
                    Registries.POTION.getEntry(STICKY_POTION_STRONG2)
            );

            builder.registerPotionRecipe(
                    //Input
                    Registries.POTION.getEntry(STICKY_POTION_STRONG2),
                    //Ingredient
                    Items.GLOWSTONE_DUST,
                    //Output
                    Registries.POTION.getEntry(STICKY_POTION_STRONG3)
            );

            builder.registerPotionRecipe(
                    //Input
                    Registries.POTION.getEntry(STICKY_POTION_STRONG3),
                    //Ingredient
                    Items.GLOWSTONE_DUST,
                    //Output
                    Potions.MUNDANE
            );

            builder.registerPotionRecipe(
                    //Input
                    Potions.WATER,
                    //Ingredient
                    Items.SLIME_BALL,
                    //Output
                    Potions.MUNDANE
            );
        });
    }
}
