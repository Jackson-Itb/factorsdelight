package com.factorsdelight.villager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.factorsdelight.FactorConfig;
import com.factorsdelight.FactorsDelight;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

/**
 * Wholesale rates for a 9-item crate or bag.
 * <p>
 * A vanilla farmer pays 1 emerald for about 20 wheat or 22-26 root crops.
 * One staple crate is 9 crops and pays 1 emerald, roughly 2.5x to 3x that loose rate.
 * That is the bonus for packing a factory's output. It is not an emerald printer:
 * each offer has limited uses, and a villager only restocks twice a Minecraft day if they can work and sleep.
 * Sixteen staple sales a restock is 32 emeralds a day from one Factor.
 * <p>
 * Rarer goods pay more. Golden apples pay 16 because the crate holds 9 apples and 72 gold ingots,
 * which is a real crafting cost and a poor way to launder a gold farm (about 4.5 ingots per emerald).
 */
public final class ModTrades {
    private static final String EXPANDED_DELIGHT = "expandeddelight";
    private static final Set<String> WARNED_MISSING = ConcurrentHashMap.newKeySet();

    private ModTrades() {
    }

    public static void onVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() != ModVillagers.FACTOR.get()) {
            return;
        }
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        fill(trades, 1, ModTrades::novice);
        fill(trades, 2, ModTrades::apprentice);
        fill(trades, 3, ModTrades::journeyman);
        fill(trades, 4, ModTrades::expert);
        fill(trades, 5, ModTrades::master);
    }

    private static void fill(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades, int level, java.util.function.Consumer<List<VillagerTrades.ItemListing>> filler) {
        List<VillagerTrades.ItemListing> list = trades.get(level);
        if (list == null) {
            list = new ArrayList<>();
            trades.put(level, list);
        }
        filler.accept(list);
    }

    private static void novice(List<VillagerTrades.ItemListing> list) {
        // Farmer's Delight owns the vanilla crop crates whenever it is installed.
        // Crate Delight turns its own carrot, potato, and beetroot recipes off in that case.
        buy(list, "farmersdelight:carrot_crate", 1, 1, 16, 5, 0.05F, null);
        buy(list, "farmersdelight:potato_crate", 1, 1, 16, 5, 0.05F, null);
        buy(list, "farmersdelight:beetroot_crate", 1, 1, 16, 5, 0.05F, null);
        buy(list, "farmersdelight:cabbage_crate", 1, 1, 16, 5, 0.05F, null);
        buy(list, "farmersdelight:tomato_crate", 1, 1, 16, 5, 0.05F, null);
        buy(list, "farmersdelight:onion_crate", 1, 1, 16, 5, 0.05F, null);
        buy(list, "farmersdelight:rice_bag", 1, 1, 16, 5, 0.05F, null);
        buy(list, "farmersdelight:rice_bale", 1, 1, 16, 5, 0.05F, null);

        buy(list, "cratedelight:apple_crate", 1, 1, 16, 5, 0.05F, null);
        buy(list, "cratedelight:berry_crate", 1, 1, 16, 5, 0.05F, null);
        buy(list, "cratedelight:pumpkin_slice_crate", 1, 1, 16, 5, 0.05F, null);
        buy(list, "cratedelight:egg_crate", 1, 1, 16, 5, 0.05F, null);
        buy(list, "cratedelight:red_mushroom_crate", 1, 1, 16, 5, 0.05F, null);
        buy(list, "cratedelight:brown_mushroom_crate", 1, 1, 16, 5, 0.05F, null);
        buy(list, "cratedelight:peanut_crate", 1, 1, 16, 5, 0.05F, EXPANDED_DELIGHT);

        // Seeds and scrap. Cheap on purpose so they are a dump, not a strategy.
        buy(list, "cratedelight:wheat_seeds_bag", 3, 1, 12, 2, 0.05F, null);
        buy(list, "cratedelight:pumpkin_seeds_bag", 3, 1, 12, 2, 0.05F, null);
        buy(list, "cratedelight:melon_seeds_bag", 3, 1, 12, 2, 0.05F, null);
        buy(list, "cratedelight:beetroot_seeds_bag", 3, 1, 12, 2, 0.05F, null);
        buy(list, "cratedelight:poisonous_potato_crate", 4, 1, 8, 1, 0.05F, null);
        buy(list, "farmersdelight:straw_bale", 2, 1, 12, 2, 0.05F, null);
    }

    private static void apprentice(List<VillagerTrades.ItemListing> list) {
        // Nine bread is about 27 wheat plus crafting. Two emeralds beats selling the wheat loose, without doubling it.
        buy(list, "cratedelight:bread_bag", 1, 2, 12, 10, 0.05F, null);
        buy(list, "cratedelight:cookie_bag", 2, 1, 12, 6, 0.05F, null);
        // Fish take a real farm or a rod. Tropical fish pay less because they are mostly decorative.
        buy(list, "cratedelight:cod_crate", 1, 2, 12, 10, 0.05F, null);
        buy(list, "cratedelight:salmon_crate", 1, 2, 12, 10, 0.05F, null);
        buy(list, "cratedelight:pufferfish_crate", 1, 2, 12, 10, 0.05F, null);
        buy(list, "cratedelight:tropical_fish_crate", 1, 1, 12, 6, 0.05F, null);
    }

    private static void journeyman(List<VillagerTrades.ItemListing> list) {
        buy(list, "cratedelight:glowberry_crate", 1, 2, 12, 12, 0.05F, null);
        // Sugar cane is fast, so a bag pays half a staple crate. Cocoa is slower and pays the full emerald.
        buy(list, "cratedelight:sugar_bag", 2, 1, 16, 6, 0.05F, null);
        buy(list, "cratedelight:cocoabeans_bag", 1, 1, 12, 8, 0.05F, null);
        buy(list, "cratedelight:gunpowder_bag", 2, 1, 12, 6, 0.05F, null);
        buy(list, "cratedelight:salt_bag", 2, 1, 12, 6, 0.05F, EXPANDED_DELIGHT);
        buy(list, "cratedelight:ground_cinnamon_bag", 2, 1, 12, 6, 0.05F, EXPANDED_DELIGHT);
    }

    private static void expert(List<VillagerTrades.ItemListing> list) {
        // Nine golden carrots are 9 carrots and 8 gold ingots.
        buy(list, "cratedelight:golden_carrot_crate", 1, 6, 8, 16, 0.08F, null);
        sell(list, 1, "farmersdelight:rope", 8, 16, 4, 0.05F);
    }

    private static void master(List<VillagerTrades.ItemListing> list) {
        // Nine golden apples are 9 apples and 72 gold ingots. Limited stock, and demand pushes the price up faster.
        buy(list, "cratedelight:golden_apple_crate", 1, 16, 4, 30, 0.20F, null);
        sell(list, 16, "minecraft:name_tag", 1, 8, 15, 0.05F);
    }

    private static void buy(List<VillagerTrades.ItemListing> list, String itemId, int crates, int emeralds, int uses, int xp, float priceMultiplier, String requiredMod) {
        list.add((trader, random) -> {
            if (requiredMod != null && !ModList.get().isLoaded(requiredMod)) {
                return null;
            }
            Item item = itemOrNull(itemId, requiredMod == null);
            if (item == null) {
                return null;
            }
            return new MerchantOffer(
                    new ItemCost(item, FactorConfig.scaleCrates(crates)),
                    new ItemStack(Items.EMERALD, FactorConfig.scaleEmeralds(emeralds)),
                    uses,
                    xp,
                    priceMultiplier
            );
        });
    }

    private static void sell(List<VillagerTrades.ItemListing> list, int emeralds, String resultId, int resultCount, int uses, int xp, float priceMultiplier) {
        list.add((trader, random) -> {
            if (!FactorConfig.SELL_TRADES.get()) {
                return null;
            }
            Item result = itemOrNull(resultId, true);
            if (result == null) {
                return null;
            }
            return new MerchantOffer(
                    new ItemCost(Items.EMERALD, FactorConfig.scaleEmeralds(emeralds)),
                    new ItemStack(result, resultCount),
                    uses,
                    xp,
                    priceMultiplier
            );
        });
    }

    private static Item itemOrNull(String itemId, boolean warnIfMissing) {
        ResourceLocation id = ResourceLocation.parse(itemId);
        Item item = BuiltInRegistries.ITEM.getOptional(id).orElse(null);
        if (item == null || item == Items.AIR) {
            if (warnIfMissing && WARNED_MISSING.add(itemId)) {
                FactorsDelight.LOGGER.warn("Factor trade skipped, item {} is not loaded", itemId);
            }
            return null;
        }
        return item;
    }
}
