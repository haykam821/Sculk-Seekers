package io.github.haykam821.sculkseekers.entity;

import net.minecraft.server.network.ServerPlayerEntity;

public interface SeekerController {
	public SculkSeekerEntity getSeeker();
	public void setSeeker(SculkSeekerEntity entity);

	public static SeekerController forPlayer(ServerPlayerEntity player) {
		return (SeekerController) player;
	}
}
