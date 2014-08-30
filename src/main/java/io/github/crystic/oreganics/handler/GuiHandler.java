package io.github.crystic.oreganics.handler;

import io.github.crystic.oreganics.Oreganic;
import io.github.crystic.oreganics.container.ContainerBasicMineralExtractor;
import io.github.crystic.oreganics.container.ContainerBasicOrganicInfuser;
import io.github.crystic.oreganics.container.ContainerHobbyMineralExtractor;
import io.github.crystic.oreganics.container.ContainerHobbyOrganicInfuser;
import io.github.crystic.oreganics.container.ContainerProMineralExtractor;
import io.github.crystic.oreganics.container.ContainerProOrganicInfuser;
import io.github.crystic.oreganics.gui.GuiBasicMineralExtractor;
import io.github.crystic.oreganics.gui.GuiBasicOrganicInfuser;
import io.github.crystic.oreganics.gui.GuiHobbyMineralExtractor;
import io.github.crystic.oreganics.gui.GuiHobbyOrganicInfuser;
import io.github.crystic.oreganics.gui.GuiProMineralExtractor;
import io.github.crystic.oreganics.gui.GuiProOrganicInfuser;
import io.github.crystic.oreganics.tileentity.TileEntityBasicMineralExtractor;
import io.github.crystic.oreganics.tileentity.TileEntityBasicOrganicInfuser;
import io.github.crystic.oreganics.tileentity.TileEntityHobbyMineralExtractor;
import io.github.crystic.oreganics.tileentity.TileEntityHobbyOrganicInfuser;
import io.github.crystic.oreganics.tileentity.TileEntityProMineralExtractor;
import io.github.crystic.oreganics.tileentity.TileEntityProOrganicInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		
		if(entity != null) {
			switch(ID) {
			case Oreganic.guiIdBasicOrganicInfuser:
				if (entity instanceof TileEntityBasicOrganicInfuser) {
					return new ContainerBasicOrganicInfuser(player.inventory, (TileEntityBasicOrganicInfuser) entity);
				}
			case Oreganic.guiIdBasicMineralExtractor:
				if (entity instanceof TileEntityBasicMineralExtractor) {
					return new ContainerBasicMineralExtractor(player.inventory, (TileEntityBasicMineralExtractor) entity);
				}
			case Oreganic.guiIdHobbyOrganicInfuser:
				if (entity instanceof TileEntityHobbyOrganicInfuser) {
					return new ContainerHobbyOrganicInfuser(player.inventory, (TileEntityHobbyOrganicInfuser) entity);
				}
			case Oreganic.guiIdHobbyMineralExtractor:
				if (entity instanceof TileEntityHobbyMineralExtractor) {
					return new ContainerHobbyMineralExtractor(player.inventory, (TileEntityHobbyMineralExtractor) entity);
				}	
			case Oreganic.guiIdProOrganicInfuser:
				if (entity instanceof TileEntityProOrganicInfuser) {
					return new ContainerProOrganicInfuser(player.inventory, (TileEntityProOrganicInfuser) entity);
				}
			case Oreganic.guiIdProMineralExtractor:
				if (entity instanceof TileEntityProMineralExtractor) {
					return new ContainerProMineralExtractor(player.inventory, (TileEntityProMineralExtractor) entity);
				}	
				return null;
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		
		if(entity != null) {
			switch(ID) {
			case Oreganic.guiIdBasicOrganicInfuser:
				if (entity instanceof TileEntityBasicOrganicInfuser) {
					return new GuiBasicOrganicInfuser(player.inventory, (TileEntityBasicOrganicInfuser) entity);
				}
			case Oreganic.guiIdBasicMineralExtractor:
				if (entity instanceof TileEntityBasicMineralExtractor) {
					return new GuiBasicMineralExtractor(player.inventory, (TileEntityBasicMineralExtractor) entity);
				}
			case Oreganic.guiIdHobbyOrganicInfuser:
				if (entity instanceof TileEntityHobbyOrganicInfuser) {
					return new GuiHobbyOrganicInfuser(player.inventory, (TileEntityHobbyOrganicInfuser) entity);
				}
			case Oreganic.guiIdHobbyMineralExtractor:
				if (entity instanceof TileEntityHobbyMineralExtractor) {
					return new GuiHobbyMineralExtractor(player.inventory, (TileEntityHobbyMineralExtractor) entity);
				}
			case Oreganic.guiIdProOrganicInfuser:
				if (entity instanceof TileEntityProOrganicInfuser) {
					return new GuiProOrganicInfuser(player.inventory, (TileEntityProOrganicInfuser) entity);
				}
			case Oreganic.guiIdProMineralExtractor:
				if (entity instanceof TileEntityProMineralExtractor) {
					return new GuiProMineralExtractor(player.inventory, (TileEntityProMineralExtractor) entity);
				}	
				return null;
			}
		}
		return null;
	}

}
