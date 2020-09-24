package com.aferrercrafter.tutorial.init;

import com.aferrercrafter.tutorial.Tutorial;
import com.aferrercrafter.tutorial.blocks.Oven;
import com.aferrercrafter.tutorial.blocks.RubyBlock;
import com.aferrercrafter.tutorial.blocks.RubyOre;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Tutorial.MOD_ID);

    public static void init(){
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Blocks
    public static final RegistryObject<Block> RUBY_BLOCK = BLOCKS.register("ruby_block", RubyBlock::new);
    public static final RegistryObject<Block> RUBY_ORE = BLOCKS.register("ruby_ore", RubyOre::new);
    public static final RegistryObject<Block> OVEN = BLOCKS.register("oven", Oven::new);
}
