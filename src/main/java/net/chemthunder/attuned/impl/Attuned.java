package net.chemthunder.attuned.impl;

import net.acoyt.acornlib.api.ALib;
import net.chemthunder.attuned.impl.index.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Attuned implements ModInitializer {
	public static final String MOD_ID = "attuned";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void onInitialize() {
        AttunedItems.init();
        AttunedDataComponents.init();
        AttunedEnchantmentEffects.init();
        AttunedBlocks.init();
        AttunedParticles.init();
        AttunedStatusEffects.init();
        AttunedSounds.init();

		LOGGER.info("im tuning my fork");

        ALib.registerModMenu(MOD_ID, 0xFF201b28);
	}

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}