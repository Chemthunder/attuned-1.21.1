package net.chemthunder.attuned.impl.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class AncillaryBitItem extends Item implements ColorableItem {
    public AncillaryBitItem(Settings settings) {
        super(settings);
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
        return super.getName(stack).copy().withColor(0xFF424954);
    }
}
