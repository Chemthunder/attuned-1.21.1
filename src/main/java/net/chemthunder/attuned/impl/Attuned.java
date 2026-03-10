package net.chemthunder.attuned.impl;

import net.chemthunder.attuned.impl.index.*;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Attuned implements ModInitializer {
	public static final String MOD_ID = "attuned";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void onInitialize() {
        AttunedItems.init();
        AttunedDataComponents.init();
        AttunedEnchantmentEffects.init();
        AttunedBlocks.init();
        AttunedParticles.init();

		LOGGER.info("Hello Fabric world!");
	}

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}