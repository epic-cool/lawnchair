package net.epiccool.lawnchair.datagen;

import net.epiccool.lawnchair.block.ModBlocks;
import net.epiccool.lawnchair.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.data.recipe.StonecuttingRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class LawnchairRecipeProvider extends FabricRecipeProvider {
    public LawnchairRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {

                createShaped(RecipeCategory.TOOLS, ModItems.ICE_PICK)
                        .pattern(" 0")
                        .pattern("1 ")
                        .input('0', Items.SNOWBALL)
                        .input('1', Items.STICK)
                        .criterion(hasItem(Items.SNOWBALL), conditionsFromItem(Items.SNOWBALL))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.DECORATIONS, Items.LADDER, 9)
                        .pattern("000")
                        .pattern("000")
                        .pattern("000")
                        .input('0', Items.LADDER)
                        .criterion(hasItem(Items.LADDER), conditionsFromItem(Items.LADDER))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHARCOAL_BLOCK, 1)
                        .pattern("000")
                        .pattern("000")
                        .pattern("000")
                        .input('0', Items.CHARCOAL)
                        .criterion(hasItem(Items.CHARCOAL), conditionsFromItem(Items.CHARCOAL))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.BUILDING_BLOCKS, Items.CHARCOAL, 9)
                        .input(ModBlocks.CHARCOAL_BLOCK)
                        .criterion(hasItem(ModBlocks.CHARCOAL_BLOCK), conditionsFromItem(ModBlocks.CHARCOAL_BLOCK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STEEL_BLOCK, 1)
                        .pattern("000")
                        .pattern("000")
                        .pattern("000")
                        .input('0', ModItems.STEEL_INGOT)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.BUILDING_BLOCKS, ModItems.STEEL_INGOT, 9)
                        .input(ModBlocks.STEEL_BLOCK)
                        .criterion(hasItem(ModBlocks.STEEL_BLOCK), conditionsFromItem(ModBlocks.STEEL_BLOCK))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, Items.PUMPKIN_SEEDS, 1)
                        .input(ModItems.PUMPKIN_SLICE)
                        .criterion(hasItem(ModItems.PUMPKIN_SLICE), conditionsFromItem(ModItems.PUMPKIN_SLICE))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, Items.MELON_SEEDS, 9)
                        .input(Blocks.MELON)
                        .criterion(hasItem(Blocks.MELON), conditionsFromItem(Blocks.MELON))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, Blocks.PUMPKIN, 1)
                        .pattern("000")
                        .pattern("000")
                        .pattern("000")
                        .input('0', ModItems.PUMPKIN_SLICE)
                        .criterion(hasItem(ModItems.PUMPKIN_SLICE), conditionsFromItem(ModItems.PUMPKIN_SLICE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_CHAIN_BLOCK, 1)
                        .pattern("000")
                        .pattern("000")
                        .pattern("000")
                        .input('0', Items.IRON_CHAIN)
                        .criterion(hasItem(Items.IRON_CHAIN), conditionsFromItem(Items.IRON_CHAIN))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.BUILDING_BLOCKS, Items.IRON_CHAIN, 9)
                        .input(ModBlocks.IRON_CHAIN_BLOCK)
                        .criterion(hasItem(ModBlocks.IRON_CHAIN_BLOCK), conditionsFromItem(ModBlocks.IRON_CHAIN_BLOCK))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.BUILDING_BLOCKS, Items.GRAVEL, 4)
                        .input(Items.COBBLESTONE)
                        .criterion(hasItem(Items.COBBLESTONE), conditionsFromItem(Items.COBBLESTONE))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, Items.BLACK_DYE, 64)
                        .input(Items.DRAGON_EGG)
                        .criterion(hasItem(Items.DRAGON_EGG), conditionsFromItem(Items.DRAGON_EGG))
                        .offerTo(exporter);

                StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItem(Items.GRAVEL), RecipeCategory.BUILDING_BLOCKS, Items.COBBLESTONE, 1);

                SmithingTransformRecipeJsonBuilder.create(
                                Ingredient.ofItem(Items.COAL),
                                Ingredient.ofItem(Items.IRON_INGOT),
                                Ingredient.ofItem(Items.IRON_INGOT),
                                RecipeCategory.MISC,
                                ModItems.STEEL_INGOT
                        )
                        .criterion(hasItem(Items.COAL), conditionsFromItem(Items.COAL))
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(this.exporter, getItemPath(ModItems.STEEL_INGOT) + "_smithing");
            }
        };
    }

    @Override
    public String getName() {
        return "LawnchairRecipeProvider";
    }
}
