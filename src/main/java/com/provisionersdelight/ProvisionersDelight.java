package com.provisionersdelight;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.provisionersdelight.registry.ModBlocks;
import com.provisionersdelight.registry.ModCreativeTabs;
import com.provisionersdelight.registry.ModItems;
import com.provisionersdelight.villager.ModTrades;
import com.provisionersdelight.villager.ModVillagers;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;

@Mod(ProvisionersDelight.MOD_ID)
public class ProvisionersDelight {
    public static final String MOD_ID = "provisionersdelight";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ProvisionersDelight(IEventBus modBus, ModContainer container) {
        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModCreativeTabs.TABS.register(modBus);
        ModVillagers.POI_TYPES.register(modBus);
        ModVillagers.PROFESSIONS.register(modBus);
        container.registerConfig(ModConfig.Type.COMMON, ProvisionerConfig.SPEC);
        NeoForge.EVENT_BUS.addListener(ModTrades::onVillagerTrades);
        LOGGER.info("Provisioner's Delight ready. Provisioners buy packed crates for emeralds.");
    }
}
