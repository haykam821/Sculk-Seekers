package io.github.haykam821.sculkseekers.data.provider;

import java.util.concurrent.CompletableFuture;

import io.github.haykam821.sculkseekers.entity.SculkSeekersEntityTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.EntityTypeTags;

public class SculkSeekersEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {
	public SculkSeekersEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(WrapperLookup registries) {
		this.getOrCreateTagBuilder(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(SculkSeekersEntityTypes.SCULK_SEEKER);
		this.getOrCreateTagBuilder(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(SculkSeekersEntityTypes.SCULK_SEEKER);
	}
}
