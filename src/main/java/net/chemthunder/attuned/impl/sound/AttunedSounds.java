package net.chemthunder.attuned.impl.sound;

import net.chemthunder.attuned.impl.Attuned;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class AttunedSounds {

    public static final SoundEvent TUNING_FORK_IMPACT = registerSound("item.tuning_fork.impact");
    public static final SoundEvent TUNING_FORK_VIBRATE = registerSound("item.tuning_fork.vibrate");
    public static final SoundEvent TUNING_FORK_RESONATE = registerSound("item.tuning_fork.resonate");
    public static final SoundEvent TUNING_FORK_HIT = registerSound("item.tuning_fork.hit");
    public static final SoundEvent TUNING_FORK_SHOCKWAVE = registerSound("item.tuning_fork.shockwave");
    public static final SoundEvent TUNING_FORK_OCTAVE = registerSound("item.tuning_fork.octave");
    public static final SoundEvent TUNING_FORK_SYMPHONY = registerSound("item.tuning_fork.symphony");
    public static final SoundEvent TUNING_FORK_TICK = registerSound("item.tuning_fork.tick");
    public static final SoundEvent TUNING_FORK_MAGNUM_OPUS = registerSound("item.tuning_fork.magnum_opus");


    private static SoundEvent registerSound(String name){
        return Registry.register(Registries.SOUND_EVENT, Identifier.of(Attuned.MOD_ID, name),
                SoundEvent.of(Identifier.of(Attuned.MOD_ID,name)));
    }

    public static void init() {
        //
    }
}
