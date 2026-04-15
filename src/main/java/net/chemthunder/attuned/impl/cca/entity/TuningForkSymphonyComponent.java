package net.chemthunder.attuned.impl.cca.entity;

import net.acoyt.acornlib.api.util.MiscUtils;
import net.chemthunder.attuned.impl.Attuned;
import net.chemthunder.attuned.impl.index.data.AttunedDamageTypes;
import net.chemthunder.attuned.impl.sound.AttunedSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class TuningForkSymphonyComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<TuningForkSymphonyComponent> KEY = MiscUtils.getOrCreateKey(Attuned.id("tuning_fork_symphony"), TuningForkSymphonyComponent.class);
    private final PlayerEntity player;

    private int saviorTicks = 0;

    public TuningForkSymphonyComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(player);
    }

    public void tick() {

        if(saviorTicks%40==1){
            player.playSound(AttunedSounds.TUNING_FORK_TICK, 1f,0.95f);
        }
        if(saviorTicks%40==21){
            player.playSound(AttunedSounds.TUNING_FORK_TICK, 1f,1.0f);
        }
        if (saviorTicks > 0) {
            saviorTicks--;
            if (saviorTicks == 0) {
                player.playSound(AttunedSounds.TUNING_FORK_MAGNUM_OPUS, 1f,1f);
                player.damage(player.getDamageSources().create(AttunedDamageTypes.MAGNUM_OPUS), player.getMaxHealth() * player.getMaxHealth() * 9);
                sync();
            }
        }
    }

    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.saviorTicks = nbtCompound.getInt("SaviorTicks");
    }

    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putInt("SaviorTicks", saviorTicks);
    }

    public int getSaviorTicks() {
        return saviorTicks;
    }

    public void setSaviorTicks(int num) {
        this.saviorTicks = num;
        sync();
    }
}

/*
death message: "player finished their magnum opus"

 */
