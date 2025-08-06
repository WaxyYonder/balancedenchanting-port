
package tfar.balancedenchanting.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.EnchantmentMenu;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.balancedenchanting.BEConfig;
import tfar.balancedenchanting.ContextFlags;

@Mixin(EnchantmentMenu.class)
public abstract class EnchantmentMenuMixin {

    // The vanilla menu stores the 3 displayed costs in an int array.
    @Shadow @Final private int[] costs;

    @Inject(method = "clickMenuButton", at = @At("HEAD"))
    private void balancedEnchanting$markEnchantCost(Player player, int id, CallbackInfoReturnable<Boolean> cir) {
        if (!BEConfig.CONVERT_LEVELS_TO_RAW_XP.get()) return;
        if (id >= 0 && id < this.costs.length) {
            int levelCost = this.costs[id];
            if (levelCost > 0) {
                // Mark this thread so the upcoming giveExperienceLevels(-levelCost) is intercepted.
                ContextFlags.setEnchantCost(levelCost);
            }
        }
    }
}
