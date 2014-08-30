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

		if(item == Item.getItemFromBlock(Oreganic.blockOrganicCoal)) return new ItemStack(Item.getItemFromBlock(Blocks.coal_ore));
		if(item == Item.getItemFromBlock(Oreganic.blockOrganicIron)) return new ItemStack(Item.getItemFromBlock(Blocks.iron_ore));
		if(item == Item.getItemFromBlock(Oreganic.blockOrganicGold)) return new ItemStack(Item.getItemFromBlock(Blocks.gold_ore));
		if(item == Item.getItemFromBlock(Oreganic.blockOrganicLapis)) return new ItemStack(Item.getItemFromBlock(Blocks.lapis_ore));
		if(item == Item.getItemFromBlock(Oreganic.blockOrganicRedstone)) return new ItemStack(Item.getItemFromBlock(Blocks.redstone_ore));
		if(item == Item.getItemFromBlock(Oreganic.blockOrganicDiamond)) return new ItemStack(Item.getItemFromBlock(Blocks.diamond_ore));
		if(item == Item.getItemFromBlock(Oreganic.blockOrganicEmerald)) return new ItemStack(Item.getItemFromBlock(Blocks.emerald_ore));
		if(item == Item.getItemFromBlock(Oreganic.blockOrganicQuartz)) return new ItemStack(Item.getItemFromBlock(Blocks.quartz_ore));
		
		return null;
	}
}