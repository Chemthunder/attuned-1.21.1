package net.chemthunder.attuned.impl.client.particle;

import net.acoyt.acornlib.api.util.PortingUtils;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ShockwaveParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteWithAge;
    private final Quaternionf quaternion;
    private final Vector3f color;

    public ShockwaveParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteWithAge, ShockwaveParticleEffect effect) {
        super(world, x, y, z, 0.0, 0.0, 0.0);

        this.gravityStrength = 0.0F;
        this.scale = effect.size();
        this.maxAge = 12;
        this.velocityX = 0.0F;
        this.velocityY = 0.0F;
        this.velocityZ = 0.0F;


        this.quaternion = new Quaternionf()
                .rotateX((float) Math.toRadians(effect.rotation().x()))
                .rotateY((float) Math.toRadians(effect.rotation().y()))
                .rotateZ((float) Math.toRadians(effect.rotation().z()));

        this.spriteWithAge = spriteWithAge;
        this.setSpriteForAge(spriteWithAge);
        this.color = PortingUtils.toVector(effect.color());
    }

    public void method_60373(VertexConsumer buffer, Camera camera, Quaternionf ignored, float ticks) {
        this.setSpriteForAge(this.spriteWithAge);
        this.setColor(this.color.x, this.color.y, this.color.z);

        Vec3d camPos = camera.getPos();
        float x = (float) (MathHelper.lerp(ticks, this.prevPosX, this.x) - camPos.getX());
        float y = (float) (MathHelper.lerp(ticks, this.prevPosY, this.y) - camPos.getY());
        float z = (float) (MathHelper.lerp(ticks, this.prevPosZ, this.z) - camPos.getZ());
        float size = this.getSize(ticks);


        Vector3f[] verts = {
                new Vector3f(0.0F, -1.0F, -1.0F),
                new Vector3f(0.0F, -1.0F,  1.0F),
                new Vector3f(0.0F,  1.0F,  1.0F),
                new Vector3f(0.0F,  1.0F, -1.0F)
        };

        for (Vector3f v : verts) {
            v.rotate(this.quaternion);
            v.mul(size);
            v.add(x, y + 0.01f, z);
        }

        float u0 = this.getMinU(), u1 = this.getMaxU();
        float v0 = this.getMinV(), v1 = this.getMaxV();
        int light = this.getBrightness(ticks);

        buffer.vertex(verts[0].x(), verts[0].y(), verts[0].z()).texture(u1, v1).color(this.red, this.green, this.blue, this.alpha).light(light);
        buffer.vertex(verts[1].x(), verts[1].y(), verts[1].z()).texture(u1, v0).color(this.red, this.green, this.blue, this.alpha).light(light);
        buffer.vertex(verts[2].x(), verts[2].y(), verts[2].z()).texture(u0, v0).color(this.red, this.green, this.blue, this.alpha).light(light);
        buffer.vertex(verts[3].x(), verts[3].y(), verts[3].z()).texture(u0, v1).color(this.red, this.green, this.blue, this.alpha).light(light);

        buffer.vertex(verts[3].x(), verts[3].y(), verts[3].z()).texture(u0, v1).color(this.red, this.green, this.blue, this.alpha).light(light);
        buffer.vertex(verts[2].x(), verts[2].y(), verts[2].z()).texture(u0, v0).color(this.red, this.green, this.blue, this.alpha).light(light);
        buffer.vertex(verts[1].x(), verts[1].y(), verts[1].z()).texture(u1, v0).color(this.red, this.green, this.blue, this.alpha).light(light);
        buffer.vertex(verts[0].x(), verts[0].y(), verts[0].z()).texture(u1, v1).color(this.red, this.green, this.blue, this.alpha).light(light);
    }

    public int getBrightness(float tint) {
        return 240;
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    public record Factory(SpriteProvider spriteProvider) implements ParticleFactory<ShockwaveParticleEffect> {
        @Override
        public Particle createParticle(ShockwaveParticleEffect parameters, ClientWorld world, double x, double y, double z, double vx, double vy, double vz) {
            return new ShockwaveParticle(world, x, y, z, this.spriteProvider, parameters);
        }
    }
}