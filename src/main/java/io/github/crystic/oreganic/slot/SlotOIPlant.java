package io.github.crystic.oreganic.slot;

import io.github.crystic.oreganic.tileentity.TileEntityOrganicInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotOIPlant extends Slot {
	
	public SlotOIPlant(EntityPlayer player, IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
	}
	
	@Override
	public boolean isItemValid(ItemStack itemstack) {
		if(TileEntityOrganicInfuser.isOrganicInput(itemstack)) {
			return true;
		}
		else return false;
	}

}
