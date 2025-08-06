package com.hrajtostudio.goldenpan;

import com.hrajtostudio.goldenpan.registry.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup GOLDENPAN_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            new Identifier(GoldenPanMod.MOD_ID, "goldenpan"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.goldenpan"))
                    .icon(() -> new ItemStack(ModItems.WOODEN_PAN))
                    .entries((context, entries) -> {
                        entries.add(ModItems.WOODEN_PAN);
                    })
                    .build()
    );

    public static void registerItemGroups() {
        GoldenPanMod.LOGGER.info("Registering Creative Tabs for " + GoldenPanMod.MOD_ID);
    }
}
