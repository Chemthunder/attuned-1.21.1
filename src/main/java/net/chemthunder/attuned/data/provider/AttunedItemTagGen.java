package net.chemthunder.attuned.data.provider;

import net.chemthunder.attuned.impl.index.AttunedItems;
import net.chemthunder.attuned.impl.index.tag.AttunedItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class AttunedItemTagGen extends FabricTagProvider.ItemTagProvider {
    public AttunedItemTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(AttunedItemTags.TUNING_FORKS)
                .add(AttunedItems.TUNING_FORK)
                .setReplace(false);
    }
}
