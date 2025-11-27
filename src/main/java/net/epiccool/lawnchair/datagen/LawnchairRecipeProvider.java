package net.epiccool.lawnchair.datagen;

import net.epiccool.lawnchair.block.ModBlocks;
import net.epiccool.lawnchair.item.ModItems;
import net.epiccool.lawnchair.util.ModTags;
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
import net.minecraft.registry.tag.ItemTags;

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

                createShaped(RecipeCategory.COMBAT, ModItems.WOOD_HELMET, 1)
                        .pattern("000")
                        .pattern("0 0")
                        .input('0', ItemTags.LOGS)
                        .criterion(hasItem(Items.OAK_LOG), conditionsFromItem(Items.OAK_LOG))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.WOOD_CHESTPLATE, 1)
                        .pattern("0 0")
                        .pattern("000")
                        .pattern("000")
                        .input('0', ItemTags.LOGS)
                        .criterion(hasItem(Items.OAK_LOG), conditionsFromItem(Items.OAK_LOG))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.WOOD_LEGGINGS, 1)
                        .pattern("000")
                        .pattern("0 0")
                        .pattern("0 0")
                        .input('0', ItemTags.LOGS)
                        .criterion(hasItem(Items.OAK_LOG), conditionsFromItem(Items.OAK_LOG))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.WOOD_BOOTS, 1)
                        .pattern("0 0")
                        .pattern("0 0")
                        .input('0', ItemTags.LOGS)
                        .criterion(hasItem(Items.OAK_LOG), conditionsFromItem(Items.OAK_LOG))
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
                        .input('1', ModBlocks.UNLIT_TORCH)
                        .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                        .criterion(hasItem(ModBlocks.UNLIT_TORCH), conditionsFromItem(ModBlocks.UNLIT_TORCH))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.EVIL_GOOP_FRAGMENT, 1)
                        .pattern("000")
                        .pattern("000")
                        .pattern("000")
                        .input('0', Items.ROTTEN_FLESH)
                        .criterion(hasItem(Items.ROTTEN_FLESH), conditionsFromItem(Items.ROTTEN_FLESH))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.EVIL_GOOP, 1)
                        .pattern("00")
                        .pattern("00")
                        .input('0', ModItems.EVIL_GOOP_FRAGMENT)
                        .criterion(hasItem(ModItems.EVIL_GOOP_FRAGMENT), conditionsFromItem(ModItems.EVIL_GOOP_FRAGMENT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.GAS_MASK, 1)
                        .pattern(" 0 ")
                        .pattern("010")
                        .pattern(" 0 ")
                        .input('0', Items.IRON_INGOT)
                        .input('1', Items.LEATHER_HELMET)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .criterion(hasItem(Items.LEATHER_HELMET), conditionsFromItem(Items.LEATHER_HELMET))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, Items.NETHER_WART, 9)
                        .input(Blocks.NETHER_WART_BLOCK)
                        .criterion(hasItem(Blocks.NETHER_WART_BLOCK), conditionsFromItem(Blocks.NETHER_WART_BLOCK))
                        .offerTo(exporter);

                //stone -> deepslate
                createShapeless(RecipeCategory.BUILDING_BLOCKS, Items.DEEPSLATE, 1)
                        .input(Blocks.STONE)
                        .input(Items.BLACK_DYE)
                        .criterion(hasItem(Blocks.DEEPSLATE), conditionsFromItem(Blocks.DEEPSLATE))
                        .criterion(hasItem(Items.BLACK_DYE), conditionsFromItem(Items.BLACK_DYE))
                        .offerTo(exporter);

                //deepslate -> stone
                createShapeless(RecipeCategory.BUILDING_BLOCKS, Items.STONE, 1)
                        .input(Blocks.DEEPSLATE)
                        .input(Items.WHITE_DYE)
                        .criterion(hasItem(Blocks.DEEPSLATE), conditionsFromItem(Blocks.DEEPSLATE))
                        .criterion(hasItem(Items.WHITE_DYE), conditionsFromItem(Items.WHITE_DYE))
                        .offerTo(exporter);

                //cobblestone -> cobbled deepslate
                createShapeless(RecipeCategory.BUILDING_BLOCKS, Items.COBBLED_DEEPSLATE, 1)
                        .input(Blocks.COBBLESTONE)
                        .input(Items.BLACK_DYE)
                        .criterion(hasItem(Blocks.COBBLESTONE), conditionsFromItem(Blocks.COBBLESTONE))
                        .criterion(hasItem(Items.BLACK_DYE), conditionsFromItem(Items.BLACK_DYE))
                        .offerTo(exporter);

                //cobbled deepslate -> cobblestone
                createShapeless(RecipeCategory.BUILDING_BLOCKS, Items.COBBLESTONE, 1)
                        .input(Blocks.COBBLED_DEEPSLATE)
                        .input(Items.WHITE_DYE)
                        .criterion(hasItem(Blocks.COBBLED_DEEPSLATE), conditionsFromItem(Blocks.COBBLED_DEEPSLATE))
                        .criterion(hasItem(Items.WHITE_DYE), conditionsFromItem(Items.WHITE_DYE))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, Blocks.SPONGE, 4)
                        .input(Blocks.YELLOW_WOOL)
                        .input(Blocks.YELLOW_WOOL)
                        .input(Blocks.YELLOW_WOOL)
                        .input(Items.CACTUS)
                        .input(Items.KELP)
                        .criterion(hasItem(Blocks.YELLOW_WOOL), conditionsFromItem(Blocks.YELLOW_WOOL))
                        .criterion(hasItem(Items.KELP), conditionsFromItem(Items.KELP))
                        .criterion(hasItem(Items.CACTUS), conditionsFromItem(Items.CACTUS))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, Blocks.WET_SPONGE, 4)
                        .input(Blocks.YELLOW_WOOL)
                        .input(Blocks.YELLOW_WOOL)
                        .input(Blocks.YELLOW_WOOL)
                        .input(Items.CACTUS)
                        .input(Items.KELP)
                        .input(Items.WATER_BUCKET)
                        .criterion(hasItem(Blocks.YELLOW_WOOL), conditionsFromItem(Blocks.YELLOW_WOOL))
                        .criterion(hasItem(Items.KELP), conditionsFromItem(Items.KELP))
                        .criterion(hasItem(Items.BUCKET), conditionsFromItem(Items.BUCKET))
                        .criterion(hasItem(Items.WATER_BUCKET), conditionsFromItem(Items.WATER_BUCKET))
                        .criterion(hasItem(Items.CACTUS), conditionsFromItem(Items.CACTUS))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, Blocks.GRASS_BLOCK, 1)
                        .input(Blocks.DIRT)
                        .input(Items.WHEAT_SEEDS)
                        .criterion(hasItem(Blocks.DIRT), conditionsFromItem(Blocks.DIRT))
                        .criterion(hasItem(Items.WHEAT_SEEDS), conditionsFromItem(Items.WHEAT_SEEDS))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.TOOLS, Items.FLINT_AND_STEEL, 1)
                        .input(ModItems.STEEL_INGOT)
                        .input(Items.FLINT)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .criterion(hasItem(Items.FLINT), conditionsFromItem(Items.FLINT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.SUGAR_CUBE, 1)
                        .pattern("00")
                        .pattern("00")
                        .input('0', Items.SUGAR)
                        .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
                        .offerTo(exporter);

                //Candy
                //Wrapped Hard
                createShaped(RecipeCategory.MISC, ModItems.BLUE_WRAPPED_HARD_CANDY, 16)
                        .pattern(" 0 ")
                        .pattern("010")
                        .pattern(" 02")
                        .input('0', Items.SUGAR)
                        .input('1', Items.HONEY_BOTTLE)
                        .input('2', Items.BLUE_DYE)
                        .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
                        .criterion(hasItem(Items.HONEY_BOTTLE), conditionsFromItem(Items.HONEY_BOTTLE))
                        .criterion(hasItem(Items.BLUE_DYE), conditionsFromItem(Items.BLUE_DYE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.RED_WRAPPED_HARD_CANDY, 16)
                        .pattern(" 0 ")
                        .pattern("010")
                        .pattern(" 02")
                        .input('0', Items.SUGAR)
                        .input('1', Items.HONEY_BOTTLE)
                        .input('2', Items.RED_DYE)
                        .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
                        .criterion(hasItem(Items.HONEY_BOTTLE), conditionsFromItem(Items.HONEY_BOTTLE))
                        .criterion(hasItem(Items.RED_DYE), conditionsFromItem(Items.RED_DYE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.YELLOW_WRAPPED_HARD_CANDY, 16)
                        .pattern(" 0 ")
                        .pattern("010")
                        .pattern(" 02")
                        .input('0', Items.SUGAR)
                        .input('1', Items.HONEY_BOTTLE)
                        .input('2', Items.YELLOW_DYE)
                        .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
                        .criterion(hasItem(Items.HONEY_BOTTLE), conditionsFromItem(Items.HONEY_BOTTLE))
                        .criterion(hasItem(Items.YELLOW_DYE), conditionsFromItem(Items.YELLOW_DYE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.GREEN_WRAPPED_HARD_CANDY, 16)
                        .pattern(" 0 ")
                        .pattern("010")
                        .pattern(" 02")
                        .input('0', Items.SUGAR)
                        .input('1', Items.HONEY_BOTTLE)
                        .input('2', Items.GREEN_DYE)
                        .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
                        .criterion(hasItem(Items.HONEY_BOTTLE), conditionsFromItem(Items.HONEY_BOTTLE))
                        .criterion(hasItem(Items.GREEN_DYE), conditionsFromItem(Items.GREEN_DYE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.PURPLE_WRAPPED_HARD_CANDY, 16)
                        .pattern(" 0 ")
                        .pattern("010")
                        .pattern(" 02")
                        .input('0', Items.SUGAR)
                        .input('1', Items.HONEY_BOTTLE)
                        .input('2', Items.PURPLE_DYE)
                        .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
                        .criterion(hasItem(Items.HONEY_BOTTLE), conditionsFromItem(Items.HONEY_BOTTLE))
                        .criterion(hasItem(Items.PURPLE_DYE), conditionsFromItem(Items.PURPLE_DYE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.ORANGE_WRAPPED_HARD_CANDY, 16)
                        .pattern(" 0 ")
                        .pattern("010")
                        .pattern(" 02")
                        .input('0', Items.SUGAR)
                        .input('1', Items.HONEY_BOTTLE)
                        .input('2', Items.ORANGE_DYE)
                        .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
                        .criterion(hasItem(Items.HONEY_BOTTLE), conditionsFromItem(Items.HONEY_BOTTLE))
                        .criterion(hasItem(Items.ORANGE_DYE), conditionsFromItem(Items.ORANGE_DYE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.PINK_WRAPPED_HARD_CANDY, 16)
                        .pattern(" 0 ")
                        .pattern("010")
                        .pattern(" 02")
                        .input('0', Items.SUGAR)
                        .input('1', Items.HONEY_BOTTLE)
                        .input('2', Items.PINK_DYE)
                        .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
                        .criterion(hasItem(Items.HONEY_BOTTLE), conditionsFromItem(Items.HONEY_BOTTLE))
                        .criterion(hasItem(Items.PINK_DYE), conditionsFromItem(Items.PINK_DYE))
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


                //Wooden recipes
                //Azalea
                //todo: placeholder
                createShaped(RecipeCategory.MISC, Items.OAK_BOAT, 1)
                        .pattern("0 0")
                        .pattern("000")
                        .input('0', ModBlocks.AZALEA_PLANKS)
                        .criterion(hasItem(ModBlocks.AZALEA_PLANKS), conditionsFromItem(ModBlocks.AZALEA_PLANKS))
                        .offerTo(exporter);

                //todo: placeholder
                createShapeless(RecipeCategory.REDSTONE, Items.OAK_BUTTON, 1)
                        .input(ModBlocks.AZALEA_PLANKS)
                        .criterion(hasItem(ModBlocks.AZALEA_PLANKS), conditionsFromItem(ModBlocks.AZALEA_PLANKS))
                        .offerTo(exporter);

                //todo: placeholder
                createShapeless(RecipeCategory.MISC, Items.OAK_CHEST_BOAT, 1)
                        .input(ModBlocks.AZALEA_PLANKS)
                        .input(Blocks.CHEST)
                        .criterion(hasItem(ModBlocks.AZALEA_PLANKS), conditionsFromItem(ModBlocks.AZALEA_PLANKS))
                        .criterion(hasItem(Blocks.CHEST), conditionsFromItem(Blocks.CHEST))
                        .offerTo(exporter);

                //todo: placeholder
                createShaped(RecipeCategory.REDSTONE, Items.OAK_DOOR, 3)
                        .pattern("00")
                        .pattern("00")
                        .pattern("00")
                        .input('0', ModBlocks.AZALEA_PLANKS)
                        .criterion(hasItem(ModBlocks.AZALEA_PLANKS), conditionsFromItem(ModBlocks.AZALEA_PLANKS))
                        .offerTo(exporter);

                //todo: placeholder
                createShaped(RecipeCategory.MISC, Items.OAK_FENCE, 3)
                        .pattern("010")
                        .pattern("010")
                        .input('0', ModBlocks.AZALEA_PLANKS)
                        .input('1', Items.STICK)
                        .criterion(hasItem(ModBlocks.AZALEA_PLANKS), conditionsFromItem(ModBlocks.AZALEA_PLANKS))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                //todo: placeholder
                createShaped(RecipeCategory.REDSTONE, Items.OAK_FENCE_GATE, 1)
                        .pattern("010")
                        .pattern("010")
                        .input('0', Items.STICK)
                        .input('1', ModBlocks.AZALEA_PLANKS)
                        .criterion(hasItem(ModBlocks.AZALEA_PLANKS), conditionsFromItem(ModBlocks.AZALEA_PLANKS))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                //todo: placeholder
                createShaped(RecipeCategory.MISC, Items.OAK_HANGING_SIGN, 6)
                        .pattern("0 0")
                        .pattern("111")
                        .pattern("111")
                        .input('0', Items.STICK)
                        .input('1', ModBlocks.AZALEA_PLANKS)
                        .criterion(hasItem(ModBlocks.AZALEA_PLANKS), conditionsFromItem(ModBlocks.AZALEA_PLANKS))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.AZALEA_PLANKS, 4)
                        .pattern("00")
                        .pattern("00")
                        .input('0', ModTags.Items.AZALEA_LOGS)
                        .criterion(hasItem(ModBlocks.AZALEA_WOOD), conditionsFromItem(ModBlocks.AZALEA_WOOD))
                        .criterion(hasItem(ModBlocks.AZALEA_LOG), conditionsFromItem(ModBlocks.AZALEA_LOG))
                        .criterion(hasItem(ModBlocks.STRIPPED_AZALEA_WOOD), conditionsFromItem(ModBlocks.STRIPPED_AZALEA_WOOD))
                        .criterion(hasItem(ModBlocks.STRIPPED_AZALEA_LOG), conditionsFromItem(ModBlocks.STRIPPED_AZALEA_LOG))
                        .offerTo(exporter);

                //todo: placeholder
                createShaped(RecipeCategory.REDSTONE, Items.OAK_PRESSURE_PLATE, 1)
                        .pattern("00")
                        .input('0', ModBlocks.AZALEA_PLANKS)
                        .criterion(hasItem(ModBlocks.AZALEA_PLANKS), conditionsFromItem(ModBlocks.AZALEA_PLANKS))
                        .offerTo(exporter);

                //todo: placeholder
                createShaped(RecipeCategory.MISC, Items.OAK_SIGN, 3)
                        .pattern("000")
                        .pattern("000")
                        .pattern(" 1 ")
                        .input('0', ModBlocks.AZALEA_PLANKS)
                        .input('1', Items.STICK)
                        .criterion(hasItem(ModBlocks.AZALEA_PLANKS), conditionsFromItem(ModBlocks.AZALEA_PLANKS))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                //todo: placeholder
                createShaped(RecipeCategory.BUILDING_BLOCKS, Items.OAK_SLAB, 6)
                        .pattern("000")
                        .input('0', ModBlocks.AZALEA_PLANKS)
                        .criterion(hasItem(ModBlocks.AZALEA_PLANKS), conditionsFromItem(ModBlocks.AZALEA_PLANKS))
                        .offerTo(exporter);

                //todo: placeholder
                createShaped(RecipeCategory.BUILDING_BLOCKS, Items.OAK_STAIRS, 4)
                        .pattern("0  ")
                        .pattern("00 ")
                        .pattern("000")
                        .input('0', ModBlocks.AZALEA_PLANKS)
                        .criterion(hasItem(ModBlocks.AZALEA_PLANKS), conditionsFromItem(ModBlocks.AZALEA_PLANKS))
                        .offerTo(exporter);

                //todo: placeholder
                createShaped(RecipeCategory.REDSTONE, Items.OAK_TRAPDOOR, 2)
                        .pattern("000")
                        .pattern("000")
                        .input('0', ModBlocks.AZALEA_PLANKS)
                        .criterion(hasItem(ModBlocks.AZALEA_PLANKS), conditionsFromItem(ModBlocks.AZALEA_PLANKS))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.AZALEA_WOOD, 3)
                        .pattern("00")
                        .pattern("00")
                        .input('0', ModBlocks.AZALEA_LOG)
                        .criterion(hasItem(ModBlocks.AZALEA_LOG), conditionsFromItem(ModBlocks.AZALEA_LOG))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_AZALEA_WOOD, 3)
                        .pattern("00")
                        .pattern("00")
                        .input('0', ModBlocks.STRIPPED_AZALEA_LOG)
                        .criterion(hasItem(ModBlocks.STRIPPED_AZALEA_LOG), conditionsFromItem(ModBlocks.STRIPPED_AZALEA_LOG))
                        .offerTo(exporter);


                //Unblock Recipes
                //TNT
                createShaped(RecipeCategory.MISC, ModBlocks.TNT_SLAB, 6)
                        .pattern("000")
                        .input('0', Blocks.TNT)
                        .criterion(hasItem(Blocks.TNT), conditionsFromItem(Blocks.TNT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.TNT_STAIRS, 4)
                        .pattern("0  ")
                        .pattern("00 ")
                        .pattern("000")
                        .input('0', Blocks.TNT)
                        .criterion(hasItem(Blocks.TNT), conditionsFromItem(Blocks.TNT))
                        .offerTo(exporter);

                //dirt
                createShaped(RecipeCategory.MISC, ModBlocks.DIRT_SLAB, 6)
                        .pattern("000")
                        .input('0', Blocks.DIRT)
                        .criterion(hasItem(Blocks.DIRT), conditionsFromItem(Blocks.DIRT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.DIRT_STAIRS, 4)
                        .pattern("0  ")
                        .pattern("00 ")
                        .pattern("000")
                        .input('0', Blocks.DIRT)
                        .criterion(hasItem(Blocks.DIRT), conditionsFromItem(Blocks.DIRT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.COARSE_DIRT_SLAB, 6)
                        .pattern("000")
                        .input('0', Blocks.COARSE_DIRT)
                        .criterion(hasItem(Blocks.COARSE_DIRT), conditionsFromItem(Blocks.COARSE_DIRT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.COARSE_DIRT_STAIRS, 4)
                        .pattern("0  ")
                        .pattern("00 ")
                        .pattern("000")
                        .input('0', Blocks.COARSE_DIRT)
                        .criterion(hasItem(Blocks.COARSE_DIRT), conditionsFromItem(Blocks.COARSE_DIRT))
                        .offerTo(exporter);

                //gravel
                createShaped(RecipeCategory.MISC, ModBlocks.GRAVEL_SLAB, 6)
                        .pattern("000")
                        .input('0', Blocks.GRAVEL)
                        .criterion(hasItem(Blocks.GRAVEL), conditionsFromItem(Blocks.GRAVEL))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.GRAVEL_STAIRS, 4)
                        .pattern("0  ")
                        .pattern("00 ")
                        .pattern("000")
                        .input('0', Blocks.GRAVEL)
                        .criterion(hasItem(Blocks.GRAVEL), conditionsFromItem(Blocks.GRAVEL))
                        .offerTo(exporter);

                //sand
                createShaped(RecipeCategory.MISC, ModBlocks.SAND_SLAB, 6)
                        .pattern("000")
                        .input('0', Blocks.SAND)
                        .criterion(hasItem(Blocks.SAND), conditionsFromItem(Blocks.SAND))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.SAND_STAIRS, 4)
                        .pattern("0  ")
                        .pattern("00 ")
                        .pattern("000")
                        .input('0', Blocks.SAND)
                        .criterion(hasItem(Blocks.SAND), conditionsFromItem(Blocks.SAND))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.RED_SAND_SLAB, 6)
                        .pattern("000")
                        .input('0', Blocks.RED_SAND)
                        .criterion(hasItem(Blocks.RED_SAND), conditionsFromItem(Blocks.RED_SAND))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.RED_SAND_STAIRS, 4)
                        .pattern("0  ")
                        .pattern("00 ")
                        .pattern("000")
                        .input('0', Blocks.RED_SAND)
                        .criterion(hasItem(Blocks.RED_SAND), conditionsFromItem(Blocks.RED_SAND))
                        .offerTo(exporter);

                //quartz bricks
                createShaped(RecipeCategory.MISC, ModBlocks.QUARTZ_BRICKS_SLAB, 6)
                        .pattern("000")
                        .input('0', Blocks.QUARTZ_BRICKS)
                        .criterion(hasItem(Blocks.QUARTZ_BRICKS), conditionsFromItem(Blocks.QUARTZ_BRICKS))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.QUARTZ_BRICKS_STAIRS, 4)
                        .pattern("0  ")
                        .pattern("00 ")
                        .pattern("000")
                        .input('0', Blocks.QUARTZ_BRICKS)
                        .criterion(hasItem(Blocks.QUARTZ_BRICKS), conditionsFromItem(Blocks.QUARTZ_BRICKS))
                        .offerTo(exporter);

                //netherite block
                createShaped(RecipeCategory.MISC, ModBlocks.NETHERITE_BLOCK_SLAB, 6)
                        .pattern("000")
                        .input('0', Blocks.NETHERITE_BLOCK)
                        .criterion(hasItem(Blocks.QUARTZ_BRICKS), conditionsFromItem(Blocks.NETHERITE_BLOCK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.NETHERITE_BLOCK_STAIRS, 4)
                        .pattern("0  ")
                        .pattern("00 ")
                        .pattern("000")
                        .input('0', Blocks.NETHERITE_BLOCK)
                        .criterion(hasItem(Blocks.NETHERITE_BLOCK), conditionsFromItem(Blocks.NETHERITE_BLOCK))
                        .offerTo(exporter);


                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLUORESCENT_LIGHT, 8)
                        .pattern("000")
                        .pattern("010")
                        .pattern("000")
                        .input('0', Blocks.GLASS)
                        .input('1', Items.GLOWSTONE_DUST)
                        .criterion(hasItem(Blocks.GLASS), conditionsFromItem(Blocks.GLASS))
                        .criterion(hasItem(Items.GLOWSTONE_DUST), conditionsFromItem(Items.GLOWSTONE_DUST))
                        .offerTo(exporter);

                //Copper Golem Statues
                createShaped(RecipeCategory.MISC, Blocks.COPPER_GOLEM_STATUE, 1)
                        .pattern("0")
                        .pattern("1")
                        .input('0', Blocks.CARVED_PUMPKIN)
                        .input('1', Blocks.COPPER_BLOCK)
                        .criterion(hasItem(Blocks.CARVED_PUMPKIN), conditionsFromItem(Blocks.CARVED_PUMPKIN))
                        .criterion(hasItem(Blocks.COPPER_BLOCK), conditionsFromItem(Blocks.COPPER_BLOCK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, Blocks.EXPOSED_COPPER_GOLEM_STATUE, 1)
                        .pattern("0")
                        .pattern("1")
                        .input('0', Blocks.CARVED_PUMPKIN)
                        .input('1', Blocks.EXPOSED_COPPER)
                        .criterion(hasItem(Blocks.CARVED_PUMPKIN), conditionsFromItem(Blocks.CARVED_PUMPKIN))
                        .criterion(hasItem(Blocks.EXPOSED_COPPER), conditionsFromItem(Blocks.EXPOSED_COPPER))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, Blocks.WEATHERED_COPPER_GOLEM_STATUE, 1)
                        .pattern("0")
                        .pattern("1")
                        .input('0', Blocks.CARVED_PUMPKIN)
                        .input('1', Blocks.WEATHERED_COPPER)
                        .criterion(hasItem(Blocks.CARVED_PUMPKIN), conditionsFromItem(Blocks.CARVED_PUMPKIN))
                        .criterion(hasItem(Blocks.WEATHERED_COPPER), conditionsFromItem(Blocks.WEATHERED_COPPER))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, Blocks.OXIDIZED_COPPER_GOLEM_STATUE, 1)
                        .pattern("0")
                        .pattern("1")
                        .input('0', Blocks.CARVED_PUMPKIN)
                        .input('1', Blocks.OXIDIZED_COPPER)
                        .criterion(hasItem(Blocks.CARVED_PUMPKIN), conditionsFromItem(Blocks.CARVED_PUMPKIN))
                        .criterion(hasItem(Blocks.OXIDIZED_COPPER), conditionsFromItem(Blocks.OXIDIZED_COPPER))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, Blocks.WAXED_COPPER_GOLEM_STATUE, 1)
                        .pattern("0")
                        .pattern("1")
                        .input('0', Blocks.CARVED_PUMPKIN)
                        .input('1', Blocks.WAXED_COPPER_BLOCK)
                        .criterion(hasItem(Blocks.CARVED_PUMPKIN), conditionsFromItem(Blocks.CARVED_PUMPKIN))
                        .criterion(hasItem(Blocks.WAXED_COPPER_BLOCK), conditionsFromItem(Blocks.WAXED_COPPER_BLOCK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, Blocks.WAXED_EXPOSED_COPPER_GOLEM_STATUE, 1)
                        .pattern("0")
                        .pattern("1")
                        .input('0', Blocks.CARVED_PUMPKIN)
                        .input('1', Blocks.WAXED_EXPOSED_COPPER)
                        .criterion(hasItem(Blocks.CARVED_PUMPKIN), conditionsFromItem(Blocks.CARVED_PUMPKIN))
                        .criterion(hasItem(Blocks.WAXED_EXPOSED_COPPER), conditionsFromItem(Blocks.WAXED_EXPOSED_COPPER))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, Blocks.WAXED_WEATHERED_COPPER_GOLEM_STATUE, 1)
                        .pattern("0")
                        .pattern("1")
                        .input('0', Blocks.CARVED_PUMPKIN)
                        .input('1', Blocks.WAXED_WEATHERED_COPPER)
                        .criterion(hasItem(Blocks.CARVED_PUMPKIN), conditionsFromItem(Blocks.CARVED_PUMPKIN))
                        .criterion(hasItem(Blocks.WAXED_WEATHERED_COPPER), conditionsFromItem(Blocks.WAXED_WEATHERED_COPPER))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, Blocks.WAXED_OXIDIZED_COPPER_GOLEM_STATUE, 1)
                        .pattern("0")
                        .pattern("1")
                        .input('0', Blocks.CARVED_PUMPKIN)
                        .input('1', Blocks.WAXED_OXIDIZED_COPPER)
                        .criterion(hasItem(Blocks.CARVED_PUMPKIN), conditionsFromItem(Blocks.CARVED_PUMPKIN))
                        .criterion(hasItem(Blocks.WAXED_OXIDIZED_COPPER), conditionsFromItem(Blocks.WAXED_OXIDIZED_COPPER))
                        .offerTo(exporter);

                createButtonRecipe(ModBlocks.COPPER_BUTTON, Ingredient.ofItem(Blocks.COPPER_BLOCK));
                createButtonRecipe(ModBlocks.EXPOSED_COPPER_BUTTON, Ingredient.ofItem(Blocks.EXPOSED_COPPER));
                createButtonRecipe(ModBlocks.WEATHERED_COPPER_BUTTON, Ingredient.ofItem(Blocks.WEATHERED_COPPER));
                createButtonRecipe(ModBlocks.OXIDIZED_COPPER_BUTTON, Ingredient.ofItem(Blocks.OXIDIZED_COPPER));
                createButtonRecipe(ModBlocks.WAXED_COPPER_BUTTON, Ingredient.ofItems(ModBlocks.COPPER_BUTTON, Items.HONEYCOMB));
                createButtonRecipe(ModBlocks.WAXED_EXPOSED_COPPER_BUTTON, Ingredient.ofItems(ModBlocks.EXPOSED_COPPER_BUTTON, Items.HONEYCOMB));
                createButtonRecipe(ModBlocks.WAXED_WEATHERED_COPPER_BUTTON, Ingredient.ofItems(ModBlocks.WEATHERED_COPPER_BUTTON, Items.HONEYCOMB));
                createButtonRecipe(ModBlocks.WAXED_OXIDIZED_COPPER_BUTTON, Ingredient.ofItems(ModBlocks.OXIDIZED_COPPER_BUTTON, Items.HONEYCOMB));

                createShaped(RecipeCategory.MISC, ModBlocks.BLASTED_GLASS_PANE, 16)
                        .pattern("000")
                        .pattern("000")
                        .input('0', ModBlocks.BLASTED_GLASS)
                        .criterion(hasItem(ModBlocks.BLASTED_GLASS), conditionsFromItem(ModBlocks.BLASTED_GLASS))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.GRAVITY_GUN, 1)
                        .pattern(" 12")
                        .pattern("301")
                        .pattern("43 ")
                        .input('0', Items.NETHER_STAR)
                        .input('1', Items.DIAMOND)
                        .input('2', Items.BOW)
                        .input('3', Items.IRON_INGOT)
                        .input('4', Blocks.OBSIDIAN)
                        .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
                        .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                        .criterion(hasItem(Items.BOW), conditionsFromItem(Items.BOW))
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .criterion(hasItem(Items.OBSIDIAN), conditionsFromItem(Items.OBSIDIAN))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "Recipe";
    }
}
