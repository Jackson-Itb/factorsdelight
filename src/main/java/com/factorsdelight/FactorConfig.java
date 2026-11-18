package com.factorsdelight;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class FactorConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.DoubleValue EMERALD_MULTIPLIER = BUILDER
            .comment(
                    "Scales emeralds the Factor pays for crates, and emeralds they charge on sell trades.",
                    "1.0 is the built-in rate: one staple crate pays one emerald.",
                    "Already learned offers stay as they are until that villager rolls trades again."
            )
            .defineInRange("emeraldMultiplier", 1.0D, 0.25D, 8.0D);

    public static final ModConfigSpec.DoubleValue CRATE_MULTIPLIER = BUILDER
            .comment(
                    "Scales how many crates or bags a buy trade demands.",
                    "1.0 keeps the built-in counts. 2.0 asks for twice as many crates per emerald payout.",
                    "The count never drops below 1 or rises above 64."
            )
            .defineInRange("crateMultiplier", 1.0D, 0.25D, 8.0D);

    public static final ModConfigSpec.BooleanValue SELL_TRADES = BUILDER
            .comment("When true, experienced Factors also sell rope and a name tag.")
            .define("enableSellTrades", true);

    static final ModConfigSpec SPEC = BUILDER.build();

    private FactorConfig() {
    }

    public static int scaleEmeralds(int base) {
        return clamp(base * EMERALD_MULTIPLIER.get());
    }

    public static int scaleCrates(int base) {
        return clamp(base * CRATE_MULTIPLIER.get());
    }

    private static int clamp(double scaled) {
        int value = (int) Math.round(scaled);
        if (value < 1) {
            return 1;
        }
        return Math.min(value, 64);
    }
}
