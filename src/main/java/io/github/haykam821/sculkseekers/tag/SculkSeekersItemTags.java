package io.github.haykam821.sculkseekers.tag;

import io.github.haykam821.sculkseekers.SculkSeekers;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class SculkSeekersItemTags {
	public static final TagKey<Item> USING_PREVENTS_MOVEMENT = of("using_prevents_movement");

	private SculkSeekersItemTags() {
		return;
	}

	private static TagKey<Item> of(String path) {
		Identifier id = SculkSeekers.identifier(path);
		return TagKey.of(RegistryKeys.ITEM, id);
	}
}
