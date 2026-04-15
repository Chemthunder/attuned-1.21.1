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
import org.joml.Vector3f;

public record ShockwaveParticleEffect(int color, float size, Vector3f rotation) implements ParticleEffect {

    public static final Codec<Vector3f> ROTATION_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.fieldOf("x").forGetter(Vector3f::x),
            Codec.FLOAT.fieldOf("y").forGetter(Vector3f::y),
            Codec.FLOAT.fieldOf("z").forGetter(Vector3f::z)
    ).apply(instance, Vector3f::new));

    public static final PacketCodec<RegistryByteBuf, Vector3f> ROTATION_PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.FLOAT, Vector3f::x,
            PacketCodecs.FLOAT, Vector3f::y,
            PacketCodecs.FLOAT, Vector3f::z,
            Vector3f::new
    );

    public static final MapCodec<ShockwaveParticleEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            PortingUtils.RGB.fieldOf("color").forGetter(ShockwaveParticleEffect::color),
            Codec.FLOAT.fieldOf("size").forGetter(ShockwaveParticleEffect::size),
            ROTATION_CODEC.fieldOf("rotation").forGetter(ShockwaveParticleEffect::rotation)
    ).apply(instance, ShockwaveParticleEffect::new));

    public static final PacketCodec<RegistryByteBuf, ShockwaveParticleEffect> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER,      ShockwaveParticleEffect::color,
            PacketCodecs.FLOAT,        ShockwaveParticleEffect::size,
            ROTATION_PACKET_CODEC,     ShockwaveParticleEffect::rotation,
            ShockwaveParticleEffect::new
    );


    public ShockwaveParticleEffect(int color, float size, float rotX, float rotY, float rotZ) {
        this(color, size, new Vector3f(rotX, rotY, rotZ));
    }


    public ShockwaveParticleEffect(int color, float size, Direction.Axis axis) {
        this(color, size, switch (axis) {
            case X -> new Vector3f(90.0f,  0.0f, 0.0f);
            case Y -> new Vector3f(0.0f, 0.0f, 90.0f);
            case Z -> new Vector3f(0.0f, 90.0f, 0.0f);
        });
    }

    public ParticleType<?> getType() {
        return AttunedParticles.SHOCKWAVE;
    }
}