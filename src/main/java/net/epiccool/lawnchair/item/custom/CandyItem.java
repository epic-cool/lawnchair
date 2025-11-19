package net.epiccool.lawnchair.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.WeakHashMap;

public class CandyItem extends Item {
    private static final Map<UUID, CandyData> PLAYER_DATA = new WeakHashMap<>();

    public CandyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        Random random = new Random();
        if (!world.isClient() && user instanceof ServerPlayerEntity player) {
            UUID id = player.getUuid();
            CandyData data = PLAYER_DATA.computeIfAbsent(id, k -> new CandyData());

            data.candyCount++;

            if (data.candyCount >= 64) {
                if (random.nextFloat() < data.chance / 100f) {
                    player.clearStatusEffects();
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 10));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 10));
                    player.sendMessage(Text.translatable("item.lawnchair.candy.ill"), true);

                    data.candyCount = 0;
                    data.chance = 10f;
                } else {
                    data.chance = Math.min(data.chance + 10f, 100f);
                    data.candyCount = 0;
                }
            }

            float health = player.getHealth();

            player.setHealth(health + 2);

            PLAYER_DATA.put(id, data);
        }

        return super.finishUsing(stack, world, user);
    }

    private static class CandyData {
        int candyCount = 0;
        float chance = 10f;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 16;
    }
}
