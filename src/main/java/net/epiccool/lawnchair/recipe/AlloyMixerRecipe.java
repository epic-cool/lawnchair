//package net.epiccool.lawnchair.recipe;
//
//import com.mojang.serialization.MapCodec;
//import com.mojang.serialization.codecs.RecordCodecBuilder;
//import net.minecraft.item.ItemStack;
//import net.minecraft.network.RegistryByteBuf;
//import net.minecraft.network.codec.PacketCodec;
//import net.minecraft.recipe.*;
//import net.minecraft.recipe.book.RecipeBookCategories;
//import net.minecraft.recipe.book.RecipeBookCategory;
//import net.minecraft.registry.RegistryWrapper;
//import net.minecraft.util.collection.DefaultedList;
//import net.minecraft.world.World;
//
//public record AlloyMixerRecipe(Ingredient inputItem, ItemStack output) implements Recipe<AlloyMixerRecipeInput> {
//    public DefaultedList<Ingredient> getIngredients() {
//        DefaultedList<Ingredient> list = DefaultedList.of();
//        list.add(this.inputItem);
//        return list;
//    }
//
//    // read Recipe JSON files --> new AlloyMixerRecipe
//
//    @Override
//    public boolean matches(AlloyMixerRecipeInput input, World world) {
//        if(world.isClient()) {
//            return false;
//        }
//
//        return inputItem.test(input.getStackInSlot(0));
//    }
//
//    @Override
//    public ItemStack craft(AlloyMixerRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
//        return output.copy();
//    }
//
//    @Override
//    public RecipeSerializer<? extends Recipe<AlloyMixerRecipeInput>> getSerializer() {
//        return ModRecipes.ALLOY_MIXER_SERIALIZER;
//    }
//
//    @Override
//    public RecipeType<? extends Recipe<AlloyMixerRecipeInput>> getType() {
//        return ModRecipes.ALLOY_MIXER_TYPE;
//    }
//
//    @Override
//    public IngredientPlacement getIngredientPlacement() {
//        return IngredientPlacement.forSingleSlot(inputItem);
//    }
//
//    @Override
//    public RecipeBookCategory getRecipeBookCategory() {
//        return RecipeBookCategories.CRAFTING_MISC;
//    }
//
//    public static class Serializer implements RecipeSerializer<AlloyMixerRecipe> {
//        public static final MapCodec<AlloyMixerRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
//                Ingredient.CODEC.fieldOf("ingredient").forGetter(AlloyMixerRecipe::inputItem),
//                ItemStack.CODEC.fieldOf("result").forGetter(AlloyMixerRecipe::output)
//        ).apply(inst, AlloyMixerRecipe::new));
//
//        public static final PacketCodec<RegistryByteBuf, AlloyMixerRecipe> STREAM_CODEC =
//                PacketCodec.tuple(
//                        Ingredient.PACKET_CODEC, AlloyMixerRecipe::inputItem,
//                        ItemStack.PACKET_CODEC, AlloyMixerRecipe::output,
//                        AlloyMixerRecipe::new);
//
//        @Override
//        public MapCodec<AlloyMixerRecipe> codec() {
//            return CODEC;
//        }
//
//        @Override
//        public PacketCodec<RegistryByteBuf, AlloyMixerRecipe> packetCodec() {
//            return STREAM_CODEC;
//        }
//    }
//}