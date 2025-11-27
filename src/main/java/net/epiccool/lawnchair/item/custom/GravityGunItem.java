package net.epiccool.lawnchair.item.custom;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.WeakHashMap;
import java.util.function.Consumer;

public class GravityGunItem extends Item {
    private static final WeakHashMap<ItemStack, Mode> map = new WeakHashMap<>();

    enum Mode {
        PUSH, PULL, _STATIC
    }

    public GravityGunItem(Settings settings) {
        super(settings);
    }

    private Mode getMode(ItemStack stack) {
        return map.getOrDefault(stack, Mode.PULL);
    }

    private void setMode(ItemStack stack, Mode mode, PlayerEntity player) {
        map.put(stack, mode);
        player.sendMessage(Text.translatable("item.lawnchair.gravity_gun.tooltip." + mode.name().toLowerCase()), true);
    }

    public void changeMode(ItemStack stack, PlayerEntity player) {
        Mode mode = getMode(stack);
        switch (mode) {
            case PULL -> setMode(stack, Mode.PUSH, player);
            case PUSH -> setMode(stack, Mode._STATIC, player);
            case _STATIC -> setMode(stack, Mode.PULL, player);
        }
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        Mode mode = getMode(stack);

        if (!world.isClient()) {
            switch (mode) {
                case PULL -> pull(world, user);
                case PUSH -> push(world, user);
                case _STATIC -> _static(world, user);
            }
        }

        return ActionResult.SUCCESS;
    }

    private void pull(World world, PlayerEntity user) {
    }

    private void push(World world, PlayerEntity user) {
    }

    private void _static(World world, PlayerEntity user) {
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        Mode mode = getMode(stack);
        textConsumer.accept(Text.translatable("item.lawnchair.gravity_gun.tooltip." + mode.name().toLowerCase()).formatted(Formatting.GRAY));
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
}
