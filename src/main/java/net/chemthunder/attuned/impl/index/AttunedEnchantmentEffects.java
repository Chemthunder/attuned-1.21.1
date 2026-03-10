package net.chemthunder.attuned.impl.index;

import net.acoyt.acornlib.api.registrants.EnchantmentEffectRegistrant;
import net.chemthunder.attuned.impl.Attuned;
import net.minecraft.component.ComponentType;
import net.minecraft.util.Unit;

public interface AttunedEnchantmentEffects {
    EnchantmentEffectRegistrant ENCHANT_EFFECTS = new EnchantmentEffectRegistrant(Attuned.MOD_ID);

    ComponentType<Unit> SHOCKWAVE = ENCHANT_EFFECTS.register( "shockwave", builder -> builder.codec(Unit.CODEC));
    ComponentType<Unit> SCREECH = ENCHANT_EFFECTS.register( "screech", builder -> builder.codec(Unit.CODEC));

    static void init() {
        //
    }
}
