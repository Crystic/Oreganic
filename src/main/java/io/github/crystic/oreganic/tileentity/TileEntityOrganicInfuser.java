package io.github.crystic.oreganic.tileentity;

import io.github.crystic.oreganic.blocks.OrganicInfuser;
import io.github.crystic.oreganic.crafting.OrganicInfuserRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityOrganicInfuser extends TileEntity implements ISidedInventory{
	
	private ItemStack slots[];
	public int dualPower;
	public static int dualCookTime;
	public static final int maxPower = 2080;
	public static final int infusionSpeed = 200;
	private String customName;
	
	
	private static final int[] slots_top = new int[] {0, 1};
	private static final int[] slots_bottom = new int[] {3};
	private static final int[] slots_side = new int[] {2};
	
	public TileEntityOrganicInfuser() {
		slots = new ItemStack[4];
	}

	@Override
	public int getSizeInventory() {

		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return slots[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if(slots[i] != null) {
			if(slots[i].stackSize <= j) {
				ItemStack itemstack = slots[i];
				slots[i] = null;
				return itemstack;
			}
			ItemStack itemstack1 = slots[i].splitStack(j);
			if(slots[i].stackSize == 0) {
				slots[i] = null;
			}
			return itemstack1;
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if(slots[i] != null) {
			ItemStack itemstack = slots[i];
			slots[i] = null;
			return itemstack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		slots[i] = itemstack;
		if(itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	
	@Override
	public String getInventoryName() {
		return "container.organicInfuser";
	}
	
	@Override
	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		if (worldObj.getTileEntity(xCoord, yCoord, zCoord) != this) {
			return false;
		}else{
			return player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64;
		}
	}

	public void openInventory() {}
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if(i == 0 && (isOrganicInput(itemstack))) return true;
		if(i == 1 && (isMineralInput(itemstack))) return true;
		if(i == 2 && (isItemFuel(itemstack))) return true;
		else return false;
	}
	
	public boolean hasItemPower(ItemStack itemstack) {
		return getItemPower(itemstack) > 0;
	}
	
	private static int getItemPower(ItemStack itemstack) {
		if(itemstack == null) {
			return 0;
		} else {
			Item item = itemstack.getItem();
			if(item == Items.lava_bucket) return 204;  // 2 to cool 1 item
			else if(item == Items.blaze_powder) return 32; // 6 to cook 1 item
			else return 0;
		}
	}
	
	public void readFromNBT (NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList list = nbt.getTagList("Items", 10);
		slots = new ItemStack[getSizeInventory()];
		
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound nbt1 = (NBTTagCompound)list.getCompoundTagAt(i);
			byte b0 = nbt1.getByte("Slot");
			
			if (b0 >= 0 && b0 < slots.length) {
				slots[b0] = ItemStack.loadItemStackFromNBT(nbt1);
			}
		}
		
		dualPower = nbt.getShort("PowerTime");
		dualCookTime = nbt.getShort("CookTime");
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("PowerTime", (short)dualPower);
		nbt.setShort("CookTime", (short)dualCookTime);
		NBTTagList list = new NBTTagList();
		
		for (int i = 0; i < slots.length; i++) {
			if (slots[i] != null) {
				NBTTagCompound nbt1 = new NBTTagCompound();
				nbt1.setByte("Slot", (byte)i);
				slots[i].writeToNBT(nbt1);
				list.appendTag(nbt1);
			}
		}
		
		nbt.setTag("Items", list);
	}
	
	public int[] getAccessibleSlotsFromSide(int i) {
		return i == 0 ? slots_bottom : (i == 1 ? slots_top : slots_side);
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return this.isItemValidForSlot(i, itemstack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return j != 0 || i != 1 || itemstack.getItem() == Items.bucket;
	}
	
	public int getInfusionProgressScaled(int i) {
		return (dualCookTime * i) / this.infusionSpeed;
	}
	
	public int getPowerRemainingScaled(int i) {
		return (dualPower * i) / maxPower;
	}
	
	private boolean canInfuse() {
		if(slots[0]== null || slots[1] == null)	return false;
		
		ItemStack itemstack = OrganicInfuserRecipes.getInfusionResult(slots[0].getItem(), slots[1].getItem());
		
		if(itemstack == null) return false;
		if(slots[3] == null) return true;
		if(!slots[3].isItemEqual(itemstack)) return false; 
		if(slots[3].stackSize < getInventoryStackLimit() && slots[3].stackSize < slots[3].getMaxStackSize()) { 
			return true;
		} else {
			return slots[3].stackSize < itemstack.getMaxStackSize();
		}
	}
	
	private void infuseItem() {
		if(canInfuse()) {
			ItemStack itemstack = OrganicInfuserRecipes.getInfusionResult(slots[0].getItem(), slots[1].getItem());
			
			if(slots[3] == null) {
				slots[3] = itemstack.copy();
			} else if(slots[3].isItemEqual(itemstack)) {
				slots[3].stackSize += itemstack.stackSize;
			}
			
			for(int i = 0; i < 2; i++) {
				if (slots[i].stackSize <= 0 ) {
					slots[i] = new ItemStack(slots[i].getItem().setFull3D());
				} else {
					slots[i].stackSize--;
				}
				
				if(slots[i].stackSize <= 0) {
					slots[i] = null;
				}
			}
		}
	}
	
	public boolean hasPower() {
		return dualPower > 0;
	}
	
	public static boolean isInfusing() {
		return dualCookTime > 0;
	}
	
	public void updateEntity() {
		boolean flag = this.hasPower();
		boolean flag1= false;
		
		if(hasPower() && this.isInfusing()) {
			this.dualPower--;
		}
		
		if(!worldObj.isRemote) {
			if (this.hasItemPower(this.slots[2]) && this.dualPower < (this.maxPower - this.getItemPower(this.slots[2]))) {
				this.dualPower += getItemPower(this.slots[2]);
				
				if(this.slots[2] != null) {
					flag1 = true;
					
					this.slots[2].stackSize--;
					
					if(this.slots[2].stackSize == 0) {
						this.slots[2] = this.slots[2].getItem().getContainerItem(this.slots[2]);
					}
				}
			}
			
			if (hasPower() && canInfuse()) {
				dualCookTime++;
				
				if (this.dualCookTime == this.infusionSpeed) {
					this.dualCookTime = 0;
					this.infuseItem();
					flag1 = true;
				}
			}else{
				dualCookTime = 0;
			}
			
			if (flag != this.isInfusing()) {
				flag1 = true;
				OrganicInfuser.updateBlockState(this.isInfusing(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
		
		if (flag1) {
			this.markDirty();
		}
    }
	
	public static boolean isItemFuel(ItemStack itemstack) {
		return getItemBurnTime(itemstack) > 0;
	}
	
	private static int getItemBurnTime(ItemStack itemstack) {
		if(itemstack == null) return 0;
		else {
			Item item = itemstack.getItem();
			
			if(item == Items.lava_bucket) return 1;
			else if(item == Items.blaze_powder) return 1;
			else return 0;
		}
	}

	public static boolean isOrganicInput(ItemStack itemstack) {
		return getOrganicInput(itemstack) > 0;
	}
	
	private static int getOrganicInput(ItemStack itemstack) {
		if(itemstack == null) return 0;
		else {
			Item item = itemstack.getItem();
			
			if(item == Items.pumpkin_seeds) return 1;
			if(item == Items.melon_seeds) return 1;
			else return 0;
		}
	}
	

	public static boolean isMineralInput(ItemStack itemstack) {
		return geMineralInput(itemstack) > 0;
	}
	
	private static int geMineralInput(ItemStack itemstack) {
		if(itemstack == null) return 0;
		else {
			Item item = itemstack.getItem();
			
			if(item == Item.getItemFromBlock(Blocks.coal_ore)) return 1;
			if(item == Item.getItemFromBlock(Blocks.iron_ore)) return 1;
			if(item == Item.getItemFromBlock(Blocks.gold_ore)) return 1;
			if(item == Item.getItemFromBlock(Blocks.lapis_ore)) return 1;
			if(item == Item.getItemFromBlock(Blocks.redstone_ore)) return 1;
			if(item == Item.getItemFromBlock(Blocks.diamond_ore)) return 1;
			if(item == Item.getItemFromBlock(Blocks.emerald_ore)) return 1;
			else return 0;
		}
	}

	
	
	
}
