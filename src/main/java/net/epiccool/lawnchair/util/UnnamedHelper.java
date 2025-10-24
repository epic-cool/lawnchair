package net.epiccool.lawnchair.util;

import net.epiccool.lawnchair.Lawnchair;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

import java.util.WeakHashMap;

public class UnnamedHelper {
    private static final WeakHashMap<SquidEntity, Integer> loveTicksMap = new WeakHashMap<>();
    private static final WeakHashMap<SquidEntity, PlayerEntity> lovePlayerMap = new WeakHashMap<>();

    public static void Initialize() {
        Lawnchair.LOGGER.info("Helping " + Lawnchair.MODID + " do stuff");

        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!world.isClient()) {
                ItemStack stack = player.getStackInHand(hand);

                //Squid
                if (entity instanceof SquidEntity squid) {
                    //Milk
                    if (stack.isOf(Items.BUCKET) && squid.getCommandTags().contains("milkable")) {
                        return milk(player, world, hand, squid);
                    }

                    //Breed
                    if (stack.isOf(Items.WHEAT)) {
                        stack.decrementUnlessCreative(1, player);
                        loveTicksMap.put(squid, 600);
                        lovePlayerMap.put(squid, player);
                        player.swingHand(hand);
                        return ActionResult.SUCCESS;
                    }
                }

                //Sheep
                else if (entity instanceof SheepEntity sheep) {
                    //Milk
                    if (stack.isOf(Items.BUCKET)) {
                        return milk(player, world, hand, sheep);
                    }
                }
            }

            return ActionResult.PASS;
        });
    }

    private static ActionResult milk(PlayerEntity player, World world, Hand hand, Entity entity) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(Items.BUCKET)) {
            stack.decrementUnlessCreative(1, player);
            world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENTITY_COW_MILK, SoundCategory.PLAYERS, 1.0F, 1.0F);
            player.giveItemStack(new ItemStack(Items.MILK_BUCKET));
            player.swingHand(hand);
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    //todo: make this better
    public static void tick(World world) {
        for (SquidEntity squid : loveTicksMap.keySet().toArray(new SquidEntity[0])) {
            int ticks = loveTicksMap.getOrDefault(squid, 0);
            if (ticks > 0) {
                loveTicksMap.put(squid, ticks - 1);

                if (world.isClient() && ticks % 10 == 0) {
                    world.addParticleClient(
                            ParticleTypes.HEART,
                            squid.getX(), squid.getBodyY(0.5), squid.getZ(),
                            0.0, 0.05, 0.0
                    );
                }

                if (!world.isClient()) {
                    for (Entity entity : world.getOtherEntities(squid, squid.getBoundingBox().expand(1.5))) {
                        if (entity instanceof SquidEntity other &&
                                loveTicksMap.getOrDefault(other, 0) > 0 &&
                                other != squid) {

                            SquidEntity baby = new SquidEntity(EntityType.SQUID, world);
                            baby.setBaby(true);
                            baby.refreshPositionAndAngles(
                                    (squid.getX() + other.getX()) / 2,
                                    (squid.getY() + other.getY()) / 2,
                                    (squid.getZ() + other.getZ()) / 2,
                                    0, 0
                            );
                            world.spawnEntity(baby);

                            loveTicksMap.remove(squid);
                            loveTicksMap.remove(other);
                            lovePlayerMap.remove(squid);
                            lovePlayerMap.remove(other);
                            break;
                        }
                    }
                }
            } else {
                loveTicksMap.remove(squid);
                lovePlayerMap.remove(squid);
            }
        }
    }

    public static void injectEffects(
            ServerWorldAccess world,
            LocalDifficulty difficulty,
            LivingEntity self) {
        Random random = world.getRandom();

        if (world.getDifficulty() == Difficulty.HARD
                && random.nextFloat() < 0.1F * difficulty.getClampedLocalDifficulty()) {

            RegistryEntry<StatusEffect> effect;
            int i = random.nextInt(100);

            if (i < 40) {
                effect = StatusEffects.SPEED;
            } else if (i < 60) {
                effect = StatusEffects.STRENGTH;
            } else if (i < 80) {
                effect = StatusEffects.REGENERATION;
            } else {
                effect = StatusEffects.INVISIBILITY;
            }

            if (effect != null) {
                self.addStatusEffect(new StatusEffectInstance(effect, -1));
            }
        }
    }
}