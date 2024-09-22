package io.github.haykam821.sculkseekers.item;

import io.github.haykam821.sculkseekers.entity.SculkSeekerEntity;
import io.github.haykam821.sculkseekers.entity.SeekerController;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.VibrationParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.EntityPositionSource;
import net.minecraft.world.event.Vibrations;

public class SeekerControllerItem extends Item {
	private static final double MAX_DISTANCE = 32;
	public static final double SQUARED_MAX_DISTANCE = MAX_DISTANCE * MAX_DISTANCE;

	public SeekerControllerItem(Item.Settings settings) {
		super(settings);
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (world instanceof ServerWorld serverWorld && user instanceof ServerPlayerEntity player) {
			SeekerController controller = SeekerController.forPlayer(player);

			if (controller.getSeeker() == null) {
				Box box = player.getBoundingBox().expand(MAX_DISTANCE);
				Vec3d handPos = getHandPos(player, stack);

				TargetPredicate predicate = TargetPredicate.createNonAttackable()
					.setBaseMaxDistance(MAX_DISTANCE)
					.setPredicate(entity -> {
						if (entity instanceof SculkSeekerEntity seeker && seeker.isControlled()) {
							return false;
						}

						return !Vibrations.VibrationListener.isOccluded(world, handPos, getBodyPos(entity, 0.5));
					});

				SculkSeekerEntity entity = world.getClosestEntity(SculkSeekerEntity.class, predicate, player, handPos.getX(), handPos.getY(), handPos.getZ(), box);

				if (entity != null) {
					controller.setSeeker(entity);

					BlockPos entityPos = BlockPos.ofFloored(getBodyPos(entity, 0.5));
					int delay = (int) Vibrations.VibrationListener.getTravelDelay(BlockPos.ofFloored(handPos), entityPos);

					// Spawn vibration particle
					ParticleEffect particle = new VibrationParticleEffect(new EntityPositionSource(entity, entity.getHeight() * 0.5f), delay);
					serverWorld.spawnParticles(particle, handPos.getX(), handPos.getY(), handPos.getZ(), 1, 0, 0, 0, 0);

					user.setCurrentHand(hand);
					return TypedActionResult.consume(stack);
				}
			}
		}

		return TypedActionResult.pass(stack);
	}

	@Override
	public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
		if (!world.isClient() && user instanceof ServerPlayerEntity player) {
			SeekerController controller = SeekerController.forPlayer(player);
			controller.setSeeker(null);
		}
	}

	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BLOCK;
	}

	@Override
	public int getMaxUseTime(ItemStack stack) {
		return Integer.MAX_VALUE;
	}

	public static Vec3d getBodyPos(Entity entity, double heightScale) {
		return entity.getPos().add(0, entity.getHeight() * heightScale, 0);
	}

	public static Vec3d getHandPos(ServerPlayerEntity player, ItemStack stack) {
		Vec3d handPosOffset = player.getHandPosOffset(stack.getItem());
		return getBodyPos(player, 0.5).add(handPosOffset);
	}
}
