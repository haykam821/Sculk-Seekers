package io.github.haykam821.sculkseekers.network;

import io.github.haykam821.sculkseekers.SculkSeekers;
import io.github.haykam821.sculkseekers.entity.SculkSeekerEntity;
import io.github.haykam821.sculkseekers.entity.SeekerController;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public final class SculkSeekersNetworking {
	public static final Identifier SEEKER_INPUT_ID = SculkSeekers.identifier("seeker_input");

	private SculkSeekersNetworking() {
		return;
	}

	public static void register() {
		ServerPlayNetworking.registerGlobalReceiver(SEEKER_INPUT_ID, (server, player, handler, buf, responseSender) -> {
			SeekerInput input = SeekerInput.readBuf(buf);

			server.execute(() -> {
				SeekerController controller = SeekerController.forPlayer(player);
				SculkSeekerEntity entity = controller.getSeeker();

				if (entity != null) {
					entity.setNextInput(player, input);
				}
			});
		});
	}
}
