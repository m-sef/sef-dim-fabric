package name.modid;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Weight;
import net.minecraft.util.collection.Weighted;
import net.minecraft.util.math.Direction;

import java.util.EnumMap;

public class WFCTile implements Weighted {
    public static final Codec<WFCTile> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Identifier.CODEC.fieldOf("structure").forGetter(WFCTile::getStructure),
                    Codec.INT.optionalFieldOf("weight", 1).forGetter(WFCTile::getWeightValue),
                    WFCSocket.CODEC.optionalFieldOf("north_socket", WFCSocket.AIR).forGetter(WFCTile::getNorthSocket),
                    WFCSocket.CODEC.optionalFieldOf("east_socket",  WFCSocket.AIR).forGetter(WFCTile::getEastSocket),
                    WFCSocket.CODEC.optionalFieldOf("south_socket", WFCSocket.AIR).forGetter(WFCTile::getSouthSocket),
                    WFCSocket.CODEC.optionalFieldOf("west_socket",  WFCSocket.AIR).forGetter(WFCTile::getWestSocket),
                    WFCSocket.CODEC.optionalFieldOf("up_socket",    WFCSocket.AIR).forGetter(WFCTile::getUpSocket),
                    WFCSocket.CODEC.optionalFieldOf("down_socket",  WFCSocket.AIR).forGetter(WFCTile::getDownSocket)
            ).apply(instance, WFCTile::new)
    );

    private final Identifier structureIdentifier;
    private final int weight;
    private final EnumMap<Direction, WFCSocket> sockets;

    public WFCTile(
            Identifier structureIdentifier,
            int weight,
            WFCSocket northSocket,
            WFCSocket eastSocket,
            WFCSocket southSocket,
            WFCSocket westSocket,
            WFCSocket upSocket,
            WFCSocket downSocket)
    {
        this.structureIdentifier = structureIdentifier;
        this.weight              = weight;

        this.sockets = new EnumMap<Direction, WFCSocket>(Direction.class);
        this.sockets.put(Direction.NORTH, northSocket);
        this.sockets.put(Direction.EAST,  eastSocket);
        this.sockets.put(Direction.SOUTH, southSocket);
        this.sockets.put(Direction.WEST,  westSocket);
        this.sockets.put(Direction.UP,    upSocket);
        this.sockets.put(Direction.DOWN,  downSocket);
    }

    public Identifier
    getStructure()
    {
        return structureIdentifier;
    }

    public Weight
    getWeight()
    {
        return Weight.of(weight);
    }

    public int
    getWeightValue()
    {
        return weight;
    }

    public WFCSocket
    getSocket(
            Direction direction)
    {
        return sockets.get(direction);
    }

    public WFCSocket
    getNorthSocket()
    {
        return sockets.get(Direction.NORTH);
    }

    public WFCSocket
    getEastSocket()
    {
        return sockets.get(Direction.EAST);
    }

    public WFCSocket
    getSouthSocket()
    {
        return sockets.get(Direction.SOUTH);
    }

    public WFCSocket
    getWestSocket()
    {
        return sockets.get(Direction.WEST);
    }

    public WFCSocket
    getUpSocket()
    {
        return sockets.get(Direction.UP);
    }

    public WFCSocket
    getDownSocket()
    {
        return sockets.get(Direction.DOWN);
    }
}
