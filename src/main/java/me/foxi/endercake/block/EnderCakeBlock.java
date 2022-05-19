package me.foxi.endercake.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class EnderCakeBlock extends CakeBlock {
    public EnderCakeBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return tryEat(world, pos, state, player);
    }

    public static ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
        int i = state.get(BITES);

        ItemStack item = player.getEquippedStack(EquipmentSlot.MAINHAND);
        if(item.getItem() == Items.ENDER_EYE) {
            if(i == 0)
                return ActionResult.SUCCESS;
            world.setBlockState(pos, state.with(BITES, i - 1), 3);
            item.decrement(1);
            return ActionResult.SUCCESS;
        }

        if (i < 6) {
            world.setBlockState(pos, state.with(BITES, i + 1), 3);
        } else {
            world.removeBlock(pos, false);
        }

        if(player.getEntityWorld() instanceof ServerWorld && player.canUsePortals() && !player.hasVehicle() && !player.hasPassengers()) {
            RegistryKey<World> registryKey = player.getEntityWorld().getRegistryKey() == World.END ? World.OVERWORLD : World.END;
            ServerWorld serverWorld = player.getEntityWorld().getServer().getWorld(registryKey);
            if(serverWorld == null) {
                return ActionResult.FAIL;
            }

            player.moveToWorld(serverWorld);
        }

        return ActionResult.SUCCESS;
    }
}
