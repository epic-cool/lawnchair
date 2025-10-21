package net.epiccool.lawnchair.mixin;

import net.epiccool.lawnchair.util.ModDamageSources;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(AbstractMinecartEntity.class)
public class AbstractMinecartEntityMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    private void LawnchairTick(CallbackInfo ci) {
        AbstractMinecartEntity self = (AbstractMinecartEntity) (Object) this;
        Vec3d vel = self.getVelocity();

        //speed
        self.setVelocity(vel.x * 1.3, vel.y, vel.z * 1.3);

        //do damage
        if (!vel.equals(0.0f)) {
            for (Entity entity : self.getEntityWorld().getOtherEntities(self, self.getBoundingBox())) {
                if (self.getEntityWorld() instanceof ServerWorld serverWorld && !entity.hasPlayerRider()) {
                    float damage = 1f;
                    switch (self.getEntityWorld().getDifficulty()) {
                        case PEACEFUL -> damage = 0f;
                        case EASY -> damage = 5f;
                        case NORMAL -> damage = 7f;
                        case HARD -> damage = 8f;
                    }

                    DamageSource source;
                    try {
                        ModDamageSources damageSources = new ModDamageSources(serverWorld.getRegistryManager());
                        source = damageSources.ranOver();
                    } catch (Exception e) {
                        source = new DamageSources(serverWorld.getRegistryManager()).generic();
                    }

                    if (entity instanceof PlayerEntity player && !player.hasVehicle() && !player.isCreative()) {
                        entity.damage(serverWorld, source, damage);

                        Difficulty difficulty = player.getEntityWorld().getDifficulty();

                        //drop your shit
                        Random random = new Random();
                        int inventorySize = player.getInventory().size();

                        double dropChance = switch (difficulty) {
                            case PEACEFUL -> 0.0;
                            case EASY -> 0.2;
                            case NORMAL -> 0.5;
                            case HARD -> 1.0;
                        };

                        int maxDrops = switch (difficulty) {
                            case PEACEFUL -> 0;
                            case EASY -> 9;
                            case NORMAL -> 18;
                            case HARD -> 100;
                        };

                        for (int i = 0; i < maxDrops; i++) {
                            if (random.nextDouble() < dropChance) {
                                int randomSlot = random.nextInt(inventorySize);
                                ItemStack stack = player.getInventory().getStack(randomSlot);

                                if (!stack.isEmpty()) {
                                    var droppedStack = player.dropStack(serverWorld, stack);

                                    if (droppedStack != null) {
                                        double velocityX = (random.nextDouble() - 0.5) * 0.5;
                                        double velocityY = 0.2 + random.nextDouble() * 0.2;
                                        double velocityZ = (random.nextDouble() - 0.5) * 0.5;
                                        droppedStack.setVelocity(velocityX, velocityY, velocityZ);
                                    }

                                    player.getInventory().setStack(randomSlot, ItemStack.EMPTY);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
