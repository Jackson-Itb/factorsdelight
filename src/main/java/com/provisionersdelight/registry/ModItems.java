package com.provisionersdelight.registry;

import com.provisionersdelight.ProvisionersDelight;
import com.provisionersdelight.item.ProvisionCounterItem;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ProvisionersDelight.MOD_ID);

    public static final DeferredItem<BlockItem> PROVISION_COUNTER = ITEMS.register(
            "provision_counter",
            () -> new ProvisionCounterItem(ModBlocks.PROVISION_COUNTER.get(), new Item.Properties())
    );

    private ModItems() {
    }
}
