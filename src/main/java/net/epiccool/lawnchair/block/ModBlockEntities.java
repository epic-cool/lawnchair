package net.epiccool.lawnchair.block;

import net.epiccool.lawnchair.Lawnchair;

public class ModBlockEntities {
//    public static final BlockEntityType<AlloyMixerBlockEntity> ALLOY_MIXER_ENTITY =
//            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Lawnchair.MODID, "alloy_mixer_block_entity"),
//                    FabricBlockEntityTypeBuilder.create(AlloyMixerBlockEntity::new, ModBlocks.ALLOY_MIXER).build(null));


    public static void Initialize() {
        Lawnchair.LOGGER.info("Registering Mod Block Entities for " + Lawnchair.MODID);
    }
}
