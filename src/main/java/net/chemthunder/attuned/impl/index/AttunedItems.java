package net.chemthunder.attuned.impl.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.chemthunder.attuned.impl.Attuned;
import net.chemthunder.attuned.impl.block.item.AncillaryBlockItem;
import net.chemthunder.attuned.impl.item.TuningForkItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public interface AttunedItems {
    ItemRegistrant ITEMS = new ItemRegistrant(Attuned.MOD_ID);

    Item TUNING_FORK = ITEMS.register("tuning_fork", TuningForkItem::new, new Item.Settings().maxCount(1).attributeModifiers(TuningForkItem.createAttributeModifiers()));

    Item ANCILLARY_ITEM = ITEMS.register("ancillary", AncillaryBlockItem::new, new Item.Settings());

    static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(AttunedItems::addCombatEntries);
    }

    private static void addCombatEntries(FabricItemGroupEntries entries) {
        ItemStack AMARITE_TUNING_FORK = new ItemStack(TUNING_FORK);
        ItemStack VALEDICTION = new ItemStack(TUNING_FORK);

        AMARITE_TUNING_FORK.set(AttunedDataComponents.SKIN, 2);
        VALEDICTION.set(AttunedDataComponents.SKIN, 1);

        entries.addAfter(Items.TNT, TUNING_FORK);
        entries.addAfter(TUNING_FORK, VALEDICTION);
        entries.addAfter(VALEDICTION, AMARITE_TUNING_FORK);
    }
}
