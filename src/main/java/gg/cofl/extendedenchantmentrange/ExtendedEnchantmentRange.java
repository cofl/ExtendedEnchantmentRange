package gg.cofl.extendedenchantmentrange;

import com.mojang.logging.LogUtils;
import gg.cofl.extendedenchantmentrange.mixin.EnchantingTableBlockAccessor;
import net.minecraft.core.BlockPos;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(ExtendedEnchantmentRange.MODID)
public class ExtendedEnchantmentRange {
    public static final String MODID = "extendedenchantmentrange";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ExtendedEnchantmentRange(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event){
        setOffsets(
                Config.MINIMUM_HORIZONTAL_DISTANCE.getAsInt(),
                Config.MINIMUM_VERTICAL_DISTANCE.getAsInt(),
                Config.MAXIMUM_HORIZONTAL_DISTANCE.getAsInt(),
                Config.MAXIMUM_ABOVE_DISTANCE.getAsInt(),
                Config.MAXIMUM_BELOW_DISTANCE.getAsInt()
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
