package com.hrajtostudio.goldenpan;

import com.hrajtostudio.goldenpan.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoldenPanMod implements ModInitializer {
	public static final String MOD_ID = "goldenpan";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroup.registerItemGroups();
	}
}
