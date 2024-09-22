package io.github.haykam821.sculkseekers.sound;

import io.github.haykam821.sculkseekers.SculkSeekers;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public final class SculkSeekersSoundEvents {
	public static final SoundEvent ENTITY_SCULK_SEEKER_CLICKING = register("entity.sculk_seeker.clicking");
	public static final SoundEvent ENTITY_SCULK_SEEKER_CLICKING_STOP = register("entity.sculk_seeker.clicking_stop");
	public static final SoundEvent ENTITY_SCULK_SEEKER_DEATH = register("entity.sculk_seeker.death");
	public static final SoundEvent ENTITY_SCULK_SEEKER_HURT = register("entity.sculk_seeker.hurt");

	private SculkSeekersSoundEvents() {
		return;
	}

	public static void initialize() {
		return;
	}

	private static SoundEvent register(String path) {
		Identifier id = SculkSeekers.identifier(path);
		SoundEvent sound = SoundEvent.of(id);

		Registry.register(Registries.SOUND_EVENT, id, sound);
		return sound;
	}
}
