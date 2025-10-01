package net.epiccool.lawnchair;

import net.epiccool.lawnchair.effect.ModEffects;
import net.epiccool.lawnchair.effect.potion.ModPotions;
import net.epiccool.lawnchair.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lawnchair implements ModInitializer {
	public static final String MODID = "lawnchair";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
        ModItems.Initialize();
        ModEffects.Initialize();
        ModPotions.Initialize();
	}
}