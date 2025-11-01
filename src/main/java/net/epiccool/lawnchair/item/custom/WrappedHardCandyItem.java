package net.epiccool.lawnchair.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WrappedHardCandyItem extends CandyItem {
    private final StatusEffectInstance effect;

    public WrappedHardCandyItem(Settings settings, StatusEffectInstance effect) {
        super(settings);
        this.effect = effect;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient()) {
            if (effect != null) {
                user.addStatusEffect(new StatusEffectInstance(
                        effect.getEffectType(),
                        effect.getDuration(),
                        effect.getAmplifier(),
                        false,
                        false,
                        true
                ));
            }
        }

        return super.finishUsing(stack, world, user);
    }
}
