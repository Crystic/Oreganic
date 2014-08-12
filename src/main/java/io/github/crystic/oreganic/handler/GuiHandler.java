package io.github.crystic.oreganic.handler;

import io.github.crystic.oreganic.Oreganic;
import io.github.crystic.oreganic.container.ContainerMineralExtractor;
import io.github.crystic.oreganic.container.ContainerOrganicInfuser;
import io.github.crystic.oreganic.gui.GuiMineralExtractor;
import io.github.crystic.oreganic.gui.GuiOrganicInfuser;
import io.github.crystic.oreganic.tileentity.TileEntityMineralExtractor;
import io.github.crystic.oreganic.tileentity.TileEntityOrganicInfuser;
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
			case Oreganic.guiIdOrganicInfuser:
				if (entity instanceof TileEntityOrganicInfuser) {
					return new ContainerOrganicInfuser(player.inventory, (TileEntityOrganicInfuser) entity);
				}
			case Oreganic.guiIdMineralExtractor:
				if (entity instanceof TileEntityMineralExtractor) {
					return new ContainerMineralExtractor(player.inventory, (TileEntityMineralExtractor) entity);
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
			case Oreganic.guiIdOrganicInfuser:
				if (entity instanceof TileEntityOrganicInfuser) {
					return new GuiOrganicInfuser(player.inventory, (TileEntityOrganicInfuser) entity);
				}
			case Oreganic.guiIdMineralExtractor:
				if (entity instanceof TileEntityMineralExtractor) {
					return new GuiMineralExtractor(player.inventory, (TileEntityMineralExtractor) entity);
				}
				return null;
			}
		}
		return null;
	}

}
