package net.epiccool.lawnchair.mixin;

import net.epiccool.lawnchair.util.ModDamageTypes;
import net.epiccool.lawnchair.util.ModGameRules;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
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

                    if (entity instanceof PlayerEntity player && !player.hasVehicle() && !player.isCreative()) {
                        World world = self.getEntityWorld();
                        Entity attacker = self.hasPassengers() ? self.getFirstPassenger() : self;

                        DamageSource source = new DamageSource(
                                world.getRegistryManager()
                                        .getOrThrow(RegistryKeys.DAMAGE_TYPE)
                                        .getEntry(ModDamageTypes.RAN_OVER.getValue()).get()
                                , self, attacker
                        );

                        entity.damage(serverWorld, source, damage);

                        //drop your shit
                        boolean dropItems = serverWorld.getGameRules()
                                .getBoolean(ModGameRules.MINECART_SCATTERS);
                        if (dropItems) {
                            Difficulty difficulty = player.getEntityWorld().getDifficulty();
                            Random random = new Random();
                            int inventorySize = player.getInventory().size();

                            double dropChance = switch (difficulty) {
                                case PEACEFUL -> 0;
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
}
