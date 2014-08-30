package io.github.crystic.oreganic.handler;

import io.github.crystic.oreganic.Oreganic;
import io.github.crystic.oreganic.container.ContainerBasicMineralExtractor;
import io.github.crystic.oreganic.container.ContainerBasicOrganicInfuser;
import io.github.crystic.oreganic.container.ContainerHobbyMineralExtractor;
import io.github.crystic.oreganic.container.ContainerHobbyOrganicInfuser;
import io.github.crystic.oreganic.container.ContainerProMineralExtractor;
import io.github.crystic.oreganic.container.ContainerProOrganicInfuser;
import io.github.crystic.oreganic.gui.GuiBasicMineralExtractor;
import io.github.crystic.oreganic.gui.GuiBasicOrganicInfuser;
import io.github.crystic.oreganic.gui.GuiHobbyMineralExtractor;
import io.github.crystic.oreganic.gui.GuiHobbyOrganicInfuser;
import io.github.crystic.oreganic.gui.GuiProMineralExtractor;
import io.github.crystic.oreganic.gui.GuiProOrganicInfuser;
import io.github.crystic.oreganic.tileentity.TileEntityBasicMineralExtractor;
import io.github.crystic.oreganic.tileentity.TileEntityBasicOrganicInfuser;
import io.github.crystic.oreganic.tileentity.TileEntityHobbyMineralExtractor;
import io.github.crystic.oreganic.tileentity.TileEntityHobbyOrganicInfuser;
import io.github.crystic.oreganic.tileentity.TileEntityProMineralExtractor;
import io.github.crystic.oreganic.tileentity.TileEntityProOrganicInfuser;
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
