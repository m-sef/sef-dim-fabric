package name.modid;

public class WeightedTileSet extends TileSet {
    public static final Codec<WeightedTileSet> CODEC;

    public
    WeightedTileSet(
            List<WeightedTile> weightedTileSet)
    {
        super(weightedTileSet);
    }
}
