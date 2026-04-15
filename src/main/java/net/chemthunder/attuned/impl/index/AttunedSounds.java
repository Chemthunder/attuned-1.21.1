package net.chemthunder.attuned.impl.index;

import net.acoyt.acornlib.api.registrants.SoundEventRegistrant;
import net.chemthunder.attuned.impl.Attuned;
import net.minecraft.sound.SoundEvent;

public interface AttunedSounds {
    SoundEventRegistrant SOUND_EVENTS = new SoundEventRegistrant(Attuned.MOD_ID);

    SoundEvent TUNING_FORK_IMPACT = SOUND_EVENTS.register("item.tuning_fork.impact");
    SoundEvent TUNING_FORK_VIBRATE = SOUND_EVENTS.register("item.tuning_fork.vibrate");
    SoundEvent TUNING_FORK_RESONATE = SOUND_EVENTS.register("item.tuning_fork.resonate");
    SoundEvent TUNING_FORK_HIT = SOUND_EVENTS.register("item.tuning_fork.hit");
    SoundEvent TUNING_FORK_SHOCKWAVE = SOUND_EVENTS.register("item.tuning_fork.shockwave");
    SoundEvent TUNING_FORK_OCTAVE = SOUND_EVENTS.register("item.tuning_fork.octave");
    SoundEvent TUNING_FORK_SYMPHONY = SOUND_EVENTS.register("item.tuning_fork.symphony");
    SoundEvent TUNING_FORK_TICK = SOUND_EVENTS.register("item.tuning_fork.tick");
    SoundEvent TUNING_FORK_MAGNUM_OPUS = SOUND_EVENTS.register("item.tuning_fork.magnum_opus");

    static void init() {}
}
