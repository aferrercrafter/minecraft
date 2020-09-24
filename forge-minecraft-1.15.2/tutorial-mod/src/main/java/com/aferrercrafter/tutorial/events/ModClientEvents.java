package com.aferrercrafter.tutorial.events;

import com.aferrercrafter.tutorial.Tutorial;
import com.aferrercrafter.tutorial.util.RegistryHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Tutorial.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onJumpWithStick(LivingEvent.LivingJumpEvent event){
        LivingEntity player = event.getEntityLiving();
        if(player.getHeldItemMainhand().getItem() == Items.STICK){
            Tutorial.LOGGER.info("Player tried Jump with a stick!");
            World world = player.getEntityWorld();
            world.setBlockState(player.getPosition().add(0,-1,0), RegistryHandler.RUBY_BLOCK.get().getDefaultState());
        }
    }

}
