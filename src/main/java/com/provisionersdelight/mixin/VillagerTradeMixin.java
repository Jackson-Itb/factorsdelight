package com.provisionersdelight.mixin;

import com.provisionersdelight.villager.ModVillagers;

import net.minecraft.world.entity.npc.Villager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/**
 * Vanilla unlocks two random offers per level. A wholesale buyer has to show every crate they purchase,
 * or a factory that packs cabbage might roll a villager who only wants fish.
 */
@Mixin(Villager.class)
public abstract class VillagerTradeMixin {
    @ModifyConstant(method = "updateTrades", constant = @Constant(intValue = 2))
    private int provisionersdelight$unlockEveryOffer(int original) {
        Villager villager = (Villager) (Object) this;
        if (villager.getVillagerData().getProfession() == ModVillagers.PROVISIONER.get()) {
            return 64;
        }
        return original;
    }
}
