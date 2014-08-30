package io.github.crystic.oreganic.container;

import io.github.crystic.oreganic.slot.SlotOIFuel;
import io.github.crystic.oreganic.slot.SlotOIMineral;
import io.github.crystic.oreganic.slot.SlotOIOutput;
import io.github.crystic.oreganic.slot.SlotOIPlant;
import io.github.crystic.oreganic.tileentity.TileEntityHobbyOrganicInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerHobbyOrganicInfuser extends Container {

	private TileEntityHobbyOrganicInfuser infuser;
	private int dualCookTime;
	private int dualPower;
	@SuppressWarnings("unused")
	private int lastItemBurnTime;
	
	public ContainerHobbyOrganicInfuser(InventoryPlayer invPlayer, TileEntityHobbyOrganicInfuser teOrganicInfuser) {
		dualCookTime = 0;
		dualPower = 0;
		lastItemBurnTime = 0;
		infuser = teOrganicInfuser;
		
		this.addSlotToContainer(new SlotOIPlant(invPlayer.player, teOrganicInfuser, 0, 48, 15)); //Plant Mat
		this.addSlotToContainer(new SlotOIMineral(invPlayer.player, teOrganicInfuser, 1, 48, 54)); //Mineral
		this.addSlotToContainer(new SlotOIFuel(invPlayer.player, teOrganicInfuser, 2, 17, 54)); //Fuel
		this.addSlotToContainer(new SlotOIOutput(invPlayer.player, teOrganicInfuser, 3, 104, 34));
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
		}		
	}
	
	public void addCraftingCrafters(ICrafting crafting) {
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, this.infuser.dualCookTime);
		crafting.sendProgressBarUpdate(this, 1, this.infuser.dualPower);
	}
	
	
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return infuser.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotNum) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotNum);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (slotNum < 3) {
				if (!this.mergeItemStack(itemstack1, 2, 39, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (slotNum != 1 && slotNum != 0) {
				if (TileEntityHobbyOrganicInfuser.isOrganicInput(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
				} else if (TileEntityHobbyOrganicInfuser.isMineralInput(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return null;
					}
				} else if(TileEntityHobbyOrganicInfuser.isItemFuel(itemstack1)){
					if(!this.mergeItemStack(itemstack1, 2, 3, false)) {
						return null;
					}
				} else if (slotNum >= 3 && slotNum < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
						return null;
					}
				} else if (slotNum >= 30 && slotNum < 39
						&& !this.mergeItemStack(itemstack1, 3, 30, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}
	
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);
			
			if(this.dualCookTime != this.infuser.dualCookTime) {
				icrafting.sendProgressBarUpdate(this, 0, this.infuser.dualCookTime);
			}
			
			if(this.dualPower != this.infuser.dualPower) {
				icrafting.sendProgressBarUpdate(this, 1, this.infuser.dualPower);
			}
		}
		this.dualCookTime = this.infuser.dualCookTime;
		this.dualPower = this.infuser.dualPower;
	}
	
	public void updateProgressBar(int i, int j) {
		if(i == 0) {
			infuser.dualCookTime = j;
		}
		
		if(i == 1) {
			infuser.dualPower = j;
		}
		
	}
	
	
}
