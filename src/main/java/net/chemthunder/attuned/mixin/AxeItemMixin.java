package net.chemthunder.attuned.mixin;

import net.chemthunder.attuned.impl.client.particle.ShockwaveParticleEffect;
import net.chemthunder.attuned.impl.index.AttunedBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin {

    @Inject(method = "useOnBlock", at = @At("HEAD"))
    private void attuned$ancillaryShearsFromQuartz(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity player = context.getPlayer();
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();
        BlockState state = world.getBlockState(pos);

        if (state.isOf(Blocks.QUARTZ_BLOCK) && world.getBlockState(pos.down()).isOf(Blocks.LAVA_CAULDRON)) {
            if (player != null) {
                player.swingHand(player.getActiveHand());
                world.setBlockState(pos, AttunedBlocks.ANCILLARY.getDefaultState());

                player.playSound(SoundEvents.ITEM_AXE_SCRAPE);
                world.setBlockState(pos.down(), Blocks.CAULDRON.getDefaultState());

                if (world instanceof ServerWorld serverWorld) {
                    serverWorld.spawnParticles(
                            new ShockwaveParticleEffect(
                                  0xffffff,
                                  3,
                                  Direction.Axis.Y
                            ),
                            pos.getX() + 0.5f,
                            pos.getY(),
                            pos.getZ() + 0.5f,
                            1,
                            0,
                            0,
                            0,
                            0.1f
                    );
                }
            }
        }
    }
}
