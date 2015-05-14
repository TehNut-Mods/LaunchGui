package tehnut.launchgui.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import tehnut.launchgui.ConfigHandler;
import tehnut.launchgui.LaunchGui;
import tehnut.launchgui.utils.LogHelper;
import tehnut.launchgui.utils.Utils;

import java.net.URI;
import java.util.List;

public class GuiNotice extends GuiScreen {

    @SuppressWarnings("unchecked")
    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(0, this.width / 2 - 154, this.height / 2 + 96, 144, 20, ConfigHandler.continueButtonText));
        this.buttonList.add(new GuiButton(1, this.width / 2 + 10, this.height / 2 + 96, 144, 20, ConfigHandler.infoButtonText));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void drawScreen(int par1, int par2, float par3) {
        drawDefaultBackground();

        int heightLoc = 85;

        drawCenteredString(this.fontRendererObj, EnumChatFormatting.YELLOW + ConfigHandler.infoTitle, this.width / 2, this.height / 2 - 100, 0xFFFFFF);

        String[] lines = LaunchGui.remoteText.replace("\\n", "\n").split("\n");
        for (String s : lines) {

            List<String> info = fontRendererObj.listFormattedStringToWidth(s, this.width - 40);
            for (String infoCut : info) {
                drawCenteredString(this.fontRendererObj, Utils.replaceTextCodes(infoCut), this.width / 2, this.height / 2 - heightLoc, 0xFFFFFF);
                heightLoc = heightLoc - 12;
            }
        }

        super.drawScreen(par1, par2, par3);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0: {
                for (GuiButton b : (List<GuiButton>) buttonList) {
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
    protected void keyTyped(char par1, int par2) {
        return;
    }
}
