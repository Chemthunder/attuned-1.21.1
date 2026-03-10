package net.chemthunder.attuned.data;

import net.chemthunder.attuned.impl.Attuned;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class AttunedDynamicRegistryGen extends FabricDynamicRegistryProvider {
    public AttunedDynamicRegistryGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
        entries.addAll(wrapperLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT));
    }

    public String getName() {
        return Attuned.MOD_ID + "_dynamic";
    }
}
