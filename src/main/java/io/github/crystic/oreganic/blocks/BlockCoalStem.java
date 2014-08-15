package io.github.crystic.oreganic.blocks;

import static net.minecraftforge.common.util.ForgeDirection.UP;
import io.github.crystic.oreganic.Oreganic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCoalStem extends BlockStem
{
    private final Block block;
    private Block blockPlantedOn = Blocks.farmland;
    @SideOnly(Side.CLIENT)
    private IIcon stemIcon;
	private Random rand = new Random();

    public BlockCoalStem(Block block)
    {
    	super(block);
        this.block = block;
        this.setTickRandomly(true);
        float f = 0.125F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.setCreativeTab((CreativeTabs)null);
    }

    protected boolean canPlaceBlockOn(Block block)
    {
        return block == Blocks.farmland;
    }

    public void updateTick(World world, int x, int y, int z, Random random)
    {
        super.updateTick(world, x, y, z, random);

        if (world.getBlockLightValue(x, y + 1, z) >= 9)
        {
            float f = this.func_149875_n(world, x, y, z);

            if (random.nextInt((int)(5.0F / f) + 1) == 0)
            {
                int l = world.getBlockMetadata(x, y, z);

                if (l < 7)
                {
                    ++l;
                    world.setBlockMetadataWithNotify(x, y, z, l, 2);
                }
                else
                {
                    if (world.getBlock(x - 1, y, z) == this.block)
                    {
                        return;
                    }

                    if (world.getBlock(x + 1, y, z) == this.block)
                    {
                        return;
                    }

                    if (world.getBlock(x, y, z - 1) == this.block)
                    {
                        return;
                    }

                    if (world.getBlock(x, y, z + 1) == this.block)
                    {
                        return;
                    }

                    int i1 = random.nextInt(4);
                    int j1 = x;
                    int k1 = z;

                    if (i1 == 0)
                    {
                        j1 = x - 1;
                    }

                    if (i1 == 1)
                    {
                        ++j1;
                    }

                    if (i1 == 2)
                    {
                        k1 = z - 1;
                    }

                    if (i1 == 3)
                    {
                        ++k1;
                    }

                    Block block = world.getBlock(j1, y - 1, k1);

                    if (world.isAirBlock(j1, y, k1) && (block.canSustainPlant(world, j1, y - 1, k1, UP, this) || block == Blocks.dirt || block == Blocks.grass))
                    {
                        world.setBlock(j1, y, k1, this.block);
                    }
                }
            }
        }
    }

    private float func_149875_n(World p_149875_1_, int p_149875_2_, int p_149875_3_, int p_149875_4_)
    {
        float f = 1.0F;
        Block block = p_149875_1_.getBlock(p_149875_2_, p_149875_3_, p_149875_4_ - 1);
        Block block1 = p_149875_1_.getBlock(p_149875_2_, p_149875_3_, p_149875_4_ + 1);
        Block block2 = p_149875_1_.getBlock(p_149875_2_ - 1, p_149875_3_, p_149875_4_);
        Block block3 = p_149875_1_.getBlock(p_149875_2_ + 1, p_149875_3_, p_149875_4_);
        Block block4 = p_149875_1_.getBlock(p_149875_2_ - 1, p_149875_3_, p_149875_4_ - 1);
        Block block5 = p_149875_1_.getBlock(p_149875_2_ + 1, p_149875_3_, p_149875_4_ - 1);
        Block block6 = p_149875_1_.getBlock(p_149875_2_ + 1, p_149875_3_, p_149875_4_ + 1);
        Block block7 = p_149875_1_.getBlock(p_149875_2_ - 1, p_149875_3_, p_149875_4_ + 1);
        boolean flag = block2 == this || block3 == this;
        boolean flag1 = block == this || block1 == this;
        boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;

        for (int l = p_149875_2_ - 1; l <= p_149875_2_ + 1; ++l)
        {
            for (int i1 = p_149875_4_ - 1; i1 <= p_149875_4_ + 1; ++i1)
            {
                Block block8 = p_149875_1_.getBlock(l, p_149875_3_ - 1, i1);
                float f1 = 0.0F;

                if (block8.canSustainPlant(p_149875_1_, l, p_149875_3_ - 1, i1, UP, this))
                {
                    f1 = 1.0F;

                    if (block8.isFertile(p_149875_1_, l, p_149875_3_ - 1, i1))
                    {
                        f1 = 3.0F;
                    }
                }

                if (l != p_149875_2_ || i1 != p_149875_4_)
                {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        if (flag2 || flag && flag1)
        {
            f /= 2.0F;
        }

        return f;
    }

    public void setBlockBoundsForItemRender()
    {
        float f = 0.125F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
    {
        this.maxY = (double)((float)(p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_) * 2 + 2) / 16.0F);
        float f = 0.125F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, (float)this.maxY, 0.5F + f);
    }

    public int getRenderType()
    {
        return 19;
    }

    @SideOnly(Side.CLIENT)
    public int getState(IBlockAccess p_149873_1_, int p_149873_2_, int p_149873_3_, int p_149873_4_)
    {
        int l = p_149873_1_.getBlockMetadata(p_149873_2_, p_149873_3_, p_149873_4_);
        return l < 7 ? -1 : (p_149873_1_.getBlock(p_149873_2_ - 1, p_149873_3_, p_149873_4_) == this.block ? 0 : (p_149873_1_.getBlock(p_149873_2_ + 1, p_149873_3_, p_149873_4_) == this.block ? 1 : (p_149873_1_.getBlock(p_149873_2_, p_149873_3_, p_149873_4_ - 1) == this.block ? 2 : (p_149873_1_.getBlock(p_149873_2_, p_149873_3_, p_149873_4_ + 1) == this.block ? 3 : -1))));
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Oreganic.cropCoalSeeds;
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(Oreganic.modid + ":" +  "Stem" + "_disconnected");
        this.stemIcon = iconRegister.registerIcon(Oreganic.modid + ":" + "Stem" + "_connected");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getStemIcon()
    {
        return this.stemIcon;
    }
    
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int i)
    {
    	int h = 124;
    	int s = 71;
    	int v = 25;
        return v << 16 | h << 8 | s;
    }
    
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess iBlockAccess, int x, int y, int z)
    {
        return this.getRenderColor(iBlockAccess.getBlockMetadata(x, y, z));
    }
    
    @Override
    public boolean canBlockStay (World world, int x, int y, int z) {
    	if(world.getBlock(x, y-1, z) != this.blockPlantedOn) {
    		ItemStack itemstack = new ItemStack(Oreganic.cropCoalSeeds);
			float f = this.rand.nextFloat() * 0.8F + 0.1F;
			float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
			float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
			int j = itemstack.stackSize;
    		EntityItem item = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));	
    		world.spawnEntityInWorld(item);   		
    		return false;
    	}
    	else {
    		return true;
    	}
    
    }
}