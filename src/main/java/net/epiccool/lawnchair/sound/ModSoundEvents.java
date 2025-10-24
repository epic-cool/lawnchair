package net.epiccool.lawnchair.sound;

import net.epiccool.lawnchair.Lawnchair;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSoundEvents {
    public static final SoundEvent ENTITY_DUCK_AMBIENT = registerSoundEvent("entity_duck_ambient");
    public static final SoundEvent ENTITY_DUCK_DEATH = registerSoundEvent("entity_duck_death");
    public static final SoundEvent ENTITY_DUCK_STEP = registerSoundEvent("entity_duck_step");
    public static final SoundEvent ENTITY_DUCK_HURT = registerSoundEvent("entity_duck_hurt");
    public static final SoundEvent ENTITY_DUCK_EGG = registerSoundEvent("entity_duck_egg");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(Lawnchair.MODID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void Initialize() {
        Lawnchair.LOGGER.info("Registering Mod Sounds for " + Lawnchair.MODID);
    }
}
