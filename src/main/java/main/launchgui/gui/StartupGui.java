package main.launchgui.gui;

import main.launchgui.ConfigHandler;
import main.launchgui.LaunchGui;
import main.launchgui.util.TextHelper;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.util.List;

public class StartupGui extends GuiScreen {

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		this.buttonList.add(new GuiButton(0, this.width / 2 - 144, this.height / 2 + 96, 288, 20, ConfigHandler.buttonText));
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();

		this.drawCenteredString(this.mc.fontRenderer, TextHelper.YELLOW + ConfigHandler.guiTitle, this.width / 2, this.height / 2 - 100, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, ConfigHandler.line1, this.width / 2, this.height / 2 - 85, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, ConfigHandler.line2, this.width / 2, this.height / 2 - 70, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, ConfigHandler.line3, this.width / 2, this.height / 2 - 55, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, ConfigHandler.line4, this.width / 2, this.height / 2 - 40, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, ConfigHandler.line5, this.width / 2, this.height / 2 - 25, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, ConfigHandler.line6, this.width / 2, this.height / 2 - 10, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, ConfigHandler.line7, this.width / 2, this.height / 2 + 5, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, ConfigHandler.line8, this.width / 2, this.height / 2 + 20, 0xFFFFFF);
		this.drawCenteredString(this.mc.fontRenderer, ConfigHandler.line9, this.width / 2, this.height / 2 + 35, 0xFFFFFF);
		super.drawScreen(par1, par2, par3);
	}


	@Override
	public void drawCenteredString(FontRenderer fontRenderer, String string, int x, int y, int color) {
		fontRenderer.drawStringWithShadow(string, x - fontRenderer.getStringWidth(string.replaceAll("\\P{InBasic_Latin}", "")) / 2, y, color);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void actionPerformed(GuiButton button) {
		dontShowAgain();
		for (GuiButton b : (List<GuiButton>) buttonList) {
			b.enabled = false;
		}
		this.mc.displayGuiScreen(null);
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		;
	}

	private void dontShowAgain() {
		LaunchGui.logger.info("Disabling GUI...");
		ConfigHandler.manuallyChangeConfigValue("launchgui.cfg", "B:displayGuiOnLaunch", "true", "false");
	}
}
