package net.epiccool.lawnchair.item.custom;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.function.Consumer;

public class EvilGoopFragmentItem extends Item {
    public EvilGoopFragmentItem(Settings settings) {
        super(settings);
    }

    //Todo: Use on mob to turn them into evil goop version

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.lawnchair.evil_goop_fragment.tooltip"));

        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
}
