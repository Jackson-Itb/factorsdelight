package com.factorsdelight.villager;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.factorsdelight.FactorsDelight;
import com.factorsdelight.registry.ModBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, FactorsDelight.MOD_ID);
    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(Registries.VILLAGER_PROFESSION, FactorsDelight.MOD_ID);

    public static final DeferredHolder<PoiType, PoiType> FACTOR_POI = POI_TYPES.register("factor_poi", () -> new PoiType(
            Set.copyOf(ModBlocks.FACTOR_COUNTER.get().getStateDefinition().getPossibleStates()),
            1,
            3
    ));

    public static final DeferredHolder<VillagerProfession, VillagerProfession> FACTOR = PROFESSIONS.register("factor", () -> new VillagerProfession(
            "factor",
            holder -> holder.is(FACTOR_POI.getKey()),
            holder -> holder.is(FACTOR_POI.getKey()),
            ImmutableSet.<Item>of(),
            ImmutableSet.<Block>of(),
            SoundEvents.VILLAGER_WORK_CARTOGRAPHER
    ));

    private ModVillagers() {
    }
}
