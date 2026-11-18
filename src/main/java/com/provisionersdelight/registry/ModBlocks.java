package com.provisionersdelight.registry;

import com.provisionersdelight.ProvisionersDelight;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ProvisionersDelight.MOD_ID);

    public static final DeferredBlock<Block> PROVISION_COUNTER = BLOCKS.registerSimpleBlock(
            "provision_counter",
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .sound(SoundType.WOOD)
                    .strength(2.0F, 3.0F)
    );

    private ModBlocks() {
    }
}
