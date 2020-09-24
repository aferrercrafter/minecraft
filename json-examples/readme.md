# Models

### Item

``` json
{
  "parent": "item/generated",
  "textures": {
	"layer0": "MOD_ID:items/ITEM_NAME"
  }
}
```

### Item Block

``` json
{
  "parent": "MOD_ID:block/BLOCK_NAME"
}
``` 

### Block

``` json
{
  "parent": "block/cube_all",
  "textures": {
    "all": "MOD_ID:blocks/BLOCK_NAME"
  }
}
```

### BlockState

``` json
{
  "variants": {
    "": { "model": "MOD_ID:block/BLOCK_NAME" }
  }
}
```

### Tool

``` json
{
  "parent": "item/handheld",
  "textures": {
    "layer0": "MOD_ID:items/ITEM"
  }
}
```

# Loot Table

#### Basic

``` json
{
  "type": "minecraft:block",
  "pools": [
    {
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "MOD_ID:ITEM_NAME"
        }
      ]
    }
  ]
}
```

#### Multi-Drop

``` json
{
  "type": "minecraft:block",
  "pools": [
    {
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:alternatives",
          "children": [
            {
              "type": "minecraft:item",
              "functions": [
                {
                  "function": "minecraft:set_count",
                  "count": {
                    "min": 1,
                    "max": 1,
                    "type": "minecraft:uniform"
                  }
                }
              ],
              "name": "MOD_ID:ITEM_NAME"
            }
          ]
        }
      ]
    }
  ]
}
```

#### Fortune 


``` json

               {
                  "function": "minecraft:apply_bonus",
                  "enchantment": "minecraft:fortune",
                  "formula": "minecraft:uniform_bonus_count",
                  "parameters": {
                    "bonusMultiplier": 1
                  }
                }
```

#### Silk Touch 

``` json
{
              "type": "minecraft:item",
              "conditions": [
                {
                  "condition": "minecraft:match_tool",
                  "predicate": {
                    "enchantments": [
                      {
                        "enchantment": "minecraft:silk_touch",
                        "levels": {
                          "min": 1
                        }
                      }
                    ]
                  }
                }
              ],
              "name": "MOD_ID:ITEM_NAME"
            },
```

# Recipes

#### Shaped Recipe

``` json
{
  "type": "minecraft:crafting_shaped",
  "pattern":
  [
    "xxx",
    "xxx",
    "xxx"
  ],
  "key":
  {
    "x":
    {
      "item": "MOD_ID:ITEM"
    }
  },
  "result":
  {
    "item": "MOD_ID:ITEM",
    "count": 1
  }
}
```

#### Shapeless Recipe

``` json
{
  "type": "minecraft:crafting_shapeless",
  "ingredients": [
    {
      "item": "MOD_ID:ITEM"
    }
  ],
  "result":
  {
    "item": "MOD_ID:ITEM",
    "count": 1
  }
}
```

#### Smelting Recipe

``` json
{
  "type": "minecraft:smelting",
  "ingredient": {
    "item": "MOD_ID:ITEM"
  },
  "result": "MOD_ID:ITEM",
  "experience": 0.0,
  "cookingtime": 200
}
```

