package name.modid;

import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SefDim implements ModInitializer
{
	public static final String MOD_ID = "sef-dim";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void
	onInitialize()
	{
		LOGGER.info("Initializing...");

		Registry.register(
				Registries.CHUNK_GENERATOR,
				id("random_tile_chunk_generator"),
				RandomTileChunkGenerator.CODEC);
	}

	public static
	Identifier id(String path)
	{
		return new Identifier(MOD_ID, path);
	}
}
