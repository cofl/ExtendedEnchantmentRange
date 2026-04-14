package gg.cofl.extendedenchantmentrange.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EnchantmentTableBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(EnchantmentTableBlock.class)
public interface EnchantingTableBlockAccessor {
    @Accessor("BOOKSHELF_OFFSETS")
    static void setBookshelfOffsets(List<BlockPos> list){
        throw new AssertionError();
    }
}
