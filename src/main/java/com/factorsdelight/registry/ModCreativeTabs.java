package com.factorsdelight.registry;

import com.factorsdelight.FactorsDelight;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FactorsDelight.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN = TABS.register("main", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable("itemGroup.factorsdelight"))
            .icon(() -> new ItemStack(ModItems.FACTOR_COUNTER.get()))
            .displayItems((parameters, output) -> output.accept(ModItems.FACTOR_COUNTER.get()))
            .build());

    private ModCreativeTabs() {
    }
}
