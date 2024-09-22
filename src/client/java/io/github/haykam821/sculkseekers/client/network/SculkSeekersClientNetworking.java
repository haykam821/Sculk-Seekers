package io.github.haykam821.sculkseekers.client.network;

import io.github.haykam821.sculkseekers.network.SculkSeekersNetworking;
import io.github.haykam821.sculkseekers.network.SeekerInput;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;

public final class SculkSeekersClientNetworking {
	private SculkSeekersClientNetworking() {
		return;
	}

	public static void send(SeekerInput input) {
		if (input.isEmpty()) {
			return;
		}

		PacketByteBuf buf = PacketByteBufs.create();
		input.writeBuf(buf);

		ClientPlayNetworking.send(SculkSeekersNetworking.SEEKER_INPUT_ID, buf);
	}
}
