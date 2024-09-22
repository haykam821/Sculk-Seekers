package io.github.haykam821.sculkseekers.client.entity;

import io.github.haykam821.sculkseekers.SculkSeekers;
import io.github.haykam821.sculkseekers.client.SculkSeekersClient;
import io.github.haykam821.sculkseekers.client.entity.model.SculkSeekerEntityModel;
import io.github.haykam821.sculkseekers.entity.SculkSeekerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class SculkSeekerEntityRenderer extends MobEntityRenderer<SculkSeekerEntity, SculkSeekerEntityModel<SculkSeekerEntity>> {
	private static final Identifier TEXTURE = SculkSeekers.identifier("textures/entity/sculk_seeker.png");

	public SculkSeekerEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new SculkSeekerEntityModel<>(context.getPart(SculkSeekersClient.SCULK_SEEKER_LAYER)), 0.3f);
	}

	@Override
	protected float getLyingAngle(SculkSeekerEntity entity) {
		return 180;
	}

	@Override
	public Identifier getTexture(SculkSeekerEntity entity) {
		return TEXTURE;
	}

	@Override
	protected int getBlockLight(SculkSeekerEntity entity, BlockPos pos) {
		if (entity.isControlled()) {
			return 15;
		}

		return super.getBlockLight(entity, pos);
	}
}
