package com.factorsdelight;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.factorsdelight.registry.ModBlocks;
import com.factorsdelight.registry.ModCreativeTabs;
import com.factorsdelight.registry.ModItems;
import com.factorsdelight.villager.ModTrades;
import com.factorsdelight.villager.ModVillagers;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;

@Mod(FactorsDelight.MOD_ID)
public class FactorsDelight {
    public static final String MOD_ID = "factorsdelight";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FactorsDelight(IEventBus modBus, ModContainer container) {
        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModCreativeTabs.TABS.register(modBus);
        ModVillagers.POI_TYPES.register(modBus);
        ModVillagers.PROFESSIONS.register(modBus);
        container.registerConfig(ModConfig.Type.COMMON, FactorConfig.SPEC);
        NeoForge.EVENT_BUS.addListener(ModTrades::onVillagerTrades);
        LOGGER.info("Factor's Delight ready. Factors buy packed crates for emeralds.");
    }
}
