package io.github.crystic.oreganics.blocks.vanillastems;

import io.github.crystic.oreganics.Oreganic;
import io.github.crystic.oreganics.blocks.BlockStemGreen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEmeraldStem extends BlockStemGreen
{

    @SideOnly(Side.CLIENT)
    private IIcon stemIcon;

    public BlockEmeraldStem(Block block) {
    	super(block);
    }

    public Item getItemDropped(int i, Random random, int j) {
        return Oreganic.cropEmeraldSeeds;
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