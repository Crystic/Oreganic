package io.github.crystic.oreganics;

import io.github.crystic.oreganics.blocks.BasicMineralExtractor;
import io.github.crystic.oreganics.blocks.BasicOrganicInfuser;
import io.github.crystic.oreganics.blocks.HobbyMineralExtractor;
import io.github.crystic.oreganics.blocks.HobbyOrganicInfuser;
import io.github.crystic.oreganics.blocks.ProMineralExtractor;
import io.github.crystic.oreganics.blocks.ProOrganicInfuser;
import io.github.crystic.oreganics.blocks.vanillastems.BlockCoalStem;
import io.github.crystic.oreganics.blocks.vanillastems.BlockDiamondStem;
import io.github.crystic.oreganics.blocks.vanillastems.BlockEmeraldStem;
import io.github.crystic.oreganics.blocks.vanillastems.BlockGoldStem;
import io.github.crystic.oreganics.blocks.vanillastems.BlockIronStem;
import io.github.crystic.oreganics.blocks.vanillastems.BlockLapisStem;
import io.github.crystic.oreganics.blocks.vanillastems.BlockQuartzStem;
import io.github.crystic.oreganics.blocks.vanillastems.BlockRedstoneStem;
import io.github.crystic.oreganics.blocks.vannillaores.BlockOrganicCoal;
import io.github.crystic.oreganics.blocks.vannillaores.BlockOrganicDiamond;
import io.github.crystic.oreganics.blocks.vannillaores.BlockOrganicEmerald;
import io.github.crystic.oreganics.blocks.vannillaores.BlockOrganicGold;
import io.github.crystic.oreganics.blocks.vannillaores.BlockOrganicIron;
import io.github.crystic.oreganics.blocks.vannillaores.BlockOrganicLapis;
import io.github.crystic.oreganics.blocks.vannillaores.BlockOrganicQuartz;
import io.github.crystic.oreganics.blocks.vannillaores.BlockOrganicRedstone;
import io.github.crystic.oreganics.handler.GuiHandler;
import io.github.crystic.oreganics.items.ItemQuartzSeeds;
import io.github.crystic.oreganics.tileentity.TileEntityBasicMineralExtractor;
import io.github.crystic.oreganics.tileentity.TileEntityBasicOrganicInfuser;
import io.github.crystic.oreganics.tileentity.TileEntityHobbyMineralExtractor;
import io.github.crystic.oreganics.tileentity.TileEntityHobbyOrganicInfuser;
import io.github.crystic.oreganics.tileentity.TileEntityProMineralExtractor;
import io.github.crystic.oreganics.tileentity.TileEntityProOrganicInfuser;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
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

	public static final String modid = "oreganic";
	public static final String version = "R1.2";
	
	public static CreativeTabs oreganicTab;
	@Instance(modid)
	public static Oreganic instance;
	
	//======================\\\
	// Vanilla Declarations \\\
	//======================\\\
	
	public static Block blockCoalStem;
	public static Block blockIronStem;
	public static Block blockGoldStem;
	public static Block blockLapisStem;
	public static Block blockRedstoneStem;
	public static Block blockDiamondStem;
	public static Block blockEmeraldStem;
	public static Block blockQuartzStem;
	
	public static Block blockOrganicCoal;
	public static Block blockOrganicIron;
	public static Block blockOrganicGold;
	public static Block blockOrganicLapis;
	public static Block blockOrganicRedstone;
	public static Block blockOrganicDiamond;
	public static Block blockOrganicEmerald;
	public static Block blockOrganicQuartz;
	
	public static Block blockBasicOrganicInfuserIdle;
	public static Block blockBasicOrganicInfuserActive;
	public static final int guiIdBasicOrganicInfuser = 1;
	public static Block blockBasicMineralExtractorIdle;
	public static Block blockBasicMineralExtractorActive;
	public static final int guiIdBasicMineralExtractor = 2;
	public static Block blockHobbyOrganicInfuserIdle;
	public static Block blockHobbyOrganicInfuserActive;
	public static final int guiIdHobbyOrganicInfuser = 3;
	public static Block blockHobbyMineralExtractorIdle;
	public static Block blockHobbyMineralExtractorActive;
	public static final int guiIdHobbyMineralExtractor = 4;
	public static Block blockProOrganicInfuserIdle;
	public static Block blockProOrganicInfuserActive;
	public static final int guiIdProOrganicInfuser = 5;
	public static Block blockProMineralExtractorIdle;
	public static Block blockProMineralExtractorActive;
	public static final int guiIdProMineralExtractor = 6;
	
	public static Item cropCoalSeeds;
	public static Item cropIronSeeds;
	public static Item cropGoldSeeds;
	public static Item cropLapisSeeds;
	public static Item cropRedstoneSeeds;
	public static Item cropDiamondSeeds;
	public static Item cropEmeraldSeeds;
	public static Item cropQuartzSeeds;
	
	//================\\\
	// End of vanilla \\\
	//================\\\
		
	@EventHandler
	public void PreInit(FMLPreInitializationEvent preEvent) {
		oreganicTab = new CreativeTabs("OreGanic") {
			public Item getTabIconItem() {
				return Item.getItemFromBlock(blockOrganicCoal);
			}
		};
		
		//=========================\\\
		// Vanilla Initialisations \\\
		//=========================\\\
			
		blockOrganicCoal = new BlockOrganicCoal().setBlockName("OrganicCoal");
		blockOrganicIron = new BlockOrganicIron().setBlockName("OrganicIron");
		blockOrganicGold = new BlockOrganicGold().setBlockName("OrganicGold");
		blockOrganicLapis = new BlockOrganicLapis().setBlockName("OrganicLapis");
		blockOrganicRedstone = new BlockOrganicRedstone().setBlockName("OrganicRedstone");
		blockOrganicDiamond = new BlockOrganicDiamond().setBlockName("OrganicDiamond");
		blockOrganicEmerald = new BlockOrganicEmerald().setBlockName("OrganicEmerald");
		blockOrganicQuartz = new BlockOrganicQuartz().setBlockName("OrganicQuartz");
		
		blockCoalStem = new BlockCoalStem(blockOrganicCoal).setBlockName("CoalStem");
		blockIronStem = new BlockIronStem(blockOrganicIron).setBlockName("IronStem");
		blockGoldStem = new BlockGoldStem(blockOrganicGold).setBlockName("GoldStem");
		blockLapisStem = new BlockLapisStem(blockOrganicLapis).setBlockName("LapisStem");
		blockRedstoneStem = new BlockRedstoneStem(blockOrganicRedstone).setBlockName("RedstoneStem");
		blockDiamondStem = new BlockDiamondStem(blockOrganicDiamond).setBlockName("DiamondStem");
		blockEmeraldStem = new BlockEmeraldStem(blockOrganicEmerald).setBlockName("EmeraldStem");
		blockQuartzStem = new BlockQuartzStem(blockOrganicQuartz).setBlockName("QuartzStem");
		
		blockBasicOrganicInfuserIdle = new BasicOrganicInfuser(false).setBlockName("BasicOrganicInfuserIdle").setCreativeTab(oreganicTab);
		blockBasicOrganicInfuserActive = new BasicOrganicInfuser(true).setBlockName("BasicOrganicInfuserActive").setLightLevel(0.5F);
		blockBasicMineralExtractorIdle = new BasicMineralExtractor(false).setBlockName("BasicMineralExtractorIdle").setCreativeTab(oreganicTab);
		blockBasicMineralExtractorActive = new BasicMineralExtractor(true).setBlockName("BasicMineralExtractorActive").setLightLevel(0.5F);
		blockHobbyOrganicInfuserIdle = new HobbyOrganicInfuser(false).setBlockName("HobbyOrganicInfuserIdle").setCreativeTab(oreganicTab);
		blockHobbyOrganicInfuserActive = new HobbyOrganicInfuser(true).setBlockName("HobbyOrganicInfuserActive").setLightLevel(0.5F);
		blockHobbyMineralExtractorIdle = new HobbyMineralExtractor(false).setBlockName("HobbyMineralExtractorIdle").setCreativeTab(oreganicTab);
		blockHobbyMineralExtractorActive = new HobbyMineralExtractor(true).setBlockName("HobbyMineralExtractorActive").setLightLevel(0.5F);
		blockProOrganicInfuserIdle = new ProOrganicInfuser(false).setBlockName("ProOrganicInfuserIdle").setCreativeTab(oreganicTab);
		blockProOrganicInfuserActive = new ProOrganicInfuser(true).setBlockName("ProOrganicInfuserActive").setLightLevel(0.5F);
		blockProMineralExtractorIdle = new ProMineralExtractor(false).setBlockName("ProMineralExtractorIdle").setCreativeTab(oreganicTab);
		blockProMineralExtractorActive = new ProMineralExtractor(true).setBlockName("ProMineralExtractorActive").setLightLevel(0.5F);
		
		cropCoalSeeds = new ItemSeeds(blockCoalStem, Blocks.farmland).setUnlocalizedName("CoalSeeds").setTextureName(modid + ":CoalSeeds").setCreativeTab(oreganicTab);
		cropIronSeeds = new ItemSeeds(blockIronStem, Blocks.farmland).setUnlocalizedName("IronSeeds").setTextureName(modid + ":IronSeeds").setCreativeTab(oreganicTab);
		cropGoldSeeds = new ItemSeeds(blockGoldStem, Blocks.farmland).setUnlocalizedName("GoldSeeds").setTextureName(modid + ":GoldSeeds").setCreativeTab(oreganicTab);
		cropLapisSeeds = new ItemSeeds(blockLapisStem, Blocks.farmland).setUnlocalizedName("LapisSeeds").setTextureName(modid + ":LapisSeeds").setCreativeTab(oreganicTab);
		cropRedstoneSeeds = new ItemSeeds(blockRedstoneStem, Blocks.farmland).setUnlocalizedName("RedstoneSeeds").setTextureName(modid + ":RedstoneSeeds").setCreativeTab(oreganicTab);
		cropDiamondSeeds = new ItemSeeds(blockDiamondStem, Blocks.farmland).setUnlocalizedName("DiamondSeeds").setTextureName(modid + ":DiamondSeeds").setCreativeTab(oreganicTab);
		cropEmeraldSeeds = new ItemSeeds(blockEmeraldStem, Blocks.farmland).setUnlocalizedName("EmeraldSeeds").setTextureName(modid + ":EmeraldSeeds").setCreativeTab(oreganicTab);
		cropQuartzSeeds = new ItemQuartzSeeds(blockQuartzStem, Blocks.soul_sand).setUnlocalizedName("QuartzSeeds").setTextureName(modid + ":QuartzSeeds").setCreativeTab(oreganicTab);
	
		//=======================\\\
		// Vanilla Registrations \\\
		//=======================\\\
		
		GameRegistry.registerBlock(blockOrganicCoal, "OrganicCoal");
		GameRegistry.registerBlock(blockOrganicIron, "OrganicIron");
		GameRegistry.registerBlock(blockOrganicGold, "OrganicGold");
		GameRegistry.registerBlock(blockOrganicLapis, "OrganicLapis");
		GameRegistry.registerBlock(blockOrganicRedstone, "OrganicRedstone");
		GameRegistry.registerBlock(blockOrganicDiamond, "OrganicDiamond");
		GameRegistry.registerBlock(blockOrganicEmerald, "OrganicEmerald");
		GameRegistry.registerBlock(blockOrganicQuartz, "OrganicQuartz");

		GameRegistry.registerBlock(blockCoalStem, "cropCoalStem");
		GameRegistry.registerBlock(blockIronStem, "cropIronStem");
		GameRegistry.registerBlock(blockGoldStem, "cropGoldStem");
		GameRegistry.registerBlock(blockLapisStem, "cropLapisStem");
		GameRegistry.registerBlock(blockRedstoneStem, "cropRedstoneStem");
		GameRegistry.registerBlock(blockDiamondStem, "cropDiamondStem");
		GameRegistry.registerBlock(blockEmeraldStem, "cropEmeraldStem");
		GameRegistry.registerBlock(blockQuartzStem, "cropQuartzStem");
		
		GameRegistry.registerBlock(blockBasicOrganicInfuserIdle, "BasicOrganicInfuserIdle");
		GameRegistry.registerBlock(blockBasicOrganicInfuserActive, "BasicOrganicInfuserActive");
		GameRegistry.registerBlock(blockBasicMineralExtractorIdle, "BasicMineralExtractorIdle");
		GameRegistry.registerBlock(blockBasicMineralExtractorActive, "BasicMineralExtractorActive");	
		GameRegistry.registerBlock(blockHobbyOrganicInfuserIdle, "HobbyOrganicInfuserIdle");
		GameRegistry.registerBlock(blockHobbyOrganicInfuserActive, "HobbyOrganicInfuserActive");
		GameRegistry.registerBlock(blockHobbyMineralExtractorIdle, "HobbyMineralExtractorIdle");
		GameRegistry.registerBlock(blockHobbyMineralExtractorActive, "HobbyMineralExtractorActive");
		GameRegistry.registerBlock(blockProOrganicInfuserIdle, "ProOrganicInfuserIdle");
		GameRegistry.registerBlock(blockProOrganicInfuserActive, "ProOrganicInfuserActive");
		GameRegistry.registerBlock(blockProMineralExtractorIdle, "ProMineralExtractorIdle");
		GameRegistry.registerBlock(blockProMineralExtractorActive, "ProMineralExtractorActive");
		
		GameRegistry.registerItem(cropCoalSeeds, "CoalSeeds");
		GameRegistry.registerItem(cropIronSeeds, "IronSeeds");
		GameRegistry.registerItem(cropGoldSeeds, "GoldSeeds");
		GameRegistry.registerItem(cropLapisSeeds, "LapisSeeds");
		GameRegistry.registerItem(cropRedstoneSeeds, "RedstoneSeeds");
		GameRegistry.registerItem(cropDiamondSeeds, "DiamondSeeds");
		GameRegistry.registerItem(cropEmeraldSeeds, "EmeraldSeeds");
		GameRegistry.registerItem(cropQuartzSeeds, "QuartzSeeds");
		
		//================\\\
		// End of vanilla \\\
		//================\\\
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent initEvent) {
		
		GameRegistry.registerTileEntity(TileEntityBasicOrganicInfuser.class, "BasicOrganicInfuser");
		GameRegistry.registerTileEntity(TileEntityBasicMineralExtractor.class, "BasicMineralExtractor");
		GameRegistry.registerTileEntity(TileEntityHobbyOrganicInfuser.class, "HobbyOrganicInfuser");
		GameRegistry.registerTileEntity(TileEntityHobbyMineralExtractor.class, "HobbyMineralExtractor");
		GameRegistry.registerTileEntity(TileEntityProOrganicInfuser.class, "ProOrganicInfuser");
		GameRegistry.registerTileEntity(TileEntityProMineralExtractor.class, "ProMineralExtractor");
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		GameRegistry.addShapedRecipe(new ItemStack(blockBasicOrganicInfuserIdle), new Object[]{"BBB", "GFG", "BBB", 'B', Blocks.cobblestone, 'G', Blocks.glass_pane, 'F', Blocks.furnace});
		GameRegistry.addShapedRecipe(new ItemStack(blockBasicMineralExtractorIdle), new Object[]{"BGB", "BIB", "BBB", 'B', Blocks.cobblestone, 'G', Blocks.glass_pane, 'I', Blocks.iron_block,});
		GameRegistry.addShapedRecipe(new ItemStack(blockHobbyOrganicInfuserIdle), new Object[]{"BBB", "GFG", "BBB", 'B', Items.iron_ingot, 'G', Blocks.glass_pane, 'F', Blocks.furnace});
		GameRegistry.addShapedRecipe(new ItemStack(blockHobbyMineralExtractorIdle), new Object[]{"BGB", "BIB", "BBB", 'B', Items.iron_ingot, 'G', Blocks.glass_pane, 'I', Blocks.iron_block});
		GameRegistry.addShapedRecipe(new ItemStack(blockProOrganicInfuserIdle), new Object[]{"BBB", "GFG", "BBB", 'B', Blocks.quartz_block, 'G', Blocks.glass_pane, 'F', Blocks.furnace});
		GameRegistry.addShapedRecipe(new ItemStack(blockProMineralExtractorIdle), new Object[]{"BGB", "BIB", "BBB", 'B', Blocks.quartz_block, 'G', Blocks.glass_pane, 'I', Blocks.iron_block});
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent postEvent) {
		
	}

	
}
