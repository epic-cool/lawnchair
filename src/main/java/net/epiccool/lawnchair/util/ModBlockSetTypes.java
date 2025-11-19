package net.epiccool.lawnchair.util;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.Map;
import java.util.stream.Stream;

public record ModBlockSetTypes(String name, boolean canOpenByHand, boolean canOpenByWindCharge, boolean canButtonBeActivatedByArrows, ModBlockSetTypes.ActivationRule pressurePlateSensitivity, BlockSoundGroup soundType, SoundEvent doorClose, SoundEvent doorOpen, SoundEvent trapdoorClose, SoundEvent trapdoorOpen, SoundEvent pressurePlateClickOff, SoundEvent pressurePlateClickOn, SoundEvent buttonClickOff, SoundEvent buttonClickOn) {
    private static final Map<String, ModBlockSetTypes> VALUES = new Object2ObjectArrayMap<String, ModBlockSetTypes>();
    public static final Codec<ModBlockSetTypes> CODEC = Codec.stringResolver(ModBlockSetTypes::name, VALUES::get);

    public static final ModBlockSetTypes COPPER = ModBlockSetTypes.register(new ModBlockSetTypes(
            "copper", true, false, true,
            ModBlockSetTypes.ActivationRule.EVERYTHING,
            BlockSoundGroup.COPPER,
            SoundEvents.BLOCK_COPPER_DOOR_CLOSE, SoundEvents.BLOCK_COPPER_DOOR_OPEN,
            SoundEvents.BLOCK_COPPER_TRAPDOOR_CLOSE, SoundEvents.BLOCK_COPPER_TRAPDOOR_OPEN,
            SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON));

    public ModBlockSetTypes(String name) {
        this(name, true, true, true, ModBlockSetTypes.ActivationRule.EVERYTHING, BlockSoundGroup.WOOD, SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundEvents.BLOCK_WOODEN_DOOR_OPEN, SoundEvents.BLOCK_WOODEN_TRAPDOOR_CLOSE, SoundEvents.BLOCK_WOODEN_TRAPDOOR_OPEN, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON);
    }

    private static ModBlockSetTypes register(ModBlockSetTypes blockSetType) {
        VALUES.put(blockSetType.name, blockSetType);
        return blockSetType;
    }

    public static Stream<ModBlockSetTypes> stream() {
        return VALUES.values().stream();
    }

    public static enum ActivationRule {
        EVERYTHING,
        MOBS;
    }
}
