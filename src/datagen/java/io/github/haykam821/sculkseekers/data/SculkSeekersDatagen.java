package io.github.haykam821.sculkseekers.data;

import io.github.haykam821.sculkseekers.data.provider.SculkSeekersEntityTypeTagProvider;
import io.github.haykam821.sculkseekers.data.provider.SculkSeekersItemTagProvider;
import io.github.haykam821.sculkseekers.data.provider.SculkSeekersModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class SculkSeekersDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
		FabricDataGenerator.Pack pack = dataGenerator.createPack();

		pack.addProvider(SculkSeekersEntityTypeTagProvider::new);
		pack.addProvider(SculkSeekersItemTagProvider::new);
		pack.addProvider(SculkSeekersModelProvider::new);
	}
}
