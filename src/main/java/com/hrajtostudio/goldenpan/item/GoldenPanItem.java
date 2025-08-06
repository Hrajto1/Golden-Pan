package com.hrajtostudio.goldenpan.item;

import com.hrajtostudio.goldenpan.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class GoldenPanItem extends Item {
    public GoldenPanItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block block = world.getBlockState(context.getBlockPos()).getBlock();
        PlayerEntity player = context.getPlayer();

        if (player == null) return ActionResult.PASS;

        ItemStack newPan = null;

        if (block == Blocks.SAND) {
            newPan = ModItems.WOODEN_PAN_WITH_SAND.getDefaultStack();
        } else if (block == Blocks.GRAVEL) {
            newPan = ModItems.WOODEN_PAN_WITH_GRAVEL.getDefaultStack();
        } else if (block == Blocks.RED_SAND) {
            newPan = ModItems.WOODEN_PAN_WITH_RED_SAND.getDefaultStack();
        } else if (block == Blocks.MUD) {
            newPan = ModItems.WOODEN_PAN_WITH_MUD.getDefaultStack();
        }


        if (newPan != null) {
            if (!world.isClient) {
                world.breakBlock(context.getBlockPos(), false); // remove block
                player.setStackInHand(context.getHand(), newPan); // give the player a filled pan
            }
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
