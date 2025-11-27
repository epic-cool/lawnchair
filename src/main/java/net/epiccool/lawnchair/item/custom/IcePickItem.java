package net.epiccool.lawnchair.item.custom;

import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class IcePickItem extends Item {
    public IcePickItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canBeEnchantedWith(ItemStack stack, RegistryEntry<Enchantment> enchantmentEntry, EnchantingContext context) {
        return enchantmentEntry.value().equals(Enchantments.EFFICIENCY) || super.canBeEnchantedWith(stack, enchantmentEntry, context);
    }

    public static ToolComponent createToolComponent() {
        RegistryEntry<Block> ice = Registries.BLOCK.getEntry(Blocks.ICE);
        RegistryEntry<Block> packedIce = Registries.BLOCK.getEntry(Blocks.PACKED_ICE);
        RegistryEntry<Block> blueIce = Registries.BLOCK.getEntry(Blocks.BLUE_ICE);

        RegistryEntryList<Block> iceBlocks = RegistryEntryList.of(ice, packedIce, blueIce);

        return new ToolComponent(
                List.of(ToolComponent.Rule.of(iceBlocks, 10.0F)),
                1.0F, 1, true
        );
    }

    public int getEfficiencyLevel(ItemStack stack) {
        RegistryKey<Enchantment> efficiencyKey = Enchantments.EFFICIENCY;

        for (RegistryEntry<Enchantment> enchantmentEntry : stack.getEnchantments().getEnchantments()) {
            if (enchantmentEntry.getKey().equals(efficiencyKey)) {
                return stack.getEnchantments().getLevel(enchantmentEntry);
            }
        }
        return 0;
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        Identifier blockId = Registries.BLOCK.getId(block);

        switch (blockId.toString()) {
            case "minecraft:ice" -> {
                world.setBlockState(pos, Blocks.PACKED_ICE.getDefaultState());
                damageAndSound(context, world, pos);
            }
            case "minecraft:packed_ice" -> {
                world.setBlockState(pos, Blocks.BLUE_ICE.getDefaultState());
                damageAndSound(context, world, pos);
            }
            case "minecraft:blue_ice" -> {
                world.setBlockState(pos, Blocks.ICE.getDefaultState());
                damageAndSound(context, world, pos);
            }
            default -> {
                return ActionResult.PASS;
            }
        }
        return ActionResult.SUCCESS;
    }

    private void damageAndSound(ItemUsageContext context, World world, BlockPos pos) {
        if (!world.isClient()) {
            context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                    item -> {
                        assert context.getPlayer() != null;
                        context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND);
                    });
        }
        world.playSound(null, pos, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS,
                1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        Block block = state.getBlock();
        if (block == Blocks.ICE || block == Blocks.PACKED_ICE || block == Blocks.BLUE_ICE) {
            Block.dropStack(world, pos, new ItemStack(block));
            world.removeBlock(pos, false);
            return true;
        }
        return super.postMine(stack, world, state, pos, miner);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (!(entity instanceof PlayerEntity player)) return;
        if (!ItemStack.areEqual(player.getOffHandStack(), stack)) return;

        BlockPos pos = player.getBlockPos();
        Direction facing = player.getHorizontalFacing();
        BlockState blockState = world.getBlockState(pos.offset(facing));

        if (blockState.isOf(Blocks.ICE) || blockState.isOf(Blocks.PACKED_ICE) || blockState.isOf(Blocks.BLUE_ICE)) {
            double stickX = 0;
            double stickZ = 0;

            double speedBoost = 0.05 + 0.01 * getEfficiencyLevel(stack); // scales with efficiency
            switch (facing) {
                case NORTH -> stickZ = -speedBoost;
                case SOUTH -> stickZ = speedBoost;
                case WEST -> stickX = -speedBoost;
                case EAST -> stickX = speedBoost;
            }

            double y = 0;
            if (!player.isOnGround()) {
                y = 0.2;
            }
            if (player.isSneaking()) {
                y = -0.1;
            }

            player.setVelocity(stickX, y, stickZ);
            player.fallDistance = 0;
            player.velocityModified = true;

            if (!world.isClient() && world.getTime() % 10 == 0) {
                if (world.random.nextInt(3) == 0) {
                    stack.damage(1, player, Hand.OFF_HAND);
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot);
    }

    @Override
    public float getMiningSpeed(ItemStack stack, BlockState state) {
        float base = super.getMiningSpeed(stack, state);
        if (!state.isIn(BlockTags.ICE)) return base;
        int efficiencyLevel = getEfficiencyLevel(stack);
        float speedMultiplier = 1.0F + (0.3F * efficiencyLevel);

        return base * speedMultiplier;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.lawnchair.ice_pick.tooltip").formatted(Formatting.GRAY));
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
}