package net.chemthunder.attuned.impl.client.event;

import net.chemthunder.attuned.impl.cca.entity.TuningForkShockwaveComponent;
import net.chemthunder.attuned.impl.cca.entity.TuningForkSymphonyComponent;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class SymphonyHudEvent implements HudRenderCallback {


    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;

        if (player != null) {
            TuningForkSymphonyComponent sc = TuningForkSymphonyComponent.KEY.get(player);

            if (sc.getSaviorTicks() > 0) {
                drawContext.drawTextWithShadow(
                        client.textRenderer,
                        Text.literal(sc.getSaviorTicks() + ""),
                        drawContext.getScaledWindowWidth() / 2,
                        drawContext.getScaledWindowHeight() + 40,
                        0xff0056
                );
            }
        }
    }
}
