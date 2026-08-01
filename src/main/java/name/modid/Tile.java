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
                    Codec.INT.optionalFieldOf("weight", 1).forGetter(Tile::getWeightValue)
            ).apply(instance, Tile::new)
    );

    private final Identifier structure;
    private final int weight;

    public
    Tile(
            Identifier structure,
            int weight)
    {
        this.structure = structure;
        this.weight    = weight;
    }

    public Identifier
    getStructure()
    {
        return structure;
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
}
