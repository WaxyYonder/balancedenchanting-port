
package tfar.balancedenchanting.mixin;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.balancedenchanting.BEConfig;
import tfar.balancedenchanting.ContextFlags;
import tfar.balancedenchanting.XpMath;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Inject(method = "giveExperienceLevels", at = @At("HEAD"), cancellable = true)
    private void balancedEnchanting$convertLevelCosts(int levels, CallbackInfo ci) {
        if (!BEConfig.CONVERT_LEVELS_TO_RAW_XP.get()) return;
        if (levels >= 0) return; // we only convert deductions

        Player self = (Player)(Object)this;

        Integer enchantCost = ContextFlags.getEnchantCost();
        if (enchantCost != null) {
            ContextFlags.clearEnchantCost();
            int raw = (int)Math.round(XpMath.rawForLevelsFromZero(enchantCost) * BEConfig.ENCHANT_COST_MULTIPLIER.get());
            if (raw > 0) self.giveExperiencePoints(-raw);
            ci.cancel();
            return;
        }

        Integer anvilCost = ContextFlags.getAnvilCost();
        if (anvilCost != null) {
            ContextFlags.clearAnvilCost();
            int raw = (int)Math.round(XpMath.rawForLevelsFromZero(anvilCost) * BEConfig.ANVIL_COST_MULTIPLIER.get());
            int cap = BEConfig.MAX_ANVIL_RAW_XP.get();
            if (cap > 0 && raw > cap) raw = cap;
            if (raw > 0) self.giveExperiencePoints(-raw);
            ci.cancel();
        }
    }
}
