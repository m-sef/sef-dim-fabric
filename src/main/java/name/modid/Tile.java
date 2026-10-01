package name.modid;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Weight;
import net.minecraft.util.collection.Weighted;

public class Tile implements Weighted {
    public static final Codec<Tile> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Identifier.CODEC.fieldOf("structure").forGetter(Tile::getStructure),
                    Codec.INT.optionalFieldOf("weight", 1).forGetter(Tile::getWeightValue),
                    Codec.BOOL.optionalFieldOf("can_rotate", false).forGetter(Tile::canRotate)
            ).apply(instance, Tile::new)
    );

    private final Identifier structureIdentifier;
    private final int       weight;
    private final boolean   can_rotate;

    public
    Tile(
            Identifier structureIdentifier,
            int        weight,
            boolean    can_rotate)
    {
        this.structureIdentifier = structureIdentifier;
        this.weight              = weight;
        this.can_rotate          = can_rotate;
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

    public boolean
    canRotate()
    {
        return can_rotate;
    }
}
