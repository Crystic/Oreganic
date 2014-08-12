package io.github.crystic.oreganic.gui;

import io.github.crystic.oreganic.Oreganic;
import io.github.crystic.oreganic.container.ContainerMineralExtractor;
import io.github.crystic.oreganic.tileentity.TileEntityMineralExtractor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiMineralExtractor extends GuiContainer {

	public static final ResourceLocation bground = new ResourceLocation(
			Oreganic.modid + ":" + "textures/gui/mineralextractor.png");

	public TileEntityMineralExtractor mineralExtractor;

	public GuiMineralExtractor(InventoryPlayer inventoryPlayer,
			TileEntityMineralExtractor entity) {
		super(new ContainerMineralExtractor(inventoryPlayer, entity));

		this.mineralExtractor = entity;

		this.xSize = 176;
		this.ySize = 166;
	}

	public void drawGuiContainerForegroundLayer(int par1, int par2) {

		String name = "Mineral Extractor";
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

		if (this.mineralExtractor.isBurning()) {
            int i1 = mineralExtractor.getBurnTimeRemainingScaled(46);
            drawTexturedModalRect(guiLeft + 17, guiTop + 49 - i1, 176, 166 - i1, 16, i1);

		}

        int j1 = mineralExtractor.getCookProgressScaled(34);
        drawTexturedModalRect(guiLeft + 65, guiTop + 34, 176, 0, j1, 56);
	}

}
