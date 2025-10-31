package net.epiccool.lawnchair.block.custom;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class SugarCubeBlockItem extends BlockItem {
    public SugarCubeBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        World world = user.getEntityWorld();

        if (!world.isClient() && entity instanceof AnimalEntity animal && entity.isBaby() && /*todo: fix breeding age still progressing*/ animal.getBreedingAge() != Integer.MIN_VALUE) {
            if (world instanceof ServerWorld serverWorld) {
                Random random = serverWorld.getRandom();
                var pos = animal.getBlockPos();

                for (int i = 0; i < 15; i++) {
                    double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5);
                    double y = pos.getY() + 0.5 + random.nextDouble();
                    double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5);
                    double vx = random.nextGaussian() * 0.02;
                    double vy = random.nextGaussian() * 0.02;
                    double vz = random.nextGaussian() * 0.02;
                    serverWorld.spawnParticles(ParticleTypes.HEART, x, y, z, 1, vx, vy, vz, 0.1);
                }
            }

            if (!animal.isBaby()) {
                animal.setBaby(true);
            }

            animal.setBreedingAge(Integer.MIN_VALUE);
            stack.decrementUnlessCreative(1, user);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
