package net.chemthunder.attuned.data;

import net.chemthunder.attuned.data.provider.AttunedItemTagGen;
import net.chemthunder.attuned.data.provider.AttunedLangGen;
import net.chemthunder.attuned.data.provider.AttunedModelGen;
import net.chemthunder.attuned.impl.index.AttunedDataComponents;
import net.chemthunder.attuned.impl.index.data.AttunedEnchantments;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class AttunedDataGen implements DataGeneratorEntrypoint {
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(AttunedModelGen::new);
        pack.addProvider(AttunedLangGen::new);
        pack.addProvider(AttunedItemTagGen::new);

        pack.addProvider(AttunedDynamicRegistryGen::new);
	}

    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, AttunedEnchantments::bootstrap);
    }
}
