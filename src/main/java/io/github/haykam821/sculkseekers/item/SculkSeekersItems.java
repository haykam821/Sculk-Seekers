package io.github.haykam821.sculkseekers.item;

import io.github.haykam821.sculkseekers.SculkSeekers;
import io.github.haykam821.sculkseekers.entity.SculkSeekersEntityTypes;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class SculkSeekersItems {
	public static final Item SCULK_SEEKER = new SculkSeekerItem(new Item.Settings());

	private static final Identifier SEEKER_CONTROLLER_ID = SculkSeekers.identifier("seeker_controller");
	public static final Item SEEKER_CONTROLLER = new SeekerControllerItem(new Item.Settings());

	private SculkSeekersItems() {
		return;
	}

	public static void register() {
		Registry.register(Registries.ITEM, SculkSeekersEntityTypes.SCULK_SEEKER_ID, SCULK_SEEKER);
		Registry.register(Registries.ITEM, SEEKER_CONTROLLER_ID, SEEKER_CONTROLLER);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> {
			entries.addAfter(Items.SCULK_SHRIEKER, SCULK_SEEKER, SEEKER_CONTROLLER);
		});

		DispenserBlock.registerBehavior(SCULK_SEEKER, new SculkSeekerDispenserBehavior());
	}
}
