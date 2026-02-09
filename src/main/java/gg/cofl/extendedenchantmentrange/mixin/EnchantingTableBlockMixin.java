package gg.cofl.extendedenchantmentrange.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import gg.cofl.extendedenchantmentrange.FoundStateHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EnchantingTableBlock;
import org.spongepowered.asm.mixin.*;

@Mixin(EnchantingTableBlock.class)
public abstract class EnchantingTableBlockMixin {
    @WrapMethod(method = "isValidBookShelf")
    private static boolean isValidBookshelf(Level level, BlockPos enchantingTablePos, BlockPos bookshelfPos, Operation<Boolean> _original) {
        final var blockPos = enchantingTablePos.offset(bookshelfPos);
        final var block = level.getBlockState(blockPos);
        if (block.getEnchantPowerBonus(level, blockPos) == 0.0F)
            return false;

        var found = new FoundStateHolder();
        final var bookshelfDistance = blockPos.distManhattan(enchantingTablePos);
        BlockPos.breadthFirstTraversal(blockPos,
                bookshelfDistance,
                bookshelfDistance * bookshelfDistance * bookshelfDistance,
                (current, enqueue) -> {
                    final var offset = current.subtract(enchantingTablePos);
                    final int x = offset.getX(), y = offset.getY(), z = offset.getZ();
                    if (x != 0) enqueue.accept(current.offset(x < 0 ? 1 : -1, 0, 0));
                    if (y != 0) enqueue.accept(current.offset(0, y < 0 ? 1 : -1, 0));
                    if (z != 0) enqueue.accept(current.offset(0, 0, z < 0 ? 1 : -1));
                },
                (pos) -> {
                    final var state = level.getBlockState(pos);
                    if (state.is(Blocks.ENCHANTING_TABLE)) {
                        found.setFound(true);
                        return false;
                    }
                    return state.getEnchantPowerBonus(level, pos) != 0.0F || level.getBlockState(pos).is(BlockTags.ENCHANTMENT_POWER_TRANSMITTER);
                });
        return found.isFound();
    }
}
