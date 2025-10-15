//package net.epiccool.lawnchair.recipe;
//
//import net.epiccool.lawnchair.Lawnchair;
//import net.minecraft.recipe.RecipeSerializer;
//import net.minecraft.recipe.RecipeType;
//import net.minecraft.registry.Registries;
//import net.minecraft.registry.Registry;
//import net.minecraft.util.Identifier;
//
//public class ModRecipes {
//    public static final RecipeSerializer<AlloyMixerRecipe> ALLOY_MIXER_SERIALIZER = Registry.register(
//            Registries.RECIPE_SERIALIZER, Identifier.of(Lawnchair.MODID, "alloy_mixer"),
//            new AlloyMixerRecipe.Serializer());
//    public static final RecipeType<AlloyMixerRecipe> ALLOY_MIXER_TYPE = Registry.register(
//            Registries.RECIPE_TYPE, Identifier.of(Lawnchair.MODID, "alloy_mixer"), new RecipeType<AlloyMixerRecipe>() {
//                @Override
//                public String toString() {
//                    return "alloy_mixer";
//                }
//            });
//
//    public static void registerRecipes() {
//        Lawnchair.LOGGER.info("Registering Custom Recipes for " + Lawnchair.MODID);
//    }
//}
