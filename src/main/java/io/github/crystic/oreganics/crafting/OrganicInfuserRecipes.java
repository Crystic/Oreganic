package io.github.crystic.oreganics.crafting;

import io.github.crystic.oreganics.Oreganic;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class OrganicInfuserRecipes {

	public OrganicInfuserRecipes() {
		
	}
	
	public static ItemStack getInfusionResult(Item item, Item item2) {
		return getOutput(item, item2);
		
	}
	
	public static ItemStack getOutput(Item item, Item item2) {

		if(item == Items.melon_seeds && item2 == Item.getItemFromBlock(Blocks.coal_ore)) return new ItemStack (Oreganic.cropCoalSeeds);
		else if(item == Items.melon_seeds && item2 == Item.getItemFromBlock(Blocks.iron_ore)) return new ItemStack (Oreganic.cropIronSeeds);
		else if(item == Items.melon_seeds && item2 == Item.getItemFromBlock(Blocks.gold_ore)) return new ItemStack (Oreganic.cropGoldSeeds);
		else if(item == Items.melon_seeds && item2 == Item.getItemFromBlock(Blocks.lapis_ore)) return new ItemStack (Oreganic.cropLapisSeeds);
		else if(item == Items.melon_seeds && item2 == Item.getItemFromBlock(Blocks.redstone_ore)) return new ItemStack (Oreganic.cropRedstoneSeeds);
		else if(item == Items.melon_seeds && item2 == Item.getItemFromBlock(Blocks.diamond_ore)) return new ItemStack (Oreganic.cropDiamondSeeds);
		else if(item == Items.melon_seeds && item2 == Item.getItemFromBlock(Blocks.emerald_ore)) return new ItemStack (Oreganic.cropEmeraldSeeds);
		else if(item == Items.melon_seeds && item2 == Item.getItemFromBlock(Blocks.quartz_ore)) return new ItemStack (Oreganic.cropQuartzSeeds);
		
		else if(item == Items.pumpkin_seeds && item2 == Item.getItemFromBlock(Blocks.coal_ore)) return new ItemStack (Oreganic.cropCoalSeeds);
		else if(item == Items.pumpkin_seeds && item2 == Item.getItemFromBlock(Blocks.iron_ore)) return new ItemStack (Oreganic.cropIronSeeds);
		else if(item == Items.pumpkin_seeds && item2 == Item.getItemFromBlock(Blocks.gold_ore)) return new ItemStack (Oreganic.cropGoldSeeds);
		else if(item == Items.pumpkin_seeds && item2 == Item.getItemFromBlock(Blocks.lapis_ore)) return new ItemStack (Oreganic.cropLapisSeeds);
		else if(item == Items.pumpkin_seeds && item2 == Item.getItemFromBlock(Blocks.redstone_ore)) return new ItemStack (Oreganic.cropRedstoneSeeds);
		else if(item == Items.pumpkin_seeds && item2 == Item.getItemFromBlock(Blocks.diamond_ore)) return new ItemStack (Oreganic.cropDiamondSeeds);
		else if(item == Items.pumpkin_seeds && item2 == Item.getItemFromBlock(Blocks.emerald_ore)) return new ItemStack (Oreganic.cropEmeraldSeeds);
		else if(item == Items.pumpkin_seeds && item2 == Item.getItemFromBlock(Blocks.quartz_ore)) return new ItemStack (Oreganic.cropQuartzSeeds);
		
		return null;
	}
	
}
