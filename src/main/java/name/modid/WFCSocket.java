package name.modid;

import com.mojang.serialization.Codec;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Set;

public class WFCSocket {
    public static final Codec<WFCSocket> CODEC = Identifier.CODEC.listOf().xmap(
            list -> new WFCSocket(Set.copyOf(list)),
            socket -> List.copyOf(socket.getConnections())
    );

    public static final Identifier AIR_STRUCTURE_ID = SefDim.id("tiles/air");
    public static final WFCSocket  AIR              = new WFCSocket(Set.of(AIR_STRUCTURE_ID));

    private final Set<Identifier> connections;

    public WFCSocket(
            Set<Identifier> connections)
    {
        this.connections = connections;
    }

    public Set<Identifier>
    getConnections()
    {
        return connections;
    }

    public boolean
    connectsTo(
            Identifier structureIdentifier)
    {
        return connections.contains(structureIdentifier);
    }
}
