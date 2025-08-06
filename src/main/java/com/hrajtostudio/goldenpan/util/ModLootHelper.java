package com.hrajtostudio.goldenpan.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.*;

public class ModLootHelper {
    private static final Random RANDOM = new Random();
    private static final Map<String, List<LootEntry>> LOOT_TABLES = new HashMap<>();

    public static class LootEntry {
        public String item;
        public double chance;
    }

    public static void giveRandomLoot(ServerWorld world, PlayerEntity player, String material) {
        if (LOOT_TABLES.isEmpty()) loadLootTables();

        List<LootEntry> entries = LOOT_TABLES.get(material);
        if (entries == null) {
            System.err.println("[GoldenPan] 🛠️ Loot table for material '" + material + "' not found!");
            return;
        }

        double roll = RANDOM.nextDouble();
        double cum = 0.0;
        for (LootEntry e : entries) {
            cum += e.chance;
            if (roll < cum) {
                var item = Registries.ITEM.get(new Identifier(e.item));
                if (item != null) player.dropStack(new ItemStack(item));
                return;
            }
        }
    }

    private static void loadLootTables() {
        Gson gson = new Gson();
        Type entryList = new TypeToken<List<LootEntry>>() {}.getType();

        LOOT_TABLES.clear();
        try {
            for (var mat : Arrays.asList("sand", "gravel", "red_sand", "mud")) {
                var path = "data/goldenpan/loot/" + mat + ".json";
                var stream = ModLootHelper.class.getClassLoader().getResourceAsStream(path);
                if (stream != null) {
                    @SuppressWarnings("unchecked")
                    List<LootEntry> list = (List<LootEntry>) gson.fromJson(new InputStreamReader(stream), entryList);
                    LOOT_TABLES.put(mat, list);
                }
            }
            System.out.println("[GoldenPan] ✅ Loaded loot tables: " + LOOT_TABLES.keySet());
        } catch (Exception ex) {
            System.err.println("[GoldenPan] ⚠️ Error loading loot JSON:");
            ex.printStackTrace();
        }
    }
}
