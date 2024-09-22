package io.github.haykam821.sculkseekers.mixin;

import org.spongepowered.asm.mixin.Mixin;

import io.github.haykam821.sculkseekers.entity.SculkSeekerEntity;
import io.github.haykam821.sculkseekers.entity.SeekerController;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin implements SeekerController {
	private SculkSeekerEntity seeker;

	@Override
	public SculkSeekerEntity getSeeker() {
		if (this.seeker != null && this.seeker.isRemoved()) {
			this.seeker = null;
		}

		return this.seeker;
	}

	@Override
	public void setSeeker(SculkSeekerEntity entity) {
		if (this.seeker != null) {
			this.seeker.setControlled(false);
		}

		this.seeker = entity;

		if (this.seeker != null) {
			this.seeker.setControlled(true);
		}
	}
}
