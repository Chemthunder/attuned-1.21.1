package net.chemthunder.attuned.mixin;

import net.chemthunder.attuned.impl.item.TuningForkItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void attuned$tuningForkParry(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack stack = player.getStackInHand(player.getActiveHand());

        if (stack.getItem() instanceof TuningForkItem item) {
            if (source.getAttacker() instanceof LivingEntity living) {
                if (player.isUsingItem()) {
                    if (!player.isCreative()) {
                        cir.setReturnValue(false);

                        item.attuned$tuningForkParry(player, living, player.getWorld(), stack);
                    }
                }
            }
        }
    }
}
