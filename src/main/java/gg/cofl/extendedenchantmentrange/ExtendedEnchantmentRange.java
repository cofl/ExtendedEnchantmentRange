package gg.cofl.extendedenchantmentrange;

import com.mojang.logging.LogUtils;
import gg.cofl.extendedenchantmentrange.mixin.EnchantingTableBlockAccessor;
import net.minecraft.core.BlockPos;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(ExtendedEnchantmentRange.MODID)
public class ExtendedEnchantmentRange {
    public static final String MODID = "extendedenchantmentrange";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ExtendedEnchantmentRange(FMLJavaModLoadingContext context) {
        var modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event){
        setOffsets(
                Config.MINIMUM_HORIZONTAL_DISTANCE.get(),
                Config.MINIMUM_VERTICAL_DISTANCE.get(),
                Config.MAXIMUM_HORIZONTAL_DISTANCE.get(),
                Config.MAXIMUM_ABOVE_DISTANCE.get(),
                Config.MAXIMUM_BELOW_DISTANCE.get()
        );
    }

    private void setOffsets(int minH, int minY, int maxH, int maxUp, int maxDown) {
        EnchantingTableBlockAccessor.setBookshelfOffsets(
                BlockPos.betweenClosedStream(-maxH, -maxDown, -maxH, maxH, maxUp, maxH)
                        .filter(pos -> Math.abs(pos.getY()) > minY || !(Math.abs(pos.getX()) < minH && Math.abs(pos.getZ()) < minH))
                        .map(BlockPos::immutable)
                        .toList());
    }
}
