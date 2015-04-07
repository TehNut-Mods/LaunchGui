package tehnut.launchgui.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import tehnut.launchgui.ConfigHandler;
import tehnut.launchgui.utils.LogHelper;
import tehnut.launchgui.utils.Utils;

import java.net.URI;
import java.util.List;

public class GuiStartup extends GuiScreen {

    @SuppressWarnings("unchecked")
    @Override
    public void initGui() {
        if (ConfigHandler.enableLinkButton) {
            this.buttonList.add(new GuiButton(0, this.width / 2 - 154, this.height / 2 + 96, 144, 20, ConfigHandler.continueButtonText));
            this.buttonList.add(new GuiButton(1, this.width / 2 + 10, this.height / 2 + 96, 144, 20, ConfigHandler.linkButtonText));
        } else {
            buttonList.clear();
            this.buttonList.add(new GuiButton(0, this.width / 2 - 144, this.height / 2 + 96, 288, 20, ConfigHandler.continueButtonText));
        }
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        drawDefaultBackground();

        drawCenteredString(this.fontRendererObj, EnumChatFormatting.YELLOW + ConfigHandler.startupGuiTitle, this.width / 2, this.height / 2 - 100, 0xFFFFFF);
        drawCenteredString(this.fontRendererObj, ConfigHandler.startupGuiLine1, this.width / 2, this.height / 2 - 85, 0xFFFFFF);
        drawCenteredString(this.fontRendererObj, ConfigHandler.startupGuiLine2, this.width / 2, this.height / 2 - 70, 0xFFFFFF);
        drawCenteredString(this.fontRendererObj, ConfigHandler.startupGuiLine3, this.width / 2, this.height / 2 - 55, 0xFFFFFF);
        drawCenteredString(this.fontRendererObj, ConfigHandler.startupGuiLine4, this.width / 2, this.height / 2 - 40, 0xFFFFFF);
        drawCenteredString(this.fontRendererObj, ConfigHandler.startupGuiLine5, this.width / 2, this.height / 2 - 25, 0xFFFFFF);
        drawCenteredString(this.fontRendererObj, ConfigHandler.startupGuiLine6, this.width / 2, this.height / 2 - 10, 0xFFFFFF);
        drawCenteredString(this.fontRendererObj, ConfigHandler.startupGuiLine7, this.width / 2, this.height / 2 + 5, 0xFFFFFF);
        drawCenteredString(this.fontRendererObj, ConfigHandler.startupGuiLine8, this.width / 2, this.height / 2 + 20, 0xFFFFFF);
        drawCenteredString(this.fontRendererObj, ConfigHandler.startupGuiLine9, this.width / 2, this.height / 2 + 35, 0xFFFFFF);
        
        super.drawScreen(par1, par2, par3);
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
                    Utils.browse(new URI(ConfigHandler.linkButtonUrl));
                } catch (Exception exception) {
                    LogHelper.error("Failed to load the page at " + ConfigHandler.linkButtonUrl + "!");
                    exception.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    protected void keyTyped(char par1, int par2) {
        return;
    }

    private void dontShowAgain() {
        if (ConfigHandler.disableGuiAfterViewed) {
            LogHelper.info("Disabling GUI...");
            ConfigHandler.manuallyChangeConfigValue("LaunchGui.cfg", "B:showGuiOnStartup", "true", "false");
        }
    }
}
