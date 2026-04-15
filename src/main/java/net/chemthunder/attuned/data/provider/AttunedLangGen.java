package net.chemthunder.attuned.data.provider;

import net.chemthunder.attuned.impl.index.AttunedItems;
import net.chemthunder.attuned.impl.index.AttunedStatusEffects;
import net.chemthunder.attuned.impl.index.data.AttunedDamageTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class AttunedLangGen extends FabricLanguageProvider {
    public AttunedLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        AttunedItems.ITEMS.registerLang(wrapperLookup, translationBuilder);
        AttunedStatusEffects.STATUS_EFFECTS.registerLang(wrapperLookup, translationBuilder);

        translationBuilder.add("enchantment.attuned.octave", "Octave");
        translationBuilder.add("enchantment.attuned.octave.desc", "Swaps the boost from blocking to a long-range dash in any direction.");

        translationBuilder.add("enchantment.attuned.shrill", "Shrill");
        translationBuilder.add("enchantment.attuned.shrill.desc", "Allows the user to release a large shockwave upon blocking a hit, deafening nearby enemies.");

        translationBuilder.add("enchantment.attuned.symphony", "Symphony");
        translationBuilder.add("enchantment.attuned.symphony.desc", "If the user dies while blocking, they will be kept alive for ten extra seconds. Upon running out of time, they will instantly die.");


        translationBuilder.add("sounds.attuned.tuning_fork_hit", "Constructive interference");
        translationBuilder.add("sounds.attuned.tuning_fork_shockwave", "Destructive interference");
        translationBuilder.add("sounds.attuned.tuning_fork_octave", "Quindicesima");
        translationBuilder.add("sounds.attuned.tuning_fork_shrill", "Forzando");
        translationBuilder.add("sounds.attuned.tuning_fork_symphony", "Crescendo");

        registerDamageType(translationBuilder,
                AttunedDamageTypes.MAGNUM_OPUS,
                "%1$s finished their magnum opus",
                "%1$s finished their magnum opus whilst fighting %2$s wielding %3$s",
                "%1$s finished their magnum opus whilst fighting %2$s"
        );
    }

    public void registerDamageType(TranslationBuilder builder, RegistryKey<DamageType> registryKey, String normal, String item, String player) {
        String key = "death.attack." + registryKey.getValue().getPath();
        builder.add(key, normal);
        builder.add(key + ".item", item);
        builder.add(key + ".player", player);
    }
}
