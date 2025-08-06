
package tfar.balancedenchanting;

import net.minecraftforge.common.ForgeConfigSpec;

public class BEConfig {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.DoubleValue ENCHANT_COST_MULTIPLIER;
    public static final ForgeConfigSpec.DoubleValue ANVIL_COST_MULTIPLIER;
    public static final ForgeConfigSpec.IntValue MAX_ANVIL_RAW_XP; // 0 = no cap
    public static final ForgeConfigSpec.BooleanValue CONVERT_LEVELS_TO_RAW_XP;

    static {
        ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();
        ENCHANT_COST_MULTIPLIER = b.comment("Multiplier on raw XP cost for enchanting table selections")
                .defineInRange("enchantCostMultiplier", 1.0, 0.0, 100.0);
        ANVIL_COST_MULTIPLIER = b.comment("Multiplier on raw XP cost for anvil operations")
                .defineInRange("anvilCostMultiplier", 1.0, 0.0, 100.0);
        MAX_ANVIL_RAW_XP = b.comment("Maximum raw XP cost for a single anvil operation (0 = no cap)")
                .defineInRange("maxAnvilRawXpCost", 0, 0, Integer.MAX_VALUE);
        CONVERT_LEVELS_TO_RAW_XP = b.comment("If true, converts level-based costs to raw XP so costs are level-agnostic")
                .define("convertLevelsToRawXp", true);
        SPEC = b.build();
    }
}
