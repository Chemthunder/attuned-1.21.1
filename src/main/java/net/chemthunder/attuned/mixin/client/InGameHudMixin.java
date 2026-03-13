package net.chemthunder.attuned.mixin.client;

import net.chemthunder.attuned.impl.Attuned;
import net.chemthunder.attuned.impl.cca.entity.TuningForkSymphonyComponent;
import net.chemthunder.attuned.mixin.PlayerEntityMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Shadow
    protected abstract void renderOverlay(DrawContext context, Identifier texture, float opacity);

    @Unique
    private static final Identifier VIGNETTE = Attuned.id("textures/gui/symphony_vignette.png");

    @Inject(method = "renderMiscOverlays", at = @At("TAIL"))
    private void attuned$symphonyOverlay(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;

        if (player != null) {
            int ticks = TuningForkSymphonyComponent.KEY.get(player).getSaviorTicks();
            if (ticks > 0) {
                float opacity = ticks > 50 ? 1f : ticks / 50.0f;
                this.renderOverlay(context, VIGNETTE, opacity);
            }
        }
    }
}
