package main.launchgui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.net.URI;
import java.util.List;

public class StartupGui extends GuiScreen {

	private boolean showUpdateText = false;

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		if (ConfigHandler.addLinkButton) {
			this.buttonList.add(new GuiButton(0, this.width / 2 - 154, this.height / 2 + 96, 144, 20, ConfigHandler.continueButtonText));
			this.buttonList.add(new GuiButton(1, this.width / 2 + 10, this.height / 2 + 96, 144, 20, ConfigHandler.linkButtonText));
		} else {
			this.buttonList.add(new GuiButton(0, this.width / 2 - 144, this.height / 2 + 96, 288, 20, ConfigHandler.continueButtonText));
		}

		if (ConfigHandler.enableUpdateChecker && Utils.isUpdateAvailable() && ConfigHandler.disableContinueIfUpdatable) {
			this.buttonList.clear();
			showUpdateText = true;
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();

		if (!showUpdateText) {
			drawCenteredString(this.fontRendererObj, TextHelper.YELLOW + ConfigHandler.guiTitle, this.width / 2, this.height / 2 - 100, 0xFFFFFF);
			drawCenteredString(this.fontRendererObj, ConfigHandler.line1, this.width / 2, this.height / 2 - 85, 0xFFFFFF);
			drawCenteredString(this.fontRendererObj, ConfigHandler.line2, this.width / 2, this.height / 2 - 70, 0xFFFFFF);
			drawCenteredString(this.fontRendererObj, ConfigHandler.line3, this.width / 2, this.height / 2 - 55, 0xFFFFFF);
			drawCenteredString(this.fontRendererObj, ConfigHandler.line4, this.width / 2, this.height / 2 - 40, 0xFFFFFF);
			drawCenteredString(this.fontRendererObj, ConfigHandler.line5, this.width / 2, this.height / 2 - 25, 0xFFFFFF);
			drawCenteredString(this.fontRendererObj, ConfigHandler.line6, this.width / 2, this.height / 2 - 10, 0xFFFFFF);
			drawCenteredString(this.fontRendererObj, ConfigHandler.line7, this.width / 2, this.height / 2 + 5, 0xFFFFFF);
			drawCenteredString(this.fontRendererObj, ConfigHandler.line8, this.width / 2, this.height / 2 + 20, 0xFFFFFF);
			drawCenteredString(this.fontRendererObj, ConfigHandler.line9, this.width / 2, this.height / 2 + 35, 0xFFFFFF);
			if (ConfigHandler.enableUpdateChecker && Utils.isUpdateAvailable()) {
				drawCenteredString(this.fontRendererObj, TextHelper.BRIGHT_GREEN + TextHelper.localize("info.launchgui.update"), this.width - 70, this.height / 2 - 115, 0xFFFFFF);
			}
			super.drawScreen(par1, par2, par3);
		} else {
			drawCenteredString(this.fontRendererObj, TextHelper.BRIGHT_GREEN + TextHelper.localize("info.launchgui.update"), this.width / 2, this.height / 2 - 70, 0xFFFFFF);
			drawCenteredString(this.fontRendererObj, TextHelper.ORANGE + String.format(TextHelper.localize("info.launchgui.update.version"), Utils.getRemoteVersion()), this.width / 2, this.height / 2 - 55, 0xFFFFFF);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
			case 0: {
				dontShowAgain();
				for (GuiButton b : (List<GuiButton>) buttonList) {
					b.enabled = false;
				}
				this.mc.displayGuiScreen(null);
				break;
			}
			case 1: {
				try {
					Utils.browse(new URI(ConfigHandler.linkButtonLink));
				} catch (Exception exception) {
					LaunchGui.logger.error("Failed to load the page at " + ConfigHandler.linkButtonLink + "!");
					exception.printStackTrace();
				}
				break;
			}
			default: {
				this.mc.displayGuiScreen(null);
				break;
			}
		}
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		return;
	}

	private void dontShowAgain() {
		if (ConfigHandler.disableGuiAfterFirstLaunch) {
			LaunchGui.logger.info("Disabling GUI...");
			ConfigHandler.manuallyChangeConfigValue("launchgui.cfg", "B:displayGuiOnLaunch", "true", "false");
		}
	}
}
