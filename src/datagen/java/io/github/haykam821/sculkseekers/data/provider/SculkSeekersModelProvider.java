package io.github.haykam821.sculkseekers.data.provider;

import io.github.haykam821.sculkseekers.item.SculkSeekersItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class SculkSeekersModelProvider extends FabricModelProvider {
	public SculkSeekersModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator generator) {
		return;
	}

	@Override
	public void generateItemModels(ItemModelGenerator generator) {
		generator.register(SculkSeekersItems.SCULK_SEEKER, Models.GENERATED);
		generator.register(SculkSeekersItems.SEEKER_CONTROLLER, Models.GENERATED);
	}
}
