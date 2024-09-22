package io.github.haykam821.sculkseekers.item;

import io.github.haykam821.sculkseekers.entity.SculkSeekerEntity;
import io.github.haykam821.sculkseekers.entity.SculkSeekersEntityTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class SculkSeekerItem extends Item {
	public SculkSeekerItem(Item.Settings settings) {
		super(settings);
	}

	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos().offset(context.getSide());

		ItemStack stack = context.getStack();

		SculkSeekerEntity entity = new SculkSeekerEntity(SculkSeekersEntityTypes.SCULK_SEEKER, world);
		entity.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

		float yaw = context.getPlayerYaw();

		entity.setYaw(yaw);
		entity.setHeadYaw(yaw);
		entity.setBodyYaw(yaw);

		if (stack.hasCustomName()) {
			entity.setCustomName(stack.getName());
		}

		world.spawnEntity(entity);
		world.emitGameEvent(GameEvent.ENTITY_PLACE, pos, GameEvent.Emitter.of(context.getPlayer(), world.getBlockState(pos.down())));

		stack.decrement(1);
		return ActionResult.success(world.isClient());
	}
}
