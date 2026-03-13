package net.chemthunder.attuned.impl;

import net.chemthunder.attuned.impl.client.event.SymphonyHudEvent;
import net.chemthunder.attuned.impl.index.AttunedParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class AttunedClient implements ClientModInitializer {

    public void onInitializeClient() {
        AttunedParticles.clientInit();

        HudRenderCallback.EVENT.register(new SymphonyHudEvent());
    }
}
