package net.epiccool.lawnchair.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoneMealItem.class)
public abstract class BoneMealItemMixin {
    private static final TagKey<Block> SMALL_FLOWERS =
            TagKey.of(RegistryKeys.BLOCK, Identifier.ofVanilla("small_flowers"));
    private static final TagKey<Block> TALL_FLOWERS =
            TagKey.of(RegistryKeys.BLOCK, Identifier.ofVanilla("tall_flowers"));

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
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

        if (block.getDefaultState().isIn(SMALL_FLOWERS) || block.getDefaultState().isIn(TALL_FLOWERS)) {
            ItemStack stack = new ItemStack(block.asItem());
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
}
