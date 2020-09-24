package com.aferrercrafter.tutorial;

import com.aferrercrafter.tutorial.init.ModBlocks;
import com.aferrercrafter.tutorial.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Mod ID
@Mod("tutorial")
public class Tutorial
{

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "tutorial";

    public Tutorial() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        ModItems.init();
        ModBlocks.init();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event){

    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    // Creates Custom Tab
    public static final ItemGroup CREATIVE_TAB = new ItemGroup("tutorialTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.RUBY.get());
        }
    };

}
