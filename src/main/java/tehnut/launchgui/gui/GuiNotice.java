package tehnut.launchgui.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.TextFormatting;
import tehnut.launchgui.ConfigHandler;
import tehnut.launchgui.LaunchGui;
import tehnut.launchgui.utils.LogHelper;
import tehnut.launchgui.utils.Utils;

import java.net.URI;

public class GuiNotice extends GuiScreen {

    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(0, this.width / 2 - 154, this.height / 2 + 96, 144, 20, ConfigHandler.continueButtonText));
        this.buttonList.add(new GuiButton(1, this.width / 2 + 10, this.height / 2 + 96, 144, 20, ConfigHandler.infoButtonText));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // Don't ask me why this is necessary. If this isn't done, only a white screen will render.
        GlStateManager.disableTexture2D();
        GlStateManager.enableTexture2D();

        drawDefaultBackground();
        drawCenteredString(this.fontRendererObj, TextFormatting.YELLOW + ConfigHandler.infoTitle, this.width / 2, this.height / 2 - 100, 0xFFFFFF);
        Utils.handleGuiText(LaunchGui.remoteText, fontRendererObj, this, this.width, this.height);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0: {
                for (GuiButton b : buttonList) {
                    b.enabled = false;
                }
                this.mc.displayGuiScreen(null);
                break;
            }
            case 1: {
                try {
                    Utils.browse(new URI(ConfigHandler.infoButtonUrl));
                } catch (Exception exception) {
                    LogHelper.error("Failed to load the page at " + ConfigHandler.infoButtonUrl + "!");
                    exception.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keycode) {
        // No-op
    }
}
