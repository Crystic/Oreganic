package io.github.crystic.oreganic.blocks;

import io.github.crystic.oreganic.Oreganic;
import io.github.crystic.oreganic.tileentity.TileEntityOrganicInfuser;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class OrganicInfuser extends BlockContainer{

	private Random rand;
	private final boolean isActive;
	private static boolean keepInventory = true;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	@SideOnly(Side.CLIENT)
	private IIcon iconTopBottom;
	
	
	public OrganicInfuser(boolean isActive) {
		super(Material.iron);
		rand = new Random();
		this.isActive = isActive;
		this.setHardness(5.0F);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.iconFront = iconRegister.registerIcon(Oreganic.modid + ":" + (this.isActive ? "OrganicInfuserFrontOn" : "OrganicInfuserFrontOff"));
		this.blockIcon = iconRegister.registerIcon(Oreganic.modid + ":" + "OrganicInfuserSide");
		this.iconTopBottom = iconRegister.registerIcon(Oreganic.modid + ":" + "OrganicInfuserTopBottom");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return meta == 0 && side == 3 ? this.iconFront : side == 1 ? this.iconTopBottom : (side == 0 ? this.iconTopBottom : (side == meta ? this.iconFront : this.blockIcon));		
	}
	
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}
	
	private void setDefaultDirection(World world, int x, int y, int z) {
		if(!world.isRemote) {
			Block block1 = world.getBlock(x, y, z-1);
			Block block2 = world.getBlock(x, y, z+1);
			Block block3 = world.getBlock(x-1, y, z);
			Block block4 = world.getBlock(x+1, y, z);
			
			byte b0 = 3;
			
			if(block1.func_149730_j() && !block2.func_149730_j()) {
				b0 = 3;
			}
			
			if(block2.func_149730_j() && !block1.func_149730_j()) {
				b0 = 2;
			}
			
			if(block3.func_149730_j() && !block4.func_149730_j()) {
				b0 = 5;
			}
			
			if(block4.func_149730_j() && !block3.func_149730_j()) {
				b0 = 4;
			}
			
			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityPlayer, ItemStack itemstack) {
		int i = MathHelper.floor_double((double)(entityPlayer.rotationYaw * 4.0F / 360F) + 0.5D) & 3;
		
		if(i == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}
		
		if(i == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}
		
		if(i == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}
		
		if(i == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}
		
		if(itemstack.hasDisplayName()) {
			//((TileEntityOrganicInfuser) world.getTileEntity(x, y, z)).setCustomName(itemstack.getDisplayName());
		}
	}
	
	public boolean onBlockActivated(World world,  int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    	if (world.isRemote) {
    		return true;
    	}else if (!player.isSneaking()) {
    		TileEntityOrganicInfuser entity = (TileEntityOrganicInfuser) world.getTileEntity(x, y, z);
    		if (entity != null) {
    			FMLNetworkHandler.openGui(player, Oreganic.instance, Oreganic.guiIdOrganicInfuser, world, x, y, z);
    		}
    		return true;
    	}else{
    		return false;
    	}
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityOrganicInfuser();
	}

	public static void updateBlockState(boolean isMashing, World world, int xCoord, int yCoord, int zCoord) {
		
		int i = world.getBlockMetadata(xCoord, yCoord, zCoord);
		TileEntity entity = world.getTileEntity(xCoord, yCoord, zCoord);
		keepInventory = true;
		
		if (isMashing) {
			world.setBlock(xCoord, yCoord, zCoord, Oreganic.blockOrganicInfuserActive);
		}else{
			world.setBlock(xCoord, yCoord, zCoord, Oreganic.blockOrganicInfuserIdle);
		}
		
		keepInventory = false;
		world.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);
		
		if (entity != null) {
			entity.validate();
			world.setTileEntity(xCoord, yCoord, zCoord, entity);
		}
	}
	
	public void breakBlock(World world, int x, int y, int z, Block oldblock,
			int oldMetadata) {
		if (!keepInventory) {
			TileEntityOrganicInfuser tileentity = (TileEntityOrganicInfuser) world
					.getTileEntity(x, y, z);

			if (tileentity != null) {
				for (int i = 0; i < tileentity.getSizeInventory(); i++) {
					ItemStack itemstack = tileentity.getStackInSlot(i);

					if (itemstack != null) {
						float f = this.rand.nextFloat() * 0.8F + 0.1F;
						float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
						float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

						while (itemstack.stackSize > 0) {
							int j = this.rand.nextInt(21) + 10;

							if (j > itemstack.stackSize) {
								j = itemstack.stackSize;
							}

							itemstack.stackSize -= j;

							EntityItem item = new EntityItem(world,
									(double) ((float) x + f),
									(double) ((float) y + f1),
									(double) ((float) z + f2), new ItemStack(
											itemstack.getItem(), j,
											itemstack.getItemDamage()));

							if (itemstack.hasTagCompound()) {
								item.getEntityItem().setTagCompound(
										(NBTTagCompound) itemstack
												.getTagCompound().copy());
							}

							world.spawnEntityInWorld(item);
						}
					}
				}

				world.func_147453_f(x, y, z, oldblock);
			}
		}

		super.breakBlock(world, x, y, z, oldblock, oldMetadata);
	}

	public Item getItem(World world, int x, int y, int z) {
		return Item.getItemFromBlock(Oreganic.blockOrganicInfuserIdle);
	}
}
