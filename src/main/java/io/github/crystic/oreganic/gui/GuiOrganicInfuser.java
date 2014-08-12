package io.github.crystic.oreganic.gui;

import org.lwjgl.opengl.GL11;

import io.github.crystic.oreganic.Oreganic;
import io.github.crystic.oreganic.container.ContainerOrganicInfuser;
import io.github.crystic.oreganic.tileentity.TileEntityOrganicInfuser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiOrganicInfuser extends GuiContainer{

	private ResourceLocation texture = new ResourceLocation(Oreganic.modid + ":" + "/textures/gui/organicinfuser.png");
	private TileEntityOrganicInfuser organicInfuser;
	
	
	public GuiOrganicInfuser(InventoryPlayer invPlayer, TileEntityOrganicInfuser teOrganicInfuser) {
		super(new ContainerOrganicInfuser(invPlayer, teOrganicInfuser));
		organicInfuser = teOrganicInfuser;
		this.xSize = 176;
		this.ySize = 166;
	}
	
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		String name = this.organicInfuser.hasCustomInventoryName() ? this.organicInfuser.getInventoryName() : I18n.format(this.organicInfuser.getInventoryName());
		this.fontRendererObj.drawString(name, this.xSize/2 - this.fontRendererObj.getStringWidth(name)/2, 4, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize-96 + 2, 4210752);
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
        if (organicInfuser.hasPower())
        {
            int i1 = organicInfuser.getPowerRemainingScaled(46);
            drawTexturedModalRect(guiLeft + 17, guiTop + 49 - i1, 176, 166 - i1, 16, i1);
        }
        
        int j1 = organicInfuser.getInfusionProgressScaled(34);
        drawTexturedModalRect(guiLeft + 65, guiTop + 15, 176, 0, j1, 56);
	}

}