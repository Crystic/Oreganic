package io.github.crystic.oreganics.container;

import io.github.crystic.oreganics.slot.SlotMEFuel;
import io.github.crystic.oreganics.slot.SlotMEMineral;
import io.github.crystic.oreganics.tileentity.TileEntityBasicMineralExtractor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerBasicMineralExtractor extends Container {

	private TileEntityBasicMineralExtractor basicMineralExtractor;

	public int lastBurnTime;
	public int lastCurrentItemBurnTime;
	public int lastCookTime;

	public ContainerBasicMineralExtractor(InventoryPlayer inventory, TileEntityBasicMineralExtractor tileentity) {
		this.basicMineralExtractor = tileentity;

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
				.sendProgressBarUpdate(this, 0, this.basicMineralExtractor.cookTime);
		icrafting
				.sendProgressBarUpdate(this, 1, this.basicMineralExtractor.burnTime);
		icrafting.sendProgressBarUpdate(this, 2,
				this.basicMineralExtractor.currentItemBurnTime);
	}

	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.lastCookTime != this.basicMineralExtractor.cookTime) {
				icrafting.sendProgressBarUpdate(this, 0,
						this.basicMineralExtractor.cookTime);
			}

			if (this.lastBurnTime != this.basicMineralExtractor.burnTime) {
				icrafting.sendProgressBarUpdate(this, 1,
						this.basicMineralExtractor.burnTime);
			}

			if (this.lastCurrentItemBurnTime != this.basicMineralExtractor.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2,
						this.basicMineralExtractor.currentItemBurnTime);
			}
		}

		this.lastCookTime = this.basicMineralExtractor.cookTime;
		this.lastBurnTime = this.basicMineralExtractor.burnTime;
		this.lastCurrentItemBurnTime = this.basicMineralExtractor.currentItemBurnTime;
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			this.basicMineralExtractor.cookTime = par2;
		}

		if (par1 == 1) {
			this.basicMineralExtractor.burnTime = par2;
		}

		if (par1 == 2) {
			this.basicMineralExtractor.currentItemBurnTime = par2;
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
				if (TileEntityBasicMineralExtractor.isItemInput(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
				} else if (TileEntityBasicMineralExtractor.isItemFuel(itemstack1)) {
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
