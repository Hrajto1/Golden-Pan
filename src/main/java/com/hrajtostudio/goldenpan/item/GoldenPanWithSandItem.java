package com.hrajtostudio.goldenpan.item;

import com.hrajtostudio.goldenpan.registry.ModItems;
import com.hrajtostudio.goldenpan.util.ModLootHelper;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;

public class GoldenPanWithSandItem extends Item {
    private final String material;

    public GoldenPanWithSandItem(Settings settings, String material) {
        super(settings);
        this.material = material;
    }

    private boolean isPlayerInFlowingWater(PlayerEntity player, World world) {
        BlockPos pos = player.getBlockPos();
        var fluidState = world.getFluidState(pos);
        return fluidState.getFluid() == Fluids.FLOWING_WATER && fluidState.getLevel() > 0;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!isPlayerInFlowingWater(player, world)) {
            if (!world.isClient()) {
                player.sendMessage(net.minecraft.text.Text.translatable("message.must_be_in_flowing_water"), true);
            }
            return TypedActionResult.fail(stack);
        }

        player.setCurrentHand(hand); // Starts the scooping animation
        return TypedActionResult.consume(stack);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            if (world instanceof ServerWorld serverWorld) {
                ModLootHelper.giveRandomLoot(serverWorld, player, material);
            }

            world.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_SAND_BREAK, player.getSoundCategory(), 1.0F, 1.0F);

            return new ItemStack(ModItems.WOODEN_PAN);
        }
        return super.finishUsing(stack, world, user);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 40;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }
}
