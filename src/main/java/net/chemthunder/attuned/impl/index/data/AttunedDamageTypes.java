package net.chemthunder.attuned.impl.index.data;

import net.chemthunder.attuned.impl.Attuned;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public interface AttunedDamageTypes {
    RegistryKey<DamageType> MAGNUM_OPUS = create("magnum_opus");

    private static RegistryKey<DamageType> create(String id) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Attuned.id(id));
    }

    static void bootstrap(Registerable<DamageType> registerable) {
        registerable.register(MAGNUM_OPUS, new DamageType("magnum_opus", 0.0f));
    }

    static DamageSource create(World world, RegistryKey<DamageType> key, @Nullable Entity source, @Nullable Entity attacker) {
        return new DamageSource(world.getRegistryManager().getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE).getOrThrow(key), source, attacker);
    }

    static DamageSource create(World world, RegistryKey<DamageType> key, @Nullable Entity attacker) {
        return new DamageSource(world.getRegistryManager().getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE).getOrThrow(key), attacker);
    }

    static DamageSource create(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE).getOrThrow(key));
    }
}
