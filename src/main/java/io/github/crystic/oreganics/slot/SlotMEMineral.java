package io.github.crystic.oreganics.slot;

import io.github.crystic.oreganics.tileentity.TileEntityBasicMineralExtractor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMEMineral extends Slot {
	
	public SlotMEMineral(EntityPlayer player, IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
	}
	
	@Override
	public boolean isItemValid(ItemStack itemstack) {
		if(TileEntityBasicMineralExtractor.isItemInput(itemstack)) {
			return true;
		}
		else return false;
	}

}
