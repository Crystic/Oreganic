package io.github.crystic.oreganic;

import io.github.crystic.oreganic.blocks.BlockCoalStem;
import io.github.crystic.oreganic.blocks.BlockDiamondStem;
import io.github.crystic.oreganic.blocks.BlockEmeraldStem;
import io.github.crystic.oreganic.blocks.BlockGoldStem;
import io.github.crystic.oreganic.blocks.BlockIronStem;
import io.github.crystic.oreganic.blocks.BlockLapisStem;
import io.github.crystic.oreganic.blocks.BlockRedstoneStem;
import io.github.crystic.oreganic.blocks.MineralExtractor;
import io.github.crystic.oreganic.blocks.OrganicCoal;
import io.github.crystic.oreganic.blocks.OrganicDiamond;
import io.github.crystic.oreganic.blocks.OrganicEmerald;
import io.github.crystic.oreganic.blocks.OrganicGold;
import io.github.crystic.oreganic.blocks.OrganicInfuser;
import io.github.crystic.oreganic.blocks.OrganicIron;
import io.github.crystic.oreganic.blocks.OrganicLapis;
import io.github.crystic.oreganic.blocks.OrganicRedstone;
import io.github.crystic.oreganic.handler.GuiHandler;
import io.github.crystic.oreganic.tileentity.TileEntityMineralExtractor;
import io.github.crystic.oreganic.tileentity.TileEntityOrganicInfuser;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Oreganic.modid, version = Oreganic.version)

public class Oreganic {

	public static final String modid = "Oreganic";
	public static final String version = "R1.0";
	
	public static CreativeTabs oreganicTab;
	@Instance(modid)
	public static Oreganic instance;
	
	public static Block cropCoalStem;
	public static Block cropIronStem;
	public static Block cropGoldStem;
	public static Block cropLapisStem;
	public static Block cropRedstoneStem;
	public static Block cropDiamondStem;
	public static Block cropEmeraldStem;
	
	public static Block cropOrganicCoal;
	public static Block cropOrganicIron;
	public static Block cropOrganicGold;
	public static Block cropOrganicLapis;
	public static Block cropOrganicRedstone;
	public static Block cropOrganicDiamond;
	public static Block cropOrganicEmerald;
	
	public static Block blockOrganicInfuserIdle;
	public static Block blockOrganicInfuserActive;
	public static final int guiIdOrganicInfuser = 1;
	
	public static Block blockMineralExtractorIdle;
	public static Block blockMineralExtractorActive;
	public static final int guiIdMineralExtractor = 2;
	
	public static Item cropCoalSeeds;
	public static Item cropIronSeeds;
	public static Item cropGoldSeeds;
	public static Item cropLapisSeeds;
	public static Item cropRedstoneSeeds;
	public static Item cropDiamondSeeds;
	public static Item cropEmeraldSeeds;
			
	@EventHandler
	public void PreInit(FMLPreInitializationEvent preEvent) {

		oreganicTab = new CreativeTabs("OreGanic") {
			public Item getTabIconItem() {
				return Item.getItemFromBlock(cropOrganicCoal);
			}
		};
		
		cropOrganicCoal = new OrganicCoal().setBlockName("OrganicCoal");
		cropOrganicIron = new OrganicIron().setBlockName("OrganicIron");
		cropOrganicGold = new OrganicGold().setBlockName("OrganicGold");
		cropOrganicLapis = new OrganicLapis().setBlockName("OrganicLapis");
		cropOrganicRedstone = new OrganicRedstone().setBlockName("OrganicRedstone");
		cropOrganicDiamond = new OrganicDiamond().setBlockName("OrganicDiamond");
		cropOrganicEmerald = new OrganicEmerald().setBlockName("OrganicEmerald");
		
		cropCoalStem = new BlockCoalStem(cropOrganicCoal).setBlockName("CoalStem");
		cropIronStem = new BlockIronStem(cropOrganicIron).setBlockName("IronStem");
		cropGoldStem = new BlockGoldStem(cropOrganicGold).setBlockName("GoldStem");
		cropLapisStem = new BlockLapisStem(cropOrganicLapis).setBlockName("LapisStem");
		cropRedstoneStem = new BlockRedstoneStem(cropOrganicRedstone).setBlockName("RedstoneStem");
		cropDiamondStem = new BlockDiamondStem(cropOrganicDiamond).setBlockName("DiamondStem");
		cropEmeraldStem = new BlockEmeraldStem(cropOrganicEmerald).setBlockName("EmeraldStem");
		
		blockOrganicInfuserIdle = new OrganicInfuser(false).setBlockName("OrganicInfuserIdle").setCreativeTab(oreganicTab);
		blockOrganicInfuserActive = new OrganicInfuser(true).setBlockName("OrganicInfuserActive").setLightLevel(0.5F);
		
		blockMineralExtractorIdle = new MineralExtractor(false).setBlockName("MineralExtractorIdle").setCreativeTab(oreganicTab);
		blockMineralExtractorActive = new MineralExtractor(true).setBlockName("MineralExtractorActive").setLightLevel(0.5F);

		cropCoalSeeds = new ItemSeeds(cropCoalStem, Blocks.farmland).setUnlocalizedName("CoalSeeds").setTextureName(modid + ":CoalSeeds").setCreativeTab(oreganicTab);
		cropIronSeeds = new ItemSeeds(cropIronStem, Blocks.farmland).setUnlocalizedName("IronSeeds").setTextureName(modid + ":IronSeeds").setCreativeTab(oreganicTab);
		cropGoldSeeds = new ItemSeeds(cropGoldStem, Blocks.farmland).setUnlocalizedName("GoldSeeds").setTextureName(modid + ":GoldSeeds").setCreativeTab(oreganicTab);
		cropLapisSeeds = new ItemSeeds(cropLapisStem, Blocks.farmland).setUnlocalizedName("LapisSeeds").setTextureName(modid + ":LapisSeeds").setCreativeTab(oreganicTab);
		cropRedstoneSeeds = new ItemSeeds(cropRedstoneStem, Blocks.farmland).setUnlocalizedName("RedstoneSeeds").setTextureName(modid + ":RedstoneSeeds").setCreativeTab(oreganicTab);
		cropDiamondSeeds = new ItemSeeds(cropDiamondStem, Blocks.farmland).setUnlocalizedName("DiamondSeeds").setTextureName(modid + ":DiamondSeeds").setCreativeTab(oreganicTab);
		cropEmeraldSeeds = new ItemSeeds(cropEmeraldStem, Blocks.farmland).setUnlocalizedName("EmeraldSeeds").setTextureName(modid + ":EmeraldSeeds").setCreativeTab(oreganicTab);

		GameRegistry.registerBlock(cropOrganicCoal, "OrganicCoal");
		GameRegistry.registerBlock(cropOrganicIron, "OrganicIron");
		GameRegistry.registerBlock(cropOrganicGold, "OrganicGold");
		GameRegistry.registerBlock(cropOrganicLapis, "OrganicLapis");
		GameRegistry.registerBlock(cropOrganicRedstone, "OrganicRedstone");
		GameRegistry.registerBlock(cropOrganicDiamond, "OrganicDiamond");
		GameRegistry.registerBlock(cropOrganicEmerald, "OrganicEmerald");
		
		GameRegistry.registerBlock(cropCoalStem, "cropCoalStem");
		GameRegistry.registerBlock(cropIronStem, "cropIronStem");
		GameRegistry.registerBlock(cropGoldStem, "cropGoldStem");
		GameRegistry.registerBlock(cropLapisStem, "cropLapisStem");
		GameRegistry.registerBlock(cropRedstoneStem, "cropRedstoneStem");
		GameRegistry.registerBlock(cropDiamondStem, "cropDiamondStem");
		GameRegistry.registerBlock(cropEmeraldStem, "cropEmeraldStem");
		
		GameRegistry.registerBlock(blockOrganicInfuserIdle, "OrganicInfuserIdle");
		GameRegistry.registerBlock(blockOrganicInfuserActive, "OrganicInfuserActive");
		
		GameRegistry.registerBlock(blockMineralExtractorIdle, "MineralExtractorIdle");
		GameRegistry.registerBlock(blockMineralExtractorActive, "MineralExtractorActive");
		
		GameRegistry.registerItem(cropCoalSeeds, "CoalSeeds");
		GameRegistry.registerItem(cropIronSeeds, "IronSeeds");
		GameRegistry.registerItem(cropGoldSeeds, "GoldSeeds");
		GameRegistry.registerItem(cropLapisSeeds, "LapisSeeds");
		GameRegistry.registerItem(cropRedstoneSeeds, "RedstoneSeeds");
		GameRegistry.registerItem(cropDiamondSeeds, "DiamondSeeds");
		GameRegistry.registerItem(cropEmeraldSeeds, "EmeraldSeeds");
	}
	
	@EventHandler
	public void PreInit(FMLInitializationEvent initEvent) {
		
		GameRegistry.registerTileEntity(TileEntityOrganicInfuser.class, "OrganicInfuser");
		GameRegistry.registerTileEntity(TileEntityMineralExtractor.class, "MineralExtractor");
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}
	
	@EventHandler
	public void PreInit(FMLPostInitializationEvent postEvent) {
		
	}

	
}
