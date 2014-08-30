package io.github.crystic.oreganic.gui;

import io.github.crystic.oreganic.Oreganic;
import io.github.crystic.oreganic.container.ContainerProOrganicInfuser;
import io.github.crystic.oreganic.tileentity.TileEntityProOrganicInfuser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiProOrganicInfuser extends GuiContainer {
	
	public ResourceLocation texture = new ResourceLocation(Oreganic.modid + ":" + "textures/gui/organicinfuser.png");
	public TileEntityProOrganicInfuser hobbyOrganicInfuser;

	public GuiProOrganicInfuser(InventoryPlayer invPlayer, TileEntityProOrganicInfuser teProOrganicInfuser) {
		super(new ContainerProOrganicInfuser(invPlayer, teProOrganicInfuser));
		hobbyOrganicInfuser = teProOrganicInfuser;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		String name = "Professional Organic Infuser";
		
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 4, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if (hobbyOrganicInfuser.hasPower())
        {
            int i1 = hobbyOrganicInfuser.getPowerRemainingScaled(37);
            drawTexturedModalRect(guiLeft + 17, guiTop + 49 - i1, 176, 166 - i1, 16, i1);
        }

        int i1 = hobbyOrganicInfuser.getInfusionProgressScaled(34);
        drawTexturedModalRect(guiLeft + 65, guiTop + 15, 176, 0, i1, 56);
    }

}
