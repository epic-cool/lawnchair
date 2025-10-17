package net.epiccool.lawnchair.entity;

import net.epiccool.lawnchair.Lawnchair;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.server.world.ServerWorld;

public class VariantTagger {
    public static void Initialize() {
        Lawnchair.LOGGER.info("Tagging Variants with " + Lawnchair.MODID);
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerWorld world : server.getWorlds()) {
                for (Entity entity : world.iterateEntities()) {
                    if (entity.getType() == EntityType.PIG && entity instanceof PigEntity pig) {
                        String variant = pig.getVariant().getIdAsString();
                        if (variant.contains("cold")) {
                            pig.addCommandTag("cold_variant");
                        } else if (variant.contains("temperate")) {
                            pig.addCommandTag("temperate_variant");
                        } else if (variant.contains("warm")) {
                            pig.addCommandTag("warm_variant");
                        }
                    }
                }
            }
        });
    }
}
