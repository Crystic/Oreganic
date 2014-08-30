package io.github.crystic.oreganics.container;

import io.github.crystic.oreganics.slot.SlotMEFuel;
import io.github.crystic.oreganics.slot.SlotMEMineral;
import io.github.crystic.oreganics.tileentity.TileEntityProMineralExtractor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerProMineralExtractor extends Container {

	private TileEntityProMineralExtractor hobbyMineralExtractor;

	public int lastBurnTime;
	public int lastCurrentItemBurnTime;
	public int lastCookTime;

	public ContainerProMineralExtractor(InventoryPlayer inventory, TileEntityProMineralExtractor tileentity) {
		this.hobbyMineralExtractor = tileentity;

		this.addSlotToContainer(new SlotMEMineral(inventory.player, tileentity, 0, 48, 34));
		this.addSlotToContainer(new SlotMEFuel(inventory.player, tileentity, 1, 17, 54));
		this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity,
				2, 104, 34));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9,
						8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}
	}

	public void addCraftingToCrafters(ICrafting icrafting) {
		super.addCraftingToCrafters(icrafting);
		icrafting
				.sendProgressBarUpdate(this, 0, this.hobbyMineralExtractor.cookTime);
		icrafting
				.sendProgressBarUpdate(this, 1, this.hobbyMineralExtractor.burnTime);
		icrafting.sendProgressBarUpdate(this, 2,
				this.hobbyMineralExtractor.currentItemBurnTime);
	}

	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.lastCookTime != this.hobbyMineralExtractor.cookTime) {
				icrafting.sendProgressBarUpdate(this, 0,
						this.hobbyMineralExtractor.cookTime);
			}

			if (this.lastBurnTime != this.hobbyMineralExtractor.burnTime) {
				icrafting.sendProgressBarUpdate(this, 1,
						this.hobbyMineralExtractor.burnTime);
			}

			if (this.lastCurrentItemBurnTime != this.hobbyMineralExtractor.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2,
						this.hobbyMineralExtractor.currentItemBurnTime);
			}
		}

		this.lastCookTime = this.hobbyMineralExtractor.cookTime;
		this.lastBurnTime = this.hobbyMineralExtractor.burnTime;
		this.lastCurrentItemBurnTime = this.hobbyMineralExtractor.currentItemBurnTime;
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			this.hobbyMineralExtractor.cookTime = par2;
		}

		if (par1 == 1) {
			this.hobbyMineralExtractor.burnTime = par2;
		}

		if (par1 == 2) {
			this.hobbyMineralExtractor.currentItemBurnTime = par2;
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotNum) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotNum);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (slotNum == 2) {
				if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (slotNum != 1 && slotNum != 0) {
				if (TileEntityProMineralExtractor.isItemInput(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
				} else if (TileEntityProMineralExtractor.isItemFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return null;
					}
				} else if (slotNum >= 3 && slotNum < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
						return null;
					}
				} else if (slotNum >= 30 && slotNum < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
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

	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

}
