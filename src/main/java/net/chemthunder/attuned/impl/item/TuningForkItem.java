package net.chemthunder.attuned.impl.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.acoyt.acornlib.api.item.SprintUsableItem;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.acoyt.acornlib.api.util.ParticleUtils;
import net.acoyt.acornlib.impl.index.AcornParticles;
import net.chemthunder.attuned.impl.Attuned;
import net.chemthunder.attuned.impl.cca.entity.TuningForkShockwaveComponent;
import net.chemthunder.attuned.impl.cca.entity.TuningForkSymphonyComponent;
import net.chemthunder.attuned.impl.client.particle.ShockwaveParticleEffect;
import net.chemthunder.attuned.impl.index.AttunedDataComponents;
import net.chemthunder.attuned.impl.index.AttunedEnchantmentEffects;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class TuningForkItem extends Item implements ModelVaryingItem, CustomHitParticleItem, ColorableItem, CustomHitSoundItem, SprintUsableItem {
    public static final int maxCharges = 30;
    public static final SimpleParticleType[] EFFECTS = new SimpleParticleType[]{
            AcornParticles.LIGHT_GRAY_SWEEP,
            AcornParticles.GRAY_SWEEP
    };

    public static final SimpleParticleType[] ALT_EFFECTS = new SimpleParticleType[]{
            AcornParticles.MAGENTA_SWEEP,
            AcornParticles.BLACK_SWEEP
    };

    public static final SimpleParticleType[] AMARITE_EFFECTS = new SimpleParticleType[]{
            AcornParticles.PURPLE_SWEEP,
            AcornParticles.GOLD_SWEEP
    };

    public static final SimpleParticleType[] WIMDY_EFFECTS = new SimpleParticleType[]{
            AcornParticles.LIGHT_GRAY_SWEEP,
            AcornParticles.WHITE_SWEEP
    };

    public int startColor(ItemStack itemStack) {
        if (getSkin(itemStack) == 1) {
            return 0xFF2d2534;
        }
        if (getSkin(itemStack) == 2) {
            return 0xFF632c6c;
        }
        if (getSkin(itemStack) == 3) {
            return 0xFF2e304d;
        }
        return 0xFF2a2e28;
    }

    public int endColor(ItemStack itemStack) {
        if (getSkin(itemStack) == 1) {
            return 0xFF7a6873;
        }
        if (getSkin(itemStack) == 2) {
            return 0xFFf5cf5d;
        }
        if (getSkin(itemStack) == 3) {
            return 0xFFbfafbd;
        }
        return 0xFFa99797;
    }

    public int backgroundColor(ItemStack itemStack) {
        if (getSkin(itemStack) == 1) {
            return 0xFF0c070e;
        }
        if (getSkin(itemStack) == 2) {
            return 0xFF180f17;
        }
        if (getSkin(itemStack) == 3) {
            return 0xFF100d13;
        }
        return 0xF0171315;
    }

    public TuningForkItem(Item.Settings settings) {
        super(settings.component(AttunedDataComponents.CHARGES, 0).component(AttunedDataComponents.SKIN, 0).component(AttunedDataComponents.HELD_TICKS, 0));
    }

    public boolean hasOctave(ItemStack stack) {
        return EnchantmentHelper.hasAnyEnchantmentsWith(stack, AttunedEnchantmentEffects.SHOCKWAVE);
    }

    public boolean hasShrill(ItemStack stack) {
        return EnchantmentHelper.hasAnyEnchantmentsWith(stack, AttunedEnchantmentEffects.SCREECH);
    }

    public boolean hasSymphony(ItemStack stack) {
        return EnchantmentHelper.hasAnyEnchantmentsWith(stack, AttunedEnchantmentEffects.DEATHSONG);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return super.use(world, user, hand);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            ItemStack stack = player.getStackInHand(player.getActiveHand());

            if (context.getWorld().getBlockState(context.getBlockPos()).isOf(Blocks.SMITHING_TABLE)) {
                var skin = AttunedDataComponents.SKIN;
                int result = 0;

                switch (stack.getOrDefault(skin, 0)) {
                    case 0 -> result = 1;
                    case 1 -> result = 2;
                    case 2 -> result = 3;
                    case 3 -> result = 0;
                }

                stack.set(skin, result);
                player.stopUsingItem();

                player.swingHand(player.getActiveHand());
                player.playSoundToPlayer(SoundEvents.BLOCK_SMITHING_TABLE_USE, SoundCategory.BLOCKS, 1, 1);
            }
        }

        return super.useOnBlock(context);
    }

    public Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity) {
        String skinId;

        switch (getSkin(stack)) {
            case 1 -> skinId = "skin/valediction";
            case 2 -> skinId = "skin/amarite";
            case 3 -> skinId = "skin/wimdy";
            default -> skinId = "tuning_fork";
        }

        if (entity != null) {
            if (entity.isUsingItem()) {
                return MiscUtils.isGui(renderMode) ? Attuned.id(skinId) : Attuned.id(skinId + "_blocking");
            }
        }
        return MiscUtils.isGui(renderMode) ? Attuned.id(skinId) : Attuned.id(skinId + "_handheld");
    }

    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Attuned.id("tuning_fork"),
                Attuned.id("tuning_fork_handheld"),
                Attuned.id("tuning_fork_blocking"),
                Attuned.id("skin/valediction"),
                Attuned.id("skin/valediction_handheld"),
                Attuned.id("skin/valediction_blocking"),
                Attuned.id("skin/amarite"),
                Attuned.id("skin/amarite_handheld"),
                Attuned.id("skin/amarite_blocking"),
                Attuned.id("skin/wimdy"),
                Attuned.id("skin/wimdy_handheld"),
                Attuned.id("skin/wimdy_blocking")
        );
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 7.0f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.6f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(Identifier.ofVanilla("base_entity_interaction_range"), 1.0f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    public void spawnHitParticles(PlayerEntity playerEntity, Entity target) {
        ItemStack stack = playerEntity.getStackInHand(Hand.MAIN_HAND);
        switch (getSkin(stack)) {
            case 1 -> ParticleUtils.spawnSweepParticles(ALT_EFFECTS[playerEntity.getRandom().nextInt(ALT_EFFECTS.length)], playerEntity);
            case 2 -> ParticleUtils.spawnSweepParticles(AMARITE_EFFECTS[playerEntity.getRandom().nextInt(AMARITE_EFFECTS.length)], playerEntity);
            case 3 -> ParticleUtils.spawnSweepParticles(WIMDY_EFFECTS[playerEntity.getRandom().nextInt(WIMDY_EFFECTS.length)], playerEntity);
            default -> ParticleUtils.spawnSweepParticles(EFFECTS[playerEntity.getRandom().nextInt(EFFECTS.length)], playerEntity);
        }
    }

    public Text getName(ItemStack stack) {
        if (getSkin(stack) == 1) {
            return Text.literal("Valediction").withColor(0x7d153b);
        }

        if (getSkin(stack) == 2) {
            return Text.literal("Amarite Tuning Fork").withColor(0xf5cf5d);
        }

        if (getSkin(stack) == 3) {
            return Text.literal("Galestar").withColor(0x918197);
        }
        return super.getName(stack).copy().withColor(0xFFa99797);
    }

    public static int getSkin(ItemStack stack) {
        return stack.getOrDefault(AttunedDataComponents.SKIN, 0);
    }

    public void playHitSound(PlayerEntity player, Entity target) {
        ItemStack stack = player.getMainHandStack();
        player.playSound(attuned$specificSounds(stack), 1, (float) (1.0f + player.getRandom().nextGaussian() / 10.0f));
    }

    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    public int attuned$shockwaveColors(ItemStack stack) {
        int returnedValue;

        switch (getSkin(stack)) {
            case 1 -> returnedValue = 0xFF7d153b;
            case 2 -> returnedValue = 0xFFf5cf5d;
            case 3 -> returnedValue = 0xFFb69bc8;
            default -> returnedValue = 0xFFffffff;
        }

        return returnedValue;
    }

    public SoundEvent attuned$specificSounds(ItemStack stack) {
        SoundEvent returnedValue;

        switch (getSkin(stack)) {
            case 1 -> returnedValue = SoundEvents.BLOCK_MANGROVE_ROOTS_BREAK;
            case 2 -> returnedValue = SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK;
            case 3 -> returnedValue = SoundEvents.ENTITY_BREEZE_SHOOT;
            default -> returnedValue = SoundEvents.BLOCK_ANCIENT_DEBRIS_BREAK;
        }

        return returnedValue;
    }

    public void attuned$tuningForkParry(PlayerEntity player, LivingEntity source, World world, ItemStack stack) {
        if (world instanceof ServerWorld serverWorld) {
            Vec3d pos = player.getPos();
            player.setVelocity(player.getRotationVec(0).multiply(-1.4f));
            player.velocityModified = true;

            player.stopUsingItem();

            player.getItemCooldownManager().set(this, 90);

            serverWorld.spawnParticles(
                    new ShockwaveParticleEffect(
                            attuned$shockwaveColors(stack),
                            3,
                            Direction.Axis.Y
                    ),
                    pos.x, pos.y + 0.5f, pos.z,
                    1,
                    0.0, 0.0, 0.0,
                    0.1
            );

            player.playSoundToPlayer(attuned$specificSounds(stack),SoundCategory.PLAYERS, 1, (float) (1.0f + player.getRandom().nextGaussian() / 10.0f));
        }
    }

    public void attuned$tuningForkSymphony(PlayerEntity player, LivingEntity source, World world, ItemStack stack) {
        player.setVelocity(0, 0, 0);
        source.setVelocity(0, 0, 0);

        if (source instanceof PlayerEntity targetPlayer) {
            targetPlayer.getItemCooldownManager().set(targetPlayer.getMainHandStack().getItem(), 40);
        }

        TuningForkSymphonyComponent symphonyComponent = TuningForkSymphonyComponent.KEY.get(player);

        symphonyComponent.setSaviorTicks(400);
    }

    public int getItemBarStep(ItemStack stack) {
        return Math.round((float) stack.getOrDefault(AttunedDataComponents.HELD_TICKS, 0) / maxCharges * 13);
    }

    public boolean isItemBarVisible(ItemStack stack) {
        return hasOctave(stack);
    }

    public int getItemBarColor(ItemStack stack) {
        return attuned$shockwaveColors(stack);
    }

    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        var heldTicks = stack.getOrDefault(AttunedDataComponents.HELD_TICKS, 0);
        Hand hand = user.getActiveHand();

        if (hasOctave(user.getStackInHand(hand))) {
            if (heldTicks != maxCharges) {
                user.getStackInHand(hand).set(AttunedDataComponents.HELD_TICKS, heldTicks + 1);
            }
        }

        super.usageTick(world, user, stack, remainingUseTicks);
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        var HELDTICKS = stack.getOrDefault(AttunedDataComponents.HELD_TICKS, 0);

        if (EnchantmentHelper.hasAnyEnchantmentsWith(stack, AttunedEnchantmentEffects.SHOCKWAVE)) {
            if (user != null) {
                if (user instanceof PlayerEntity player) {
                    if (world instanceof ServerWorld serverWorld) {
                        if (HELDTICKS >= 2) {
                            player.setVelocity(player.getRotationVec(0).multiply((double) HELDTICKS / 14));
                            player.velocityModified = true;

                            serverWorld.spawnParticles(
                                    new ShockwaveParticleEffect(
                                            attuned$shockwaveColors(stack),
                                            3,
                                            Direction.Axis.Y
                                    ),
                                    player.getX(),
                                    player.getY() + 0.5f,
                                    player.getZ(),
                                    1,
                                    0,
                                    0,
                                    0,
                                    0.1f
                            );

                            stack.set(AttunedDataComponents.HELD_TICKS, 0);

                            if (!player.isCreative()) {
                                player.getItemCooldownManager().set(this, 130);
                            }

                            player.playSoundToPlayer(attuned$specificSounds(stack),SoundCategory.PLAYERS, 1, (float) (1.0f + player.getRandom().nextGaussian() / 10.0f));
                            TuningForkShockwaveComponent.KEY.get(player).setSaviorTicks(90);
                        }
                    }
                }
            }
        }
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return !ItemStack.areItemsEqual(oldStack, newStack);
    }

    public boolean isEnchantable(ItemStack stack) {
        return true;
    }
}

// OCTAVE
/*
Upon use, negate fall damage for a few secs
 */

// SYMPHONY
/*
While blocking, if you die you get 10s in which you take ZERO damage. After this runs out, you will die instantly.
 */