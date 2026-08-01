package name.modid;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.Direction;

import java.util.List;

public class WFCTileSet {
    public static final Codec<WFCTileSet> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    WFCTile.CODEC.listOf().fieldOf("tiles").forGetter(WFCTileSet::getTiles)
            ).apply(instance, WFCTileSet::new)
    );

    private final List<WFCTile> tiles;

    public WFCTileSet(
            List<WFCTile> tiles
    )
    {
        this.tiles = tiles;
    }

    public List<WFCTile>
    getTiles()
    {
        return tiles;
    }

    public WFCTile
    getTile(
            int index)
    {
        return tiles.get(index);
    }

    public List<WFCTile>
    getPotentialConnections(
            Direction direction,
            WFCTile neighbour)
    {
        Direction opposite        = direction.getOpposite();
        WFCSocket neighbourSocket = neighbour.getSocket(opposite);

        return tiles.stream()
                .filter(tile -> tile.getSocket(direction).connectsTo(neighbour.getStructure()) || neighbourSocket.connectsTo(tile.getStructure()))
                .toList();
    }
}
