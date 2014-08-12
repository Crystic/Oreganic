package io.github.crystic.oreganic.crafting;

import io.github.crystic.oreganic.Oreganic;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MineralExtractorRecipes {

	public static ItemStack extraction(Item item) {
		return getOutput(item);
	}

	public static ItemStack getOutput(Item item) {

		if(item == Item.getItemFromBlock(Oreganic.cropOrganicCoal)) return new ItemStack(Item.getItemFromBlock(Blocks.coal_ore));
		else if(item == Item.getItemFromBlock(Oreganic.cropOrganicIron)) return new ItemStack(Item.getItemFromBlock(Blocks.iron_ore));
		else if(item == Item.getItemFromBlock(Oreganic.cropOrganicGold)) return new ItemStack(Item.getItemFromBlock(Blocks.gold_ore));
		else if(item == Item.getItemFromBlock(Oreganic.cropOrganicLapis)) return new ItemStack(Item.getItemFromBlock(Blocks.lapis_ore));
		else if(item == Item.getItemFromBlock(Oreganic.cropOrganicRedstone)) return new ItemStack(Item.getItemFromBlock(Blocks.redstone_ore));
		else if(item == Item.getItemFromBlock(Oreganic.cropOrganicDiamond)) return new ItemStack(Item.getItemFromBlock(Blocks.diamond_ore));
		else if(item == Item.getItemFromBlock(Oreganic.cropOrganicEmerald)) return new ItemStack(Item.getItemFromBlock(Blocks.emerald_ore));
		
		return null;
	}
}