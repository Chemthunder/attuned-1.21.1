package net.chemthunder.attuned.impl.index.data;

import net.chemthunder.attuned.impl.Attuned;
import net.chemthunder.attuned.impl.index.AttunedEnchantmentEffects;
import net.chemthunder.attuned.impl.index.tag.AttunedItemTags;
import net.minecraft.block.Block;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface AttunedEnchantments {
    RegistryKey<Enchantment> OCTAVE = create("octave");
    RegistryKey<Enchantment> SHRILL = create("shrill");
    RegistryKey<Enchantment> SYMPHONY = create("symphony");


    private static RegistryKey<Enchantment> create(String id) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Attuned.id(id));
    }

    static void bootstrap(Registerable<Enchantment> registerable) {
        RegistryEntryLookup<Enchantment> enchantmentLookup = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        RegistryEntryLookup<EntityType<?>> entityTypeLookup = registerable.getRegistryLookup(RegistryKeys.ENTITY_TYPE);
        RegistryEntryLookup<Block> blockLookup = registerable.getRegistryLookup(RegistryKeys.BLOCK);
        RegistryEntryLookup<Item> itemLookup = registerable.getRegistryLookup(RegistryKeys.ITEM);

        registerable.register(OCTAVE, Enchantment.builder(Enchantment.definition(
                                itemLookup.getOrThrow(AttunedItemTags.TUNING_FORKS),
                                2,
                                1,
                                Enchantment.leveledCost(5, 0),
                                Enchantment.leveledCost(17, 0),
                                7,
                                AttributeModifierSlot.MAINHAND
                        ))
                        .addEffect(AttunedEnchantmentEffects.SHOCKWAVE)
                        .build(OCTAVE.getValue())
        );

        registerable.register(SHRILL, Enchantment.builder(Enchantment.definition(
                                itemLookup.getOrThrow(AttunedItemTags.TUNING_FORKS),
                                2,
                                1,
                                Enchantment.leveledCost(5, 0),
                                Enchantment.leveledCost(17, 0),
                                7,
                                AttributeModifierSlot.MAINHAND
                        ))
                        .addEffect(AttunedEnchantmentEffects.SCREECH)
                        .build(SHRILL.getValue())
        );

        registerable.register(SYMPHONY, Enchantment.builder(Enchantment.definition(
                                itemLookup.getOrThrow(AttunedItemTags.TUNING_FORKS),
                                2,
                                1,
                                Enchantment.leveledCost(5, 0),
                                Enchantment.leveledCost(17, 0),
                                7,
                                AttributeModifierSlot.MAINHAND
                        ))
                        .addEffect(AttunedEnchantmentEffects.DEATHSONG)
                        .build(SYMPHONY.getValue())
        );
    }
}
