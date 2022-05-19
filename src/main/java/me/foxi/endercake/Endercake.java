package me.foxi.endercake;

import me.foxi.endercake.block.ChocolateCakeBlock;
import me.foxi.endercake.block.EnderCakeBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Endercake implements ModInitializer {

    public static final Block ENDER_CAKE = new EnderCakeBlock(FabricBlockSettings.of(Material.CAKE).strength(0.5F).sounds(BlockSoundGroup.WOOL));
    public static final Block CHOCOLATE_CAKE = new ChocolateCakeBlock(FabricBlockSettings.of(Material.CAKE).strength(0.5F).sounds(BlockSoundGroup.WOOL));

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier("endercake", "ender_cake"), ENDER_CAKE);
        Registry.register(Registry.ITEM, new Identifier("endercake", "ender_cake"), new BlockItem(ENDER_CAKE, new FabricItemSettings().group(ItemGroup.FOOD)));

        Registry.register(Registry.BLOCK, new Identifier("endercake", "chocolate_cake"), CHOCOLATE_CAKE);
        Registry.register(Registry.ITEM, new Identifier("endercake", "chocolate_cake"), new BlockItem(CHOCOLATE_CAKE, new FabricItemSettings().group(ItemGroup.FOOD)));
    }
}
