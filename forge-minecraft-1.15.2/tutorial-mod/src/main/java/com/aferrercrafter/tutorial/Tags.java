package com.aferrercrafter.tutorial;

import com.aferrercrafter.tutorial.init.ModItems;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class Tags {
    ResourceLocation myTagId = new ResourceLocation("forge", "gems/ruby");
    Item testItem = ModItems.RUBY.get();
    boolean isInGroup = ItemTags.getCollection().getOrCreate(myTagId).contains(testItem);
}
