package io.github.haykam821.sculkseekers.network;

import net.minecraft.network.PacketByteBuf;

public record SeekerInput(boolean forward, boolean backward, boolean left, boolean right) {
	public boolean isEmpty() {
		return !this.forward() && !this.backward() && !this.left() && !this.right();
	}

	public void writeBuf(PacketByteBuf buf) {
		buf.writeBoolean(this.forward());
		buf.writeBoolean(this.backward());

		buf.writeBoolean(this.left());
		buf.writeBoolean(this.right());
	}

	public static SeekerInput readBuf(PacketByteBuf buf) {
		boolean forward = buf.readBoolean();
		boolean backward = buf.readBoolean();

		boolean left = buf.readBoolean();
		boolean right = buf.readBoolean();

		return new SeekerInput(forward, backward, left, right);
	}
}
