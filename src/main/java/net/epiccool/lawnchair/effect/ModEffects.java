//package net.epiccool.lawnchair.effect;
//
//import net.epiccool.lawnchair.Lawnchair;
//import net.epiccool.lawnchair.effect.custom.StickyEffect;
//import net.minecraft.entity.effect.StatusEffect;
//import net.minecraft.registry.Registries;
//import net.minecraft.registry.Registry;
//import net.minecraft.registry.entry.RegistryEntry;
//import net.minecraft.util.Identifier;
//
//public class ModEffects {
//    public static final RegistryEntry<StatusEffect> STICKY =
//            Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Lawnchair.MODID, "sticky"), new StickyEffect());
//
//
//
//    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
//        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Lawnchair.MODID, name), statusEffect);
//    }
//
//    public static void Initialize() {
//        Lawnchair.LOGGER.info("Registering Mod Effects for " + Lawnchair.MODID);
//    }
//
//}
