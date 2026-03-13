package net.chemthunder.attuned.mixin;

import net.chemthunder.attuned.impl.client.particle.ShockwaveParticleEffect;
import net.chemthunder.attuned.impl.index.AttunedStatusEffects;
import net.chemthunder.attuned.impl.item.TuningForkItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void attuned$tuningForkParry(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack stack = player.getStackInHand(player.getActiveHand());
        World world = player.getWorld();

        if (stack.getItem() instanceof TuningForkItem item) {
            if (source.getAttacker() instanceof LivingEntity living) {
                if (world instanceof ServerWorld serverWorld) {
                    if (player.isUsingItem()) {
                        if (!player.isCreative()) {
                            if (!player.getItemCooldownManager().isCoolingDown(item)) {
                                if (!item.hasOctave(stack) && !item.hasShrill(stack) && !item.hasSymphony(stack)) {
                                    cir.setReturnValue(false);

                                    item.attuned$tuningForkParry(player, living, world, stack);
                                }

                                if (item.hasShrill(stack)) {
                                    Box area = new Box(player.getBlockPos()).expand(7);
                                    List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, area, entity -> true);

                                    for (LivingEntity entity : entities) {
                                        if (entity != player) {
                                            entity.addStatusEffect(new StatusEffectInstance(AttunedStatusEffects.DEAFENED, 600));
                                            entity.setVelocity(0, 0, 0);
                                        }
                                    }

                                    serverWorld.spawnParticles(
                                            new ShockwaveParticleEffect(
                                                    item.attuned$shockwaveColors(stack),
                                                    3,
                                                    Direction.Axis.Y
                                            ),
                                            player.getX() + 0.5f,
                                            player.getY() + 0.5f,
                                            player.getZ() + 0.5f,
                                            1,
                                            0, 0, 0,
                                            0.1f
                                    );

                                    player.getItemCooldownManager().set(item, 90);
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}
