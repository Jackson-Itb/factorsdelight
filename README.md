# Provisioner's Delight

NeoForge addon for **Minecraft 1.21.1**. Adds one villager, the **Provisioner**, who buys packed farm goods and pays emeralds.

The built jar in this repo is [release/provisionersdelight-1.0.0.jar](release/provisionersdelight-1.0.0.jar). It is the same file that was compiled here (SHA-256 `8fcd477c393ead972f1a016cf0eb57c90f8f3c2e7b4a4332fe32e7ce92a882cf`), not a rebuild.

The jars this was built against are Farmer's Delight 1.3.4, Crate Delight 26.07.01, Chef's Delight 1.0.6, and Create 6.0.10, all for **1.21.1 NeoForge**. It will not load on 1.20.1. Those mods are not 1.20.1 builds.

## What you need

- Minecraft 1.21.1
- NeoForge 21.1.219 or newer
- Farmer's Delight 1.3.4 or newer
- Crate Delight (the 1.21 NeoForge build)

Create is **not** required. If you already run a Create factory, mechanical crafters follow the normal crate recipes, so the line can pack crops and you can sell the crates. Expanded Delight is optional: peanut crates, salt bags, and cinnamon bags are bought only when it is installed.

## How a villager becomes a Provisioner

Craft a **Provision Counter**:

- paper, sticks, and a barrel on top of three Farmer's Delight straw

Put it down near an unemployed villager who can path to it (about three blocks, same idea as a Chef's Delight skillet or cooking pot). One counter employs one villager. They need a bed, and they need to be able to reach the counter, or they will not restock.

```
/summon minecraft:villager ~ ~ ~ {VillagerData:{profession:"provisionersdelight:provisioner",type:"minecraft:plains",level:1}}
```

Summoning them already employed only rolls that level's trades. Letting them take the job themselves is the reliable way to get the full ladder.

## What they pay

A crate or bag is the normal 3x3 recipe, so it holds **9 items**. A vanilla farmer pays 1 emerald for roughly 20 wheat or 22 to 26 carrots or potatoes. The Provisioner pays **1 emerald for one staple crate** (9 crops). That is about two and a half to three times the loose rate. The extra is the wholesale bonus for packing.

Each offer has a limited number of sales. Villagers restock up to twice per Minecraft day. One Provisioner buying staples is about **32 emeralds a day** at the default rate (16 sales, two restocks). A hall of them is how a factory scales, not one villager with an infinite trade.

Carrot, potato, and beetroot crates come from **Farmer's Delight** when both mods are installed. Crate Delight disables its copies of those three recipes so they do not collide. Cabbage, tomato, onion, rice bags, and rice bales are Farmer's Delight too. Everything else in the list is a Crate Delight crate or bag.

| Goods | You give | They pay | Level | Sales before restock |
| --- | --- | --- | --- | --- |
| Carrot, potato, beetroot, cabbage, tomato, onion crates; rice bag; rice bale | 1 | 1 emerald | Novice | 16 |
| Apple, berry, pumpkin slice, egg, red or brown mushroom crates | 1 | 1 emerald | Novice | 16 |
| Peanut crate (Expanded Delight) | 1 | 1 emerald | Novice | 16 |
| Seed bags (wheat, pumpkin, melon, beetroot) | 3 | 1 emerald | Novice | 12 |
| Straw bale | 2 | 1 emerald | Novice | 12 |
| Poisonous potato crate | 4 | 1 emerald | Novice | 8 |
| Bread bag | 1 | 2 emeralds | Apprentice | 12 |
| Cookie bag | 2 | 1 emerald | Apprentice | 12 |
| Cod, salmon, or pufferfish crate | 1 | 2 emeralds | Apprentice | 12 |
| Tropical fish crate | 1 | 1 emerald | Apprentice | 12 |
| Glow berry crate | 1 | 2 emeralds | Journeyman | 12 |
| Cocoa bean bag | 1 | 1 emerald | Journeyman | 12 |
| Sugar bag, gunpowder bag | 2 | 1 emerald | Journeyman | 16 / 12 |
| Salt or cinnamon bag (Expanded Delight) | 2 | 1 emerald | Journeyman | 12 |
| Golden carrot crate | 1 | 6 emeralds | Expert | 8 |
| Rope (they sell this) | 1 emerald | 8 rope | Expert | 16 |
| Golden apple crate | 1 | 16 emeralds | Master | 4 |
| Name tag (they sell this) | 16 emeralds | 1 name tag | Master | 8 |

Golden carrot crates are 9 carrots and 8 gold ingots. Golden apple crates are 9 apples and 72 gold ingots, stock is tight, and the price climbs faster if you keep selling them. That is a bonus for a real crafting bill, not a gold-farm converter: 72 ingots for 16 emeralds is a bad gold trade on purpose.

Sugar and gunpowder pay less per bag because those farms are fast. Seeds and poisonous potatoes are a dump.

Config file: `config/provisionersdelight-common.toml`

- `emeraldMultiplier` defaults to `1.0`. `2.0` doubles every emerald they pay or charge.
- `crateMultiplier` defaults to `1.0`. `2.0` makes them demand twice as many crates.
- `enableSellTrades` turns the rope and name tag offers off.

Offers a villager already learned do not change until they roll trades again.

## Build

Java 21 and the Gradle wrapper in this folder.

```
./gradlew build
```

The mod jar is `build/libs/provisionersdelight-1.0.0.jar`.
