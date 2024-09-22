package io.github.haykam821.sculkseekers.client;

import io.github.haykam821.sculkseekers.client.entity.SculkSeekerEntityRenderer;
import io.github.haykam821.sculkseekers.client.entity.model.SculkSeekerEntityModel;
import io.github.haykam821.sculkseekers.entity.SculkSeekersEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;

public class SculkSeekersClient implements ClientModInitializer {
	public static final EntityModelLayer SCULK_SEEKER_LAYER = new EntityModelLayer(SculkSeekersEntityTypes.SCULK_SEEKER_ID, EntityModelLayers.MAIN);

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(SculkSeekersEntityTypes.SCULK_SEEKER, SculkSeekerEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(SCULK_SEEKER_LAYER, SculkSeekerEntityModel::getTexturedModelData);
	}
}
