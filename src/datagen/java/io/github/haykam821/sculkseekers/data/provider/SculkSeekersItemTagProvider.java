package io.github.haykam821.sculkseekers.data.provider;

import java.util.concurrent.CompletableFuture;

import io.github.haykam821.sculkseekers.item.SculkSeekersItems;
import io.github.haykam821.sculkseekers.tag.SculkSeekersItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public class SculkSeekersItemTagProvider extends FabricTagProvider.ItemTagProvider {
	public SculkSeekersItemTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(WrapperLookup registries) {
		this.getOrCreateTagBuilder(SculkSeekersItemTags.USING_PREVENTS_MOVEMENT).add(SculkSeekersItems.SEEKER_CONTROLLER);
	}
}
