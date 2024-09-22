package io.github.haykam821.sculkseekers.entity;

import org.joml.Vector3d;

import io.github.haykam821.sculkseekers.item.SculkSeekersItems;
import io.github.haykam821.sculkseekers.item.SeekerControllerItem;
import io.github.haykam821.sculkseekers.network.SeekerInput;
import io.github.haykam821.sculkseekers.sound.SculkSeekersSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.Vibrations;

public class SculkSeekerEntity extends MobEntity {
	public static final TrackedData<Boolean> CONTROLLED = DataTracker.registerData(SculkSeekerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

	private SeekerInput input;

	public SculkSeekerEntity(EntityType<? extends SculkSeekerEntity> type, World world) {
		super(type, world);
	}

	public boolean isControlled() {
		return this.dataTracker.get(CONTROLLED);
	}

	public void setControlled(boolean controlled) {
		if (controlled != this.isControlled()) {
			SoundEvent sound = controlled ? this.getClickingSound() : this.getClickingStopSound();
			this.playSoundIfNotSilent(sound);
		}

		this.dataTracker.set(CONTROLLED, controlled);
	}

	public void setNextInput(ServerPlayerEntity player, SeekerInput input) {
		Vec3d handPos = SeekerControllerItem.getHandPos(player, player.getActiveItem());
		Vec3d pos = SeekerControllerItem.getBodyPos(this, 0.5);

		if (handPos.squaredDistanceTo(pos) > SeekerControllerItem.SQUARED_MAX_DISTANCE) return;
		if (Vibrations.VibrationListener.isOccluded(player.getWorld(), handPos, pos)) return;

		this.input = input;
	}

	@Override
	public void tick() {
		super.tick();

		if (this.getWorld().isClient()) {
			this.prevBodyYaw = this.prevHeadYaw;
			this.bodyYaw = this.headYaw;
		}
	}

	@Override
	protected void mobTick() {
		if (this.input == null) {
			return;
		}

		// Apply forward/backward movement
		if (this.input.forward() || this.input.backward()) {
			double movementSpeed = this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED);

			if (!this.input.forward()) {
				movementSpeed *= -0.7;
			}

			if (!this.isOnGround()) {
				movementSpeed *= 0.5;
			}

			Vector3d velocity = new Vector3d(0, 0, movementSpeed);
			velocity.rotateY(-this.getYaw() * MathHelper.RADIANS_PER_DEGREE);

			this.addVelocity(new Vec3d(velocity.x(), velocity.y(), velocity.z()));
		}

		// Apply rotation
		if (this.input.left() != this.input.right()) {
			float slipperiness = this.getSteppingBlockState().getBlock().getSlipperiness();
			float rotationSpeed = (float) (Math.pow(slipperiness, 2) * 7 + 3);

			if (this.input.left()) {
				rotationSpeed *= -1;
			}

			float yaw = this.getYaw() + rotationSpeed;

			this.setYaw(yaw);
			this.setHeadYaw(yaw);
			this.setBodyYaw(yaw);
		}

		this.input = null;
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(CONTROLLED, false);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SculkSeekersSoundEvents.ENTITY_SCULK_SEEKER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SculkSeekersSoundEvents.ENTITY_SCULK_SEEKER_DEATH;
	}

	protected SoundEvent getClickingSound() {
		return SculkSeekersSoundEvents.ENTITY_SCULK_SEEKER_CLICKING;
	}

	protected SoundEvent getClickingStopSound() {
		return SculkSeekersSoundEvents.ENTITY_SCULK_SEEKER_CLICKING_STOP;
	}

	@Override
	protected float getSoundVolume() {
		return 0.4f;
	}

	@Override
	public boolean occludeVibrationSignals() {
		return true;
	}

	@Override
	public boolean canBeLeashedBy(PlayerEntity player) {
		return false;
	}

	@Override
	public ItemStack getPickBlockStack() {
		return new ItemStack(SculkSeekersItems.SCULK_SEEKER);
	}

	public static DefaultAttributeContainer.Builder createSculkSeekerAttributes() {
		return MobEntity.createMobAttributes()
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 4)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1);
	}
}
