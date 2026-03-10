package net.chemthunder.attuned.data.provider;

import net.chemthunder.attuned.impl.index.AttunedBlocks;
import net.chemthunder.attuned.impl.index.AttunedItems;
import net.chemthunder.attuned.impl.index.data.AttunedEnchantments;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class AttunedLangGen extends FabricLanguageProvider {
    public AttunedLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        AttunedItems.ITEMS.registerLang(wrapperLookup, translationBuilder);

        translationBuilder.add(AttunedEnchantments.OCTAVE.getValue(), "Octave");
        translationBuilder.add("attuned.enchantment.octave.desc", "Swaps the boost from blocking to a long-range dash in any direction.");
    }
}
