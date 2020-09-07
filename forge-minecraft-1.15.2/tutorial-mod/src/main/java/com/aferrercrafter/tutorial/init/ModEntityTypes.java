package com.aferrercrafter.tutorial.init;

import com.aferrercrafter.tutorial.Tutorial;
import com.aferrercrafter.tutorial.entities.HogEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<EntityType<?>>(ForgeRegistries.ENTITIES, Tutorial.MOD_ID);

    public static void init(){
        ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Entity Types
    public static final RegistryObject<EntityType<HogEntity>> HOG = ENTITY_TYPES.register("hog",
            () -> EntityType.Builder.create(HogEntity::new, EntityClassification.CREATURE)
                    .size(1.0f,1.0f)
                    .build(new ResourceLocation(Tutorial.MOD_ID, "hog").toString()));
}
