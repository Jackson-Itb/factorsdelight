package com.factorsdelight.registry;

import com.factorsdelight.FactorsDelight;
import com.factorsdelight.item.FactorCounterItem;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FactorsDelight.MOD_ID);

    public static final DeferredItem<BlockItem> FACTOR_COUNTER = ITEMS.register(
            "factor_counter",
            () -> new FactorCounterItem(ModBlocks.FACTOR_COUNTER.get(), new Item.Properties())
    );

    private ModItems() {
    }
}
