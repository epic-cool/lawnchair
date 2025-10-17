package net.epiccool.lawnchair.item.custom;

import net.minecraft.component.ComponentsAccess;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.function.Consumer;

public class EvilGoopFragmentItem extends Item implements TooltipAppender {
    public EvilGoopFragmentItem(Settings settings) {
        super(settings);
    }

    //Todo: Use on mob to turn them into evil goop version

    @Override
    public void appendTooltip(TooltipContext context, Consumer<Text> textConsumer, TooltipType type, ComponentsAccess components) {
        textConsumer.accept(Text.translatable("item.lawnchair.evil_goop_fragment.tooltip"));

    }
}
