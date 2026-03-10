package net.chemthunder.attuned.impl.index;

import net.acoyt.acornlib.api.registrants.BlockRegistrant;
import net.chemthunder.attuned.impl.Attuned;
import net.chemthunder.attuned.impl.block.AncillaryBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;

public interface AttunedBlocks {
    BlockRegistrant BLOCKS = new BlockRegistrant(Attuned.MOD_ID);

    Block ANCILLARY = BLOCKS.register("ancillary", AncillaryBlock::new, AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.TRIAL_SPAWNER).luminance(value -> 4).emissiveLighting((state, world, pos) -> true));

    static void init() {
        //
    }
}
