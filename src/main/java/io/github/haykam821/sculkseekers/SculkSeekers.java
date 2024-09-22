package io.github.haykam821.sculkseekers;

import io.github.haykam821.sculkseekers.entity.SculkSeekersEntityTypes;
import io.github.haykam821.sculkseekers.item.SculkSeekersItems;
import io.github.haykam821.sculkseekers.network.SculkSeekersNetworking;
import io.github.haykam821.sculkseekers.sound.SculkSeekersSoundEvents;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class SculkSeekers implements ModInitializer {
	private static final String MOD_ID = "sculkseekers";

	@Override
	public void onInitialize() {
		SculkSeekersEntityTypes.register();
		SculkSeekersItems.register();
		SculkSeekersNetworking.register();
		SculkSeekersSoundEvents.initialize();
	}

	public static Identifier identifier(String path) {
		return new Identifier(MOD_ID, path);
	}
}
