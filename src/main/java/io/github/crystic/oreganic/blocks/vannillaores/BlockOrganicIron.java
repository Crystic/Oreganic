package io.github.crystic.oreganic.blocks.vannillaores;

import io.github.crystic.oreganic.Oreganic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOrganicIron extends Block {
	public BlockOrganicIron() {
		super(Material.rock);
		this.setCreativeTab(Oreganic.oreganicTab);
		this.setStepSound(soundTypeStone);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setHarvestLevel("pickaxe", 1);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(Oreganic.modid + ":"
				+ this.getUnlocalizedName().substring(5));
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return blockIcon;
	}

	public Item getItemDropped(int meta, Random random, int fortune) {
		return Item.getItemFromBlock(Oreganic.blockOrganicIron);
	}

	public int quantityDropped(Random random) {
		return 1;
	}
}