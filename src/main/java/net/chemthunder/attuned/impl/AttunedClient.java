package net.chemthunder.attuned.impl;

import net.chemthunder.attuned.impl.index.AttunedParticles;
import net.fabricmc.api.ClientModInitializer;

public class AttunedClient implements ClientModInitializer {

    public void onInitializeClient() {
        AttunedParticles.clientInit();
    }
}
