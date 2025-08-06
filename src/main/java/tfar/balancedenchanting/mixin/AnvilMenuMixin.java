
package tfar.balancedenchanting.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;
import tfar.balancedenchanting.BEConfig;
import tfar.balancedenchanting.ContextFlags;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin {

    @Shadow public abstract int getCost();

    // When the output is taken, vanilla will charge levels equal to getCost().
    @Inject(method = "onTake", at = @At("HEAD"))
    private void balancedEnchanting$markAnvilCost(Player player, net.minecraft.world.item.ItemStack stack, CallbackInfo ci) {
        if (!BEConfig.CONVERT_LEVELS_TO_RAW_XP.get()) return;
        int levelCost = this.getCost();
        if (levelCost > 0) {
            ContextFlags.setAnvilCost(levelCost);
        }
    }
}
