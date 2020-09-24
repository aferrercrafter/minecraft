package com.aferrercrafter.tutorial.util.enums;

import com.aferrercrafter.tutorial.Tutorial;
import com.aferrercrafter.tutorial.init.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import java.util.function.Supplier;

public enum ModArmorMaterial implements IArmorMaterial {

    RUBY(Tutorial.MOD_ID + ":ruby", 25, new int[]{ 2, 5, 6, 2}, 18,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f, () -> { return Ingredient.fromItems(ModItems.RUBY.get()); });

    private static final int[] MAX_DAMAGE_ARRAY = new int[] { 11, 16, 15, 13};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent sound;
    private final float toughness;
    private final Supplier<Ingredient> repairMaterial;

    ModArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent sound, float toughness, Supplier<Ingredient> repairMaterial){
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.sound = sound;
        this.toughness = toughness;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return damageReductionAmountArray[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return sound;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }
}
