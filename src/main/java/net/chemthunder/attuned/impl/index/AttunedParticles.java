package net.chemthunder.attuned.impl.index;

import net.acoyt.acornlib.api.registrants.ParticleTypeRegistrant;
import net.chemthunder.attuned.impl.Attuned;
import net.chemthunder.attuned.impl.client.particle.ShockwaveParticle;
import net.chemthunder.attuned.impl.client.particle.ShockwaveParticleEffect;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface AttunedParticles {
    ParticleType<ShockwaveParticleEffect> SHOCKWAVE = create("shockwave", FabricParticleTypes.complex(true, ShockwaveParticleEffect.CODEC, ShockwaveParticleEffect.PACKET_CODEC));

    private static <T extends ParticleType<?>> T create(String name, T particle) {
        return Registry.register(Registries.PARTICLE_TYPE, Attuned.id(name), particle);
    }

    static void init() {

    }

    static void clientInit() {
        ParticleFactoryRegistry.getInstance().register(SHOCKWAVE, ShockwaveParticle.Factory::new);
    }
}
