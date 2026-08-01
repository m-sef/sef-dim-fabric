package name.modid;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.collection.Weighting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.structure.Structure;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.Random;

public class RandomTileChunkGenerator extends ChunkGenerator {
    private final TileSet tileSet;

    public static final Codec<RandomTileChunkGenerator> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    BiomeSource.CODEC.fieldOf("biome_source").stable().forGetter(ChunkGenerator::getBiomeSource),
                    TileSet.CODEC.fieldOf("tile_set").stable().forGetter(RandomTileChunkGenerator::getTileSet)
            ).apply(instance, RandomTileChunkGenerator::new)
    );

    public
    RandomTileChunkGenerator(
            BiomeSource biomeSource,
            TileSet tileSet)
    {
        super(biomeSource);
        this.tileSet = tileSet;
    }

    @Override
    public void
    addStructureReferences(
            StructureWorldAccess world,
            StructureAccessor structureAccessor,
            Chunk chunk)
    {

    }

    @Override
    public void
    buildSurface(
            ChunkRegion region,
            StructureAccessor structures,
            NoiseConfig noiseConfig,
            Chunk chunk)
    {

    }

    @Override
    public void
    carve(
            ChunkRegion chunkRegion,
            long seed,
            NoiseConfig noiseConfig,
            BiomeAccess biomeAccess,
            StructureAccessor structureAccessor,
            Chunk chunk,
            GenerationStep.Carver carverStep)
    {

    }

    @Override
    public void
    generateFeatures(
            StructureWorldAccess world,
            Chunk chunk,
            StructureAccessor structureAccessor)
    {
        StructureTemplateManager structureTemplateManager = world.toServerWorld().getStructureTemplateManager();

        List<Tile> tiles = tileSet.getTiles();
        // TODO: Do not throw!!!
        Tile       tile  = Weighting.getRandom(world.getRandom(), tiles).orElseThrow();

        Identifier structureIdentifier = tile.getStructure();

        Optional<StructureTemplate> structureTemplate = structureTemplateManager.getTemplate(structureIdentifier);

        BlockPos chunkPos = chunk.getPos().getStartPos();

        structureTemplate.ifPresent(template -> {
            template.place(
                    world,
                    chunkPos,
                    chunkPos,
                    new StructurePlacementData(),
                    world.getRandom(),
                    Block.NOTIFY_ALL
            );
        });
    }

    /* @Override
    public BiomeSource
    getBiomeSource()
    {

    } */

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    /* @Override
    public Optional<RegistryKey<Codec<? extends ChunkGenerator>>>
    getCodecKey()
    {
        return Optional.empty();
    } */

    @Override
    public VerticalBlockSample
    getColumnSample(
            int x,
            int z,
            HeightLimitView world,
            NoiseConfig noiseConfig)
    {
        return new VerticalBlockSample(world.getBottomY(), new BlockState[0]);
    }

    @Override
    public void
    getDebugHudText(
            List<String> text,
            NoiseConfig noiseConfig,
            BlockPos pos)
    {

    }

    @Override
    public Pool<SpawnSettings.SpawnEntry>
    getEntitySpawnList(
            RegistryEntry<Biome> biome,
            StructureAccessor accessor,
            SpawnGroup group,
            BlockPos pos)
    {
        return Pool.empty();
    }

    @Override
    public int
    getHeight(
            int x,
            int z,
            Heightmap.Type heightmap,
            HeightLimitView world,
            NoiseConfig noiseConfig)
    {
        return 0;
    }

    @Override
    public int
    getHeightInGround(
            int x,
            int z,
            Heightmap.Type heightmap,
            HeightLimitView world,
            NoiseConfig noiseConfig)
    {
        return 0;
    }

    @Override
    public int
    getHeightOnGround(
            int x,
            int z,
            Heightmap.Type heightmap,
            HeightLimitView world,
            NoiseConfig noiseConfig)
    {
        return 0;
    }

    @Override
    public int getMinimumY()
    {
        return 0;
    }

    @Override
    public int
    getSeaLevel()
    {
        return 0;
    }

    @Override
    public int
    getSpawnHeight(
            HeightLimitView world)
    {
        return 0;
    }

    public TileSet
    getTileSet()
    {
        return tileSet;
    }

    @Override
    public int
    getWorldHeight()
    {
        return 0;
    }

    @Override
    public CompletableFuture<Chunk>
    populateBiomes(
            Executor executor,
            NoiseConfig noiseConfig,
            Blender blender,
            StructureAccessor structureAccessor,
            Chunk chunk)
    {
        return CompletableFuture.completedFuture(chunk);
    }

    @Override
    public void
    populateEntities(
            ChunkRegion region)
    {

    }

    @Override
    public CompletableFuture<Chunk>
    populateNoise(
            Executor executor,
            Blender blender,
            NoiseConfig noiseConfig,
            StructureAccessor structureAccessor,
            Chunk chunk)
    {
        return CompletableFuture.completedFuture(chunk);
    }
}
