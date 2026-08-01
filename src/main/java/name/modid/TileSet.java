package name.modid;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public class TileSet {
    public static final Codec<TileSet> CODEC = RecordCodecBuilder.create(
        instance -> instance.group(
            Tile.CODEC.listOf().fieldOf("tiles").forGetter(TileSet::getTiles)
        ).apply(instance, TileSet::new)
    );

    private final List<Tile> tiles;

    public
    TileSet(
            List<Tile> tiles
    )
    {
        this.tiles = tiles;
    }

    public List<Tile>
    getTiles()
    {
        return tiles;
    }

    public Tile
    getTile(
            int index)
    {
        return tiles.get(index);
    }
}
