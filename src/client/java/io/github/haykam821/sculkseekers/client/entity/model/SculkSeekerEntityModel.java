package io.github.haykam821.sculkseekers.client.entity.model;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class SculkSeekerEntityModel<T extends Entity> extends SinglePartEntityModel<T> {
	private final ModelPart root;

	public SculkSeekerEntityModel(ModelPart root) {
		this.root = root;
	}

	@Override
	public ModelPart getPart() {
		return this.root;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		return;
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData data = new ModelData();
		ModelPartData root = data.getRoot();

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-3, -2, -4, 6, 2, 8), ModelTransform.pivot(0, 24, 0));
		body.addChild("tendril", ModelPartBuilder.create().uv(0, 10).cuboid(-3, -8, 0, 6, 8, 0), ModelTransform.of(0, -2, 4, -25 * MathHelper.RADIANS_PER_DEGREE, 0, 0));

		return TexturedModelData.of(data, 32, 32);
	}
}
