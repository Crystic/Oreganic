package io.github.crystic.oreganic.blocks;

import io.github.crystic.oreganic.Oreganic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class OrganicDiamond extends Block
{
    public OrganicDiamond()
    {
        super(Material.rock);
        this.setCreativeTab(Oreganic.oreganicTab);
        this.setStepSound(soundTypeStone);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setHarvestLevel("pickaxe", 2);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
    	this.blockIcon = iconRegister.registerIcon(Oreganic.modid + ":" + this.getUnlocalizedName().substring(5));
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return blockIcon;
    }

    public Item getItemDropped(int meta, Random random, int fortune)
    {
        return Item.getItemFromBlock(Oreganic.cropOrganicDiamond);
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        int j = this.quantityDropped(random) + random.nextInt(1 + fortune);

        if (j > 9)
        {
            j = 9;
        }

        return j;
    }
}