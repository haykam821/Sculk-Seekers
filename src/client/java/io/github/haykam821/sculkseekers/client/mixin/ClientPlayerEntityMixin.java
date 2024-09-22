package io.github.haykam821.sculkseekers.client.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.authlib.GameProfile;

import io.github.haykam821.sculkseekers.client.network.SculkSeekersClientNetworking;
import io.github.haykam821.sculkseekers.network.SeekerInput;
import io.github.haykam821.sculkseekers.tag.SculkSeekersItemTags;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends PlayerEntity {
	@Shadow
	@Final
	private Input input;

	public ClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
		super(world, pos, yaw, gameProfile);
	}

	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;tick()V", shift = At.Shift.AFTER))
	private void sendSeekerInput(CallbackInfo ci) {
		SculkSeekersClientNetworking.send(new SeekerInput(this.input.pressingForward, this.input.pressingBack, this.input.pressingLeft, this.input.pressingRight));
	}

	@Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z", shift = At.Shift.BEFORE))
	private void onModifyUsingItemMovement(CallbackInfo ci) {
		if (this.isUsingItem() && !this.hasVehicle() && this.getActiveItem().isIn(SculkSeekersItemTags.USING_PREVENTS_MOVEMENT)) {
			this.input.movementSideways = 0;
			this.input.movementForward = 0;
		}
	}
}
