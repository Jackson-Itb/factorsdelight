package com.provisionersdelight.villager;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.provisionersdelight.ProvisionersDelight;
import com.provisionersdelight.registry.ModBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, ProvisionersDelight.MOD_ID);
    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(Registries.VILLAGER_PROFESSION, ProvisionersDelight.MOD_ID);

    public static final DeferredHolder<PoiType, PoiType> PROVISIONER_POI = POI_TYPES.register("provisioner_poi", () -> new PoiType(
            Set.copyOf(ModBlocks.PROVISION_COUNTER.get().getStateDefinition().getPossibleStates()),
            1,
            3
    ));

    public static final DeferredHolder<VillagerProfession, VillagerProfession> PROVISIONER = PROFESSIONS.register("provisioner", () -> new VillagerProfession(
            "provisioner",
            holder -> holder.is(PROVISIONER_POI.getKey()),
            holder -> holder.is(PROVISIONER_POI.getKey()),
            ImmutableSet.<Item>of(),
            ImmutableSet.<Block>of(),
            SoundEvents.VILLAGER_WORK_CARTOGRAPHER
    ));

    private ModVillagers() {
    }
}
