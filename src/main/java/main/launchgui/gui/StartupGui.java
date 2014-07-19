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
		this.buttonList.add(new GuiButton(0, this.width / 2 - 144, this.height / 2 + 96, 288, 20, TextHelper.localize("info.launchgui.button.title")));
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();

		this.drawCenteredString(this.fontRendererObj, TextHelper.YELLOW + TextHelper.localize("info.launchgui.title"), this.width / 2, this.height / 2 - 100, 0xFFFFFF);
		this.drawCenteredString(this.fontRendererObj, TextHelper.localize("info.launchgui.text.main.one"), this.width / 2, this.height / 2 - 85, 0xFFFFFF);
		this.drawCenteredString(this.fontRendererObj, TextHelper.localize("info.launchgui.text.main.two"), this.width / 2, this.height / 2 - 70, 0xFFFFFF);
		this.drawCenteredString(this.fontRendererObj, TextHelper.localize("info.launchgui.text.main.three"), this.width / 2, this.height / 2 - 55, 0xFFFFFF);
		this.drawCenteredString(this.fontRendererObj, TextHelper.localize("info.launchgui.text.main.four"), this.width / 2, this.height / 2 - 40, 0xFFFFFF);
		this.drawCenteredString(this.fontRendererObj, TextHelper.localize("info.launchgui.text.main.five"), this.width / 2, this.height / 2 - 25, 0xFFFFFF);
		this.drawCenteredString(this.fontRendererObj, TextHelper.localize("info.launchgui.text.main.six"), this.width / 2, this.height / 2 - 10, 0xFFFFFF);
		this.drawCenteredString(this.fontRendererObj, TextHelper.localize("info.launchgui.text.main.seven"), this.width / 2, this.height / 2 + 5, 0xFFFFFF);
		this.drawCenteredString(this.fontRendererObj, TextHelper.localize("info.launchgui.text.main.eight"), this.width / 2, this.height / 2 + 20, 0xFFFFFF);
		this.drawCenteredString(this.fontRendererObj, TextHelper.localize("info.launchgui.text.main.nine"), this.width / 2, this.height / 2 + 35, 0xFFFFFF);
		this.drawCenteredString(this.fontRendererObj, TextHelper.localize("info.launchgui.text.main.ten"), this.width / 2, this.height / 2 + 50, 0xFFFFFF);
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
		ConfigHandler.manuallyChangeConfigValue("launchgui.cfg", "B:displayGuiOnLaunch", "true", "false");
		LaunchGui.logger.info("Disabling GUI...");
	}
}
