package io.github.crystic.oreganics.slot;

import io.github.crystic.oreganics.tileentity.TileEntityBasicOrganicInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotOIMineral extends Slot {

	public SlotOIMineral(EntityPlayer player, IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
	}
	
	@Override
	public boolean isItemValid(ItemStack itemstack) {
		if(TileEntityBasicOrganicInfuser.isMineralInput(itemstack)) {
			return true;
		}
		else return false;
	}
}
