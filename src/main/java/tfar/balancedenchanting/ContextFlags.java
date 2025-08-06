
package tfar.balancedenchanting;

public class ContextFlags {
    /** Level cost from enchanting table option (if any) for the current thread. */
    private static final ThreadLocal<Integer> ENCHANT_LEVEL_COST = new ThreadLocal<>();
    /** Level cost from anvil (if any) for the current thread. */
    private static final ThreadLocal<Integer> ANVIL_LEVEL_COST = new ThreadLocal<>();

    public static void setEnchantCost(Integer cost) { ENCHANT_LEVEL_COST.set(cost); }
    public static Integer getEnchantCost() { return ENCHANT_LEVEL_COST.get(); }
    public static void clearEnchantCost() { ENCHANT_LEVEL_COST.remove(); }

    public static void setAnvilCost(Integer cost) { ANVIL_LEVEL_COST.set(cost); }
    public static Integer getAnvilCost() { return ANVIL_LEVEL_COST.get(); }
    public static void clearAnvilCost() { ANVIL_LEVEL_COST.remove(); }
}
