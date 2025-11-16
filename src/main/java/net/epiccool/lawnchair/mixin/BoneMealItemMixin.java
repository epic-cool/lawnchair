package net.epiccool.lawnchair.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoneMealItem.class)
public abstract class BoneMealItemMixin {
    @Unique
    private static final TagKey<Block> FLOWERS =
            TagKey.of(RegistryKeys.BLOCK, Identifier.of("lawnchair", "flowers"));

    @Inject(method = "useOnBlock", at = @At("HEAD"))
    private void bonemealFlower(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        if (!(context.getWorld() instanceof ServerWorld serverWorld)) return;

        BlockPos pos = context.getBlockPos();
        BlockState state = serverWorld.getBlockState(pos);
        Block block = state.getBlock();

        if (state.contains(Properties.DOUBLE_BLOCK_HALF) && state.get(Properties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER) {
            pos = pos.down();
            state = serverWorld.getBlockState(pos);
            block = state.getBlock();
        }

        boolean duplicated = false;

        if (block.getDefaultState().isIn(FLOWERS)) {
            ItemStack stack = new ItemStack(block.asItem());
            if (block.getDefaultState().isOf(Blocks.PEONY) || block.getDefaultState().isOf(Blocks.LILAC) || block.getDefaultState().isOf(Blocks.SUNFLOWER) || block.getDefaultState().isOf(Blocks.ROSE_BUSH)) {
                return;
            }
            ItemScatterer.spawn(serverWorld, pos.getX(), pos.getY(), pos.getZ(), stack);
            duplicated = true;

            Random random = serverWorld.random;
            for (int i = 0; i < 15; i++) {
                double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5);
                double y = pos.getY() + 0.5 + random.nextDouble();
                double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5);
                double vx = random.nextGaussian() * 0.02;
                double vy = random.nextGaussian() * 0.02;
                double vz = random.nextGaussian() * 0.02;
                serverWorld.spawnParticles(ParticleTypes.HAPPY_VILLAGER, x, y, z, 1, vx, vy, vz, 0.1);
            }
        }

        if (duplicated) {
            serverWorld.syncWorldEvent(WorldEvents.BONE_MEAL_USED, pos, 0);

            if (context.getPlayer() != null) {
                context.getPlayer().swingHand(context.getHand(), true);
                context.getStack().decrementUnlessCreative(1, context.getPlayer());
                context.getPlayer().emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
            }
        }
    }

    @Inject(method = "useOnBlock", at = @At("HEAD"))
    private void bonemealCane(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        if (!(context.getWorld() instanceof ServerWorld serverWorld)) return;

        BlockPos pos = context.getBlockPos();
        BlockState state = serverWorld.getBlockState(pos);
        Block block = state.getBlock();

        boolean boned = false;

        if (block.getDefaultState().isOf(Blocks.SUGAR_CANE)) {
            BlockPos top = pos;
            while (serverWorld.getBlockState(top.up()).isOf(Blocks.SUGAR_CANE)) {
                top = top.up();
            }

            BlockPos cancelT = top;
            BlockPos cancelM = cancelT.down();
            BlockPos cancelB = cancelM.down();
            if (serverWorld.getBlockState(cancelT).isOf(Blocks.SUGAR_CANE) && serverWorld.getBlockState(cancelM).isOf(Blocks.SUGAR_CANE) && serverWorld.getBlockState(cancelB).isOf(Blocks.SUGAR_CANE))
                return;

            if (serverWorld.isAir(top.up()) && top.getY() - pos.getY() < 2) {
                serverWorld.setBlockState(top.up(), Blocks.SUGAR_CANE.getDefaultState());

                boned = true;
                Random random = serverWorld.random;
                for (int i = 0; i < 15; i++) {
                    double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5);
                    double y = pos.getY() + 0.5 + random.nextDouble();
                    double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5);
                    double vx = random.nextGaussian() * 0.02;
                    double vy = random.nextGaussian() * 0.02;
                    double vz = random.nextGaussian() * 0.02;
                    serverWorld.spawnParticles(ParticleTypes.HAPPY_VILLAGER, x, y, z, 1, vx, vy, vz, 0.1);
                }
            }
        }

        if (boned) {
            serverWorld.syncWorldEvent(WorldEvents.BONE_MEAL_USED, pos, 0);

            if (context.getPlayer() != null) {
                context.getPlayer().swingHand(context.getHand(), true);
                context.getStack().decrementUnlessCreative(1, context.getPlayer());
                context.getPlayer().emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
            }
        }
    }
}
