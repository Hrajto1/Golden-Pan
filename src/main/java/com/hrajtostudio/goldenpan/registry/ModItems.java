package com.hrajtostudio.goldenpan.registry;

import com.hrajtostudio.goldenpan.GoldenPanMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import com.hrajtostudio.goldenpan.item.*;


public class ModItems {
    public static final Item WOODEN_PAN = registerItem("wooden_pan", new GoldenPanItem(new Item.Settings().maxCount(1)));

    public static final Item WOODEN_PAN_WITH_SAND = registerItem("wooden_pan_with_sand", new GoldenPanWithSandItem(new Item.Settings().maxCount(1), "sand"));
    public static final Item WOODEN_PAN_WITH_GRAVEL = registerItem("wooden_pan_with_gravel", new GoldenPanWithSandItem(new Item.Settings().maxCount(1), "gravel"));
    public static final Item WOODEN_PAN_WITH_RED_SAND = registerItem("wooden_pan_with_red_sand", new GoldenPanWithSandItem(new Item.Settings().maxCount(1), "red_sand"));
    public static final Item WOODEN_PAN_WITH_MUD = registerItem("wooden_pan_with_mud", new GoldenPanWithSandItem(new Item.Settings().maxCount(1), "mud"));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(GoldenPanMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        GoldenPanMod.LOGGER.info("Registering Mod Items for " + GoldenPanMod.MOD_ID);
    }
}
