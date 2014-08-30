package io.github.crystic.oreganics.gui;

import io.github.crystic.oreganics.Oreganic;
import io.github.crystic.oreganics.container.ContainerHobbyMineralExtractor;
import io.github.crystic.oreganics.tileentity.TileEntityHobbyMineralExtractor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiHobbyMineralExtractor extends GuiContainer {

	public static final ResourceLocation bground = new ResourceLocation(
			Oreganic.modid + ":" + "textures/gui/mineralextractor.png");

	public TileEntityHobbyMineralExtractor hobbyMineralExtractor;

	public GuiHobbyMineralExtractor(InventoryPlayer inventoryPlayer, TileEntityHobbyMineralExtractor entity) {
		super(new ContainerHobbyMineralExtractor(inventoryPlayer, entity));

		this.hobbyMineralExtractor = entity;

		this.xSize = 176;
		this.ySize = 166;
	}

	public void drawGuiContainerForegroundLayer(int par1, int par2) {

		String name = "Hobbyist Mineral Extractor";
		this.fontRendererObj.drawString(name, this.xSize / 2
				- this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(
				I18n.format("container.inventory", new Object[0]), 8,
				this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		GL11.glColor4f(1F, 1F, 1F, 1F);

		Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if (this.hobbyMineralExtractor.isBurning()) {
            int i1 = hobbyMineralExtractor.getBurnTimeRemainingScaled(37);
            drawTexturedModalRect(guiLeft + 17, guiTop + 49 - i1, 176, 166 - i1, 16, i1);

		}

        int j1 = hobbyMineralExtractor.getCookProgressScaled(34);
        drawTexturedModalRect(guiLeft + 65, guiTop + 34, 176, 0, j1, 56);
	}

}
