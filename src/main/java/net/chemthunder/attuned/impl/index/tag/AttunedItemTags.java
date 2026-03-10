package net.chemthunder.attuned.impl.index.tag;

import net.chemthunder.attuned.impl.Attuned;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public interface AttunedItemTags {
    TagKey<Item> TUNING_FORKS = create("tuning_forks");

    private static TagKey<Item> create(String id) {
        return TagKey.of(RegistryKeys.ITEM, Attuned.id(id));
    }
}
