package io.github.crystic.oreganics.gui;

import io.github.crystic.oreganics.Oreganic;
import io.github.crystic.oreganics.container.ContainerBasicOrganicInfuser;
import io.github.crystic.oreganics.tileentity.TileEntityBasicOrganicInfuser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiBasicOrganicInfuser extends GuiContainer {
	
	public ResourceLocation texture = new ResourceLocation(Oreganic.modid + ":" + "textures/gui/organicinfuser.png");
	public TileEntityBasicOrganicInfuser basicOrganicInfuser;

	public GuiBasicOrganicInfuser(InventoryPlayer invPlayer, TileEntityBasicOrganicInfuser teBasicOrganicInfuser) {
		super(new ContainerBasicOrganicInfuser(invPlayer, teBasicOrganicInfuser));
		basicOrganicInfuser = teBasicOrganicInfuser;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		String name = "Basic Organic Infuser";
		
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 4, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if (basicOrganicInfuser.hasPower())
        {
            int i1 = basicOrganicInfuser.getPowerRemainingScaled(37);
            drawTexturedModalRect(guiLeft + 17, guiTop + 49 - i1, 176, 166 - i1, 16, i1);
        }

        int i1 = basicOrganicInfuser.getInfusionProgressScaled(34);
        drawTexturedModalRect(guiLeft + 65, guiTop + 15, 176, 0, i1, 56);
    }

}
