package net.epiccool.lawnchair.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class IcePickItem extends Item implements TooltipAppender {
    public IcePickItem(Settings settings) {
        super(settings);
    }

    public static ToolComponent createToolComponent() {
        RegistryEntryLookup<Block> registryEntryLookup = Registries.createEntryLookup(Registries.BLOCK);
        return new ToolComponent(
                List.of(
                        ToolComponent.Rule.of(registryEntryLookup.getOrThrow(BlockTags.ICE), 10.0F)
                        ),
                1.0F,
                1,
                true
        );
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        Identifier blockId = Registries.BLOCK.getId(block);

        switch (blockId.toString()) {
            case "minecraft:ice":
                world.setBlockState(pos, Blocks.PACKED_ICE.getDefaultState());
                if (!world.isClient()) {
                    context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                            item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));
                }
                world.playSound(null, pos, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
                break;
            case "minecraft:packed_ice":
                world.setBlockState(pos, Blocks.BLUE_ICE.getDefaultState());
                if (!world.isClient()) {
                    context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                            item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));
                }
                world.playSound(null, pos, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
                break;
            case "minecraft:blue_ice":
                world.setBlockState(pos, Blocks.ICE.getDefaultState());
                if (!world.isClient()) {
                    context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                            item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));
                }
                world.playSound(null, pos, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
                break;
            default:
                return ActionResult.PASS;
        }

        return ActionResult.SUCCESS;
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
            Vec3d motion = player.getVelocity();
            double stickX = 0;
            double stickZ = 0;
            switch (facing) {
                case NORTH -> stickZ = -0.05;
                case SOUTH -> stickZ = 0.05;
                case WEST -> stickX = -0.05;
                case EAST -> stickX = 0.05;
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

            //~209 blocks
            if (!world.isClient() && world.getTime() % 10 == 0) {
                if (world.random.nextInt(3) == 0) {
                    stack.damage(1, player, Hand.OFF_HAND);
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot);
    }

    @Override
    public void appendTooltip(TooltipContext context, Consumer<Text> textConsumer, TooltipType type, ComponentsAccess components) {
        textConsumer.accept(Text.translatable("item.lawnchair.ice_pick.tooltip"));
    }
}
