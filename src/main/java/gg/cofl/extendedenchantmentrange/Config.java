package gg.cofl.extendedenchantmentrange;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue MINIMUM_HORIZONTAL_DISTANCE = BUILDER
            .comment("Minimum horizontal distance a bookshelf must be from the enchanting table (except if outside the minimum vertical distance)")
            .defineInRange("minimumHorizontalDistance", 1, 0, 16);

    public static final ModConfigSpec.IntValue MINIMUM_VERTICAL_DISTANCE = BUILDER
            .comment("Minimum vertical distance a bookshelf must be from the enchanting table (except if outside the minimum horizontal distance)")
            .defineInRange("minimumVerticalDistance", 1, 0, 16);

    public static final ModConfigSpec.IntValue MAXIMUM_HORIZONTAL_DISTANCE = BUILDER
            .comment("Maximum horizontal distance a bookshelf may be from an enchanting table.")
            .defineInRange("maximumHorizontalDistance", 4, 0, 16);

    public static final ModConfigSpec.IntValue MAXIMUM_ABOVE_DISTANCE = BUILDER
            .comment("Maximum distance above an enchanting table a bookshelf may be.")
            .defineInRange("maximumAboveDistance", 4, 0, 16);

    public static final ModConfigSpec.IntValue MAXIMUM_BELOW_DISTANCE = BUILDER
            .comment("Maximum distance below an enchanting table a bookshelf may be.")
            .defineInRange("maximumBelowDistance", 2, 0, 16);

    public static final ModConfigSpec SPEC = BUILDER.build();
}
