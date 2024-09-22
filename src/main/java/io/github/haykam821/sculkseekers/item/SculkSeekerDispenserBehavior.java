package io.github.haykam821.sculkseekers.item;

import io.github.haykam821.sculkseekers.entity.SculkSeekerEntity;
import io.github.haykam821.sculkseekers.entity.SculkSeekersEntityTypes;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class SculkSeekerDispenserBehavior extends ItemDispenserBehavior {
	private final ItemDispenserBehavior itemDispenser = new ItemDispenserBehavior();

	@Override
	public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
		World world = pointer.getWorld();

		Direction facing = pointer.getBlockState().get(DispenserBlock.FACING);
		BlockPos pos = pointer.getPos().offset(facing);

		if (!world.getBlockState(pos).isAir()) {
			return this.itemDispenser.dispense(pointer, stack);
		}

		SculkSeekerEntity entity = new SculkSeekerEntity(SculkSeekersEntityTypes.SCULK_SEEKER, world);
		entity.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

		float yaw = facing.asRotation();

		entity.setYaw(yaw);
		entity.setHeadYaw(yaw);
		entity.setBodyYaw(yaw);

		if (stack.hasCustomName()) {
			entity.setCustomName(stack.getName());
		}

		world.spawnEntity(entity);
		stack.decrement(1);

		return stack;
	}
}
