package net.chemthunder.attuned.impl.client.particle;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.acoyt.acornlib.api.util.PortingUtils;
import net.chemthunder.attuned.impl.index.AttunedParticles;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.math.Direction;

public record ShockwaveParticleEffect(int color, float size, String axis) implements ParticleEffect {
    public static final MapCodec<ShockwaveParticleEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            PortingUtils.RGB.fieldOf("color").forGetter(ShockwaveParticleEffect::color),
            Codec.FLOAT.fieldOf("size").forGetter(ShockwaveParticleEffect::size),
            Codec.STRING.fieldOf("axis").forGetter(ShockwaveParticleEffect::axis)
    ).apply(instance, ShockwaveParticleEffect::new));

    public static final PacketCodec<RegistryByteBuf, ShockwaveParticleEffect> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER,
            ShockwaveParticleEffect::color,
            PacketCodecs.FLOAT,
            ShockwaveParticleEffect::size,
            PacketCodecs.STRING,
            ShockwaveParticleEffect::axis,
            ShockwaveParticleEffect::new
    );

    public ShockwaveParticleEffect(int color, float size, Direction.Axis axis) {
        this(color, size, axis.getName());
    }

    @Override
    public ParticleType<?> getType() {
        return AttunedParticles.SHOCKWAVE;
    }

    public Direction.Axis getAxis() {
        for (Direction.Axis directionAxis : Direction.Axis.values()) if (directionAxis.getName().equalsIgnoreCase(this.axis)) return directionAxis;
        return Direction.Axis.Y;
    }
}