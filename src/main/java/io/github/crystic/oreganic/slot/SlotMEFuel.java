package io.github.crystic.oreganic.slot;

import io.github.crystic.oreganic.tileentity.TileEntityBasicMineralExtractor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMEFuel extends Slot {
	
	public SlotMEFuel(EntityPlayer player, IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
	}
	
	@Override
	public boolean isItemValid(ItemStack itemstack) {
		if(TileEntityBasicMineralExtractor.isItemFuel(itemstack)) {
			return true;
		}
		else return false;
	}

}
