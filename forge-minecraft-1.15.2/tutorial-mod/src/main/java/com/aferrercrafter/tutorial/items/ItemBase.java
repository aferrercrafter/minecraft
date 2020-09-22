package com.aferrercrafter.tutorial.items;

import com.aferrercrafter.tutorial.Tutorial;
import net.minecraft.item.Item;

public class ItemBase extends Item{

    public ItemBase(){
        super(new Item.Properties().group(Tutorial.CREATIVE_TAB));
    }

}
