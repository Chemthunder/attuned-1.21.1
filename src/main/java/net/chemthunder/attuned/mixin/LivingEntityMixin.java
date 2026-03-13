package net.chemthunder.attuned.mixin;

import net.chemthunder.attuned.impl.cca.entity.TuningForkSymphonyComponent;
import net.chemthunder.attuned.impl.index.data.AttunedDamageTypes;
import net.chemthunder.attuned.impl.item.TuningForkItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void attuned$symphony(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity goober = (LivingEntity) (Object) this;
        Entity entity = source.getAttacker();

        if (entity instanceof LivingEntity living) {
            if (goober instanceof PlayerEntity player) {
                if (player.getStackInHand(player.getActiveHand()).getItem() instanceof TuningForkItem item) {
                    if (player.isUsingItem()) {
                        if (item.hasSymphony(player.getStackInHand(player.getActiveHand()))) {
                            cir.setReturnValue(true);
                            player.setHealth(player.getMaxHealth());
                            item.attuned$tuningForkSymphony(player, living, player.getWorld(), player.getStackInHand(player.getActiveHand()));
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void attuned$symphonyNegateDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity) (Object) this;

        if (living instanceof PlayerEntity player) {
            if (TuningForkSymphonyComponent.KEY.get(player).getSaviorTicks() > 0) {
                if (!source.isOf(AttunedDamageTypes.MAGNUM_OPUS)) {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
