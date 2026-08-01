package name.modid;

import com.mojang.serialization.Codec;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Weight;
import net.minecraft.util.collection.Weighted;

public class WeightedTile extends Tile implements Weighted {
    public static final Codec<WeightedTile> CODEC;

    public int weight;

    public
    WeightedTile(
            Identifier structureIdentifier,
            int weight
    )
    {
        super(structureIdentifier, weight);
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
