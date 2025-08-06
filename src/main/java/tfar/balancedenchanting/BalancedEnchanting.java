
package tfar.balancedenchanting;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.ModLoadingContext;

@Mod(BalancedEnchanting.MODID)
public class BalancedEnchanting {
    public static final String MODID = "balancedenchanting";

    public BalancedEnchanting() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, BEConfig.SPEC);
    }
}
