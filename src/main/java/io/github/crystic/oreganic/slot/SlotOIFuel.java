package io.github.crystic.oreganic.slot;

import io.github.crystic.oreganic.tileentity.TileEntityOrganicInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotOIFuel extends Slot {
	public SlotOIFuel(EntityPlayer player, IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
	}
	
	@Override
	public boolean isItemValid(ItemStack itemstack) {
		if(TileEntityOrganicInfuser.isItemFuel(itemstack)) {
			return true;
		}
		else return false;
	}
}
