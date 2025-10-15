//package net.epiccool.lawnchair.screen;
//
//import net.epiccool.lawnchair.Lawnchair;
//import net.minecraft.registry.Registries;
//import net.minecraft.registry.Registry;
//import net.minecraft.resource.featuretoggle.FeatureFlags;
//import net.minecraft.screen.ScreenHandlerType;
//import net.minecraft.util.Identifier;
//
//public class ModScreenHandlers {
//    public static final ScreenHandlerType<AlloyMixerScreenHandler> ALLOY_MIXER_SCREEN_HANDLER =
//            Registry.register(
//                    Registries.SCREEN_HANDLER,
//                    Identifier.of(Lawnchair.MODID, "alloy_mixer"),
//                    new ScreenHandlerType<>(AlloyMixerScreenHandler::new, FeatureFlags.VANILLA_FEATURES)
//            );
//
//    public static void Initialize() {
//        Lawnchair.LOGGER.info("Registering Mod Screen Handlers for " + Lawnchair.MODID);
//    }
//}
