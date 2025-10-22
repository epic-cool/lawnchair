package net.epiccool.lawnchair.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RawFoodItem extends Item {
    public RawFoodItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);

        if (!world.isClient() && user instanceof PlayerEntity player) {
            if (world.getRandom().nextFloat() < 0.3f) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 600, 0));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 600, 0));
            }
        }

        return result;
    }
}
