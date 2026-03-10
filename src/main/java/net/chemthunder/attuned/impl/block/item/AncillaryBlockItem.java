package net.chemthunder.attuned.impl.block.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.chemthunder.attuned.impl.index.AttunedBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class AncillaryBlockItem extends BlockItem implements ColorableItem {
    public AncillaryBlockItem(Settings settings) {
        super(AttunedBlocks.ANCILLARY, settings);
    }


    public int startColor(ItemStack itemStack) {
        return 0xFF7a6873;
    }

    public int endColor(ItemStack itemStack) {
        return 0xFF201b28;
    }

    public int backgroundColor(ItemStack itemStack) {
        return 0xFF14111a;
    }

    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().withColor(endColor(stack));
    }
}
