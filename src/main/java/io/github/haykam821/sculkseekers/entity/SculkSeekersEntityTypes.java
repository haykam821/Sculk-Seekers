package io.github.haykam821.sculkseekers.entity;

import io.github.haykam821.sculkseekers.SculkSeekers;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class SculkSeekersEntityTypes {
	public static final Identifier SCULK_SEEKER_ID = SculkSeekers.identifier("sculk_seeker");

	public static final EntityType<SculkSeekerEntity> SCULK_SEEKER = FabricEntityTypeBuilder.createMob()
		.entityFactory(SculkSeekerEntity::new)
		.defaultAttributes(SculkSeekerEntity::createSculkSeekerAttributes)
		.dimensions(EntityDimensions.fixed(0.5f, 0.125f))
		.build();

	private SculkSeekersEntityTypes() {
		return;
	}

	public static void register() {
		Registry.register(Registries.ENTITY_TYPE, SCULK_SEEKER_ID, SCULK_SEEKER);

		ServerPlayConnectionEvents.DISCONNECT.register((networkHandler, server) -> {
			SeekerController controller = SeekerController.forPlayer(networkHandler.player);
			controller.setSeeker(null);
		});
	}
}
