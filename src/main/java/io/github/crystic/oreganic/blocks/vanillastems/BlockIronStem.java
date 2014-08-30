package io.github.crystic.oreganic.blocks.vanillastems;

import io.github.crystic.oreganic.Oreganic;
import io.github.crystic.oreganic.blocks.BlockStemGreen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockIronStem extends BlockStemGreen
{

    @SideOnly(Side.CLIENT)
    private IIcon stemIcon;

    public BlockIronStem(Block block) {
    	super(block);
    }

    public Item getItemDropped(int i, Random random, int j) {
        return Oreganic.cropIronSeeds;
    }

    public int quantityDropped(Random random) {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(Oreganic.modid + ":" +  "Stem" + "_disconnected");
        this.stemIcon = iconRegister.registerIcon(Oreganic.modid + ":" + "Stem" + "_connected");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getStemIcon() {
        return this.stemIcon;
    }
}