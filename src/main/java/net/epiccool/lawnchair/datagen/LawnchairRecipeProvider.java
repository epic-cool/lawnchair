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

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STEEL_BARS, 16)
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


                //Copper chains
                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COPPER_CHAIN_BLOCK, 1)
                        .pattern("000")
                        .pattern("000")
                        .pattern("000")
                        .input('0', Items.COPPER_CHAINS.unaffected())
                        .criterion(hasItem(Items.COPPER_CHAINS.unaffected()), conditionsFromItem(Items.COPPER_CHAINS.unaffected()))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.EXPOSED_COPPER_CHAIN_BLOCK, 1)
                        .pattern("000")
                        .pattern("000")
                        .pattern("000")
                        .input('0', Items.COPPER_CHAINS.exposed())
                        .criterion(hasItem(Items.COPPER_CHAINS.exposed()), conditionsFromItem(Items.COPPER_CHAINS.exposed()))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WEATHERED_COPPER_CHAIN_BLOCK, 1)
                        .pattern("000")
                        .pattern("000")
                        .pattern("000")
                        .input('0', Items.COPPER_CHAINS.weathered())
                        .criterion(hasItem(Items.COPPER_CHAINS.weathered()), conditionsFromItem(Items.COPPER_CHAINS.weathered()))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.OXIDIZED_COPPER_CHAIN_BLOCK, 1)
                        .pattern("000")
                        .pattern("000")
                        .pattern("000")
                        .input('0', Items.COPPER_CHAINS.oxidized())
                        .criterion(hasItem(Items.COPPER_CHAINS.oxidized()), conditionsFromItem(Items.COPPER_CHAINS.oxidized()))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WAXED_COPPER_CHAIN_BLOCK, 1)
                        .pattern("000")
                        .pattern("000")
                        .pattern("000")
                        .input('0', Items.COPPER_CHAINS.waxed())
                        .criterion(hasItem(Items.COPPER_CHAINS.waxed()), conditionsFromItem(Items.COPPER_CHAINS.waxed()))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WAXED_EXPOSED_COPPER_CHAIN_BLOCK, 1)
                        .pattern("000")
                        .pattern("000")
                        .pattern("000")
                        .input('0', Items.COPPER_CHAINS.waxedExposed())
                        .criterion(hasItem(Items.COPPER_CHAINS.waxedExposed()), conditionsFromItem(Items.COPPER_CHAINS.waxedExposed()))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WAXED_WEATHERED_COPPER_CHAIN_BLOCK, 1)
                        .pattern("000")
                        .pattern("000")
                        .pattern("000")
                        .input('0', Items.COPPER_CHAINS.waxedWeathered())
                        .criterion(hasItem(Items.COPPER_CHAINS.waxedWeathered()), conditionsFromItem(Items.COPPER_CHAINS.waxedWeathered()))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN_BLOCK, 1)
                        .pattern("000")
                        .pattern("000")
                        .pattern("000")
                        .input('0', Items.COPPER_CHAINS.waxedOxidized())
                        .criterion(hasItem(Items.COPPER_CHAINS.waxedOxidized()), conditionsFromItem(Items.COPPER_CHAINS.waxedOxidized()))
                        .offerTo(exporter);


                createShapeless(RecipeCategory.BUILDING_BLOCKS, Items.COPPER_CHAINS.unaffected(), 9)
                        .input(ModBlocks.COPPER_CHAIN_BLOCK)
                        .criterion(hasItem(ModBlocks.COPPER_CHAIN_BLOCK), conditionsFromItem(ModBlocks.COPPER_CHAIN_BLOCK))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.BUILDING_BLOCKS, Items.COPPER_CHAINS.exposed(), 9)
                        .input(ModBlocks.EXPOSED_COPPER_CHAIN_BLOCK)
                        .criterion(hasItem(ModBlocks.EXPOSED_COPPER_CHAIN_BLOCK), conditionsFromItem(ModBlocks.EXPOSED_COPPER_CHAIN_BLOCK))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.BUILDING_BLOCKS, Items.COPPER_CHAINS.weathered(), 9)
                        .input(ModBlocks.WEATHERED_COPPER_CHAIN_BLOCK)
                        .criterion(hasItem(ModBlocks.WEATHERED_COPPER_CHAIN_BLOCK), conditionsFromItem(ModBlocks.WEATHERED_COPPER_CHAIN_BLOCK))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.BUILDING_BLOCKS, Items.COPPER_CHAINS.oxidized(), 9)
                        .input(ModBlocks.OXIDIZED_COPPER_CHAIN_BLOCK)
                        .criterion(hasItem(ModBlocks.OXIDIZED_COPPER_CHAIN_BLOCK), conditionsFromItem(ModBlocks.OXIDIZED_COPPER_CHAIN_BLOCK))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.BUILDING_BLOCKS, Items.COPPER_CHAINS.waxed(), 9)
                        .input(ModBlocks.WAXED_COPPER_CHAIN_BLOCK)
                        .criterion(hasItem(ModBlocks.WAXED_COPPER_CHAIN_BLOCK), conditionsFromItem(ModBlocks.WAXED_COPPER_CHAIN_BLOCK))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.BUILDING_BLOCKS, Items.COPPER_CHAINS.waxedExposed(), 9)
                        .input(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN_BLOCK)
                        .criterion(hasItem(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN_BLOCK), conditionsFromItem(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN_BLOCK))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.BUILDING_BLOCKS, Items.COPPER_CHAINS.waxedWeathered(), 9)
                        .input(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN_BLOCK)
                        .criterion(hasItem(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN_BLOCK), conditionsFromItem(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN_BLOCK))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.BUILDING_BLOCKS, Items.COPPER_CHAINS.waxedOxidized(), 9)
                        .input(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN_BLOCK)
                        .criterion(hasItem(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN_BLOCK), conditionsFromItem(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN_BLOCK))
                        .offerTo(exporter);


                createShaped(RecipeCategory.COMBAT, ModItems.STEEL_SWORD, 1)
                        .pattern("0")
                        .pattern("0")
                        .pattern("1")
                        .input('0', ModItems.STEEL_INGOT)
                        .input('1', Items.STICK)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.STEEL_AXE, 1)
                        .pattern("00")
                        .pattern("10")
                        .pattern("1 ")
                        .input('0', ModItems.STEEL_INGOT)
                        .input('1', Items.STICK)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ModItems.STEEL_PICKAXE, 1)
                        .pattern("000")
                        .pattern(" 1 ")
                        .pattern(" 1 ")
                        .input('0', ModItems.STEEL_INGOT)
                        .input('1', Items.STICK)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ModItems.STEEL_SHOVEL, 1)
                        .pattern("0")
                        .pattern("1")
                        .pattern("1")
                        .input('0', ModItems.STEEL_INGOT)
                        .input('1', Items.STICK)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ModItems.STEEL_HOE, 1)
                        .pattern("00")
                        .pattern("1 ")
                        .pattern("1 ")
                        .input('0', ModItems.STEEL_INGOT)
                        .input('1', Items.STICK)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.STEEL_HELMET, 1)
                        .pattern("000")
                        .pattern("0 0")
                        .input('0', ModItems.STEEL_INGOT)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.STEEL_CHESTPLATE, 1)
                        .pattern("0 0")
                        .pattern("000")
                        .pattern("000")
                        .input('0', ModItems.STEEL_INGOT)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.STEEL_LEGGINGS, 1)
                        .pattern("000")
                        .pattern("0 0")
                        .pattern("0 0")
                        .input('0', ModItems.STEEL_INGOT)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.STEEL_BOOTS, 1)
                        .pattern("0 0")
                        .pattern("0 0")
                        .input('0', ModItems.STEEL_INGOT)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.UNLIT_TORCH, 4)
                        .pattern("0")
                        .pattern("1")
                        .input('0', Items.CHARCOAL)
                        .input('1', Items.STICK)
                        .criterion(hasItem(Items.CHARCOAL), conditionsFromItem(Items.CHARCOAL))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOUL_JACK_O_LANTERN, 1)
                        .pattern("0")
                        .pattern("1")
                        .input('0', Items.CARVED_PUMPKIN)
                        .input('1', Items.SOUL_LANTERN)
                        .criterion(hasItem(Items.CARVED_PUMPKIN), conditionsFromItem(Items.CARVED_PUMPKIN))
                        .criterion(hasItem(Items.SOUL_LANTERN), conditionsFromItem(Items.SOUL_LANTERN))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.UNLIT_LANTERN, 1)
                        .pattern("000")
                        .pattern("010")
                        .pattern("000")
                        .input('0', Items.IRON_NUGGET)
                        .input('1', ModItems.UNLIT_TORCH)
                        .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                        .criterion(hasItem(ModItems.UNLIT_TORCH), conditionsFromItem(ModItems.UNLIT_TORCH))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, Items.NETHER_WART, 9)
                        .input(Blocks.NETHER_WART_BLOCK)
                        .criterion(hasItem(Blocks.NETHER_WART_BLOCK), conditionsFromItem(Blocks.NETHER_WART_BLOCK))
                        .offerTo(exporter);

//                createShaped(RecipeCategory.MISC, ModBlocks.ALLOY_MIXER, 1)
//                        .pattern("000")
//                        .pattern("010")
//                        .pattern("000")
//                        .input('0', Items.IRON_NUGGET)
//                        .input('1', Items.CRAFTING_TABLE)
//                        .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
//                        .criterion(hasItem(Items.CRAFTING_TABLE), conditionsFromItem(Items.CRAFTING_TABLE))
//                        .offerTo(exporter);

                //Robots
                createShaped(RecipeCategory.MISC, ModItems.ROBOT_CORE, 1)
                        .pattern("010")
                        .pattern("121")
                        .pattern("010")
                        .input('0', Items.REDSTONE)
                        .input('1', Blocks.IRON_BLOCK)
                        .input('2', ModBlocks.STEEL_BLOCK)
                        .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                        .criterion(hasItem(Blocks.IRON_BLOCK), conditionsFromItem(Blocks.IRON_BLOCK))
                        .criterion(hasItem(ModBlocks.STEEL_BLOCK), conditionsFromItem(ModBlocks.STEEL_BLOCK))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModBlocks.IRON_BLOCK_WITH_ROBOT_CORE, 1)
                        .input(Blocks.IRON_BLOCK)
                        .input(ModItems.ROBOT_CORE)
                        .criterion(hasItem(Blocks.IRON_BLOCK), conditionsFromItem(Blocks.IRON_BLOCK))
                        .criterion(hasItem(ModItems.ROBOT_CORE), conditionsFromItem(ModItems.ROBOT_CORE))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "LawnchairRecipeProvider";
    }
}
