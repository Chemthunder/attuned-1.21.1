package net.chemthunder.attuned.impl.cca;

import net.chemthunder.attuned.impl.cca.entity.TuningForkShockwaveComponent;
import net.chemthunder.attuned.impl.cca.entity.TuningForkSymphonyComponent;
import net.chemthunder.attuned.impl.item.TuningForkItem;
import net.minecraft.entity.player.PlayerEntity;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class AttunedComponents implements EntityComponentInitializer {

    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, TuningForkShockwaveComponent.KEY).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(TuningForkShockwaveComponent::new);
        registry.beginRegistration(PlayerEntity.class, TuningForkSymphonyComponent.KEY).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(TuningForkSymphonyComponent::new);
    }
}
