package tehnut.launchgui.utils;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tehnut.launchgui.ConfigHandler;
import tehnut.launchgui.gui.GuiNotice;
import tehnut.launchgui.gui.GuiStartup;
import tehnut.launchgui.gui.GuiUpdate;

public class EventHandler {

    private boolean shouldLoadGUI = true;

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void openMainMenu(GuiOpenEvent event) {
        if (shouldLoadGUI) {
            if (event.getGui() instanceof GuiMainMenu) {
                if (Utils.hasUpdate() && !ConfigHandler.checkUpdateEarly) {
                    event.setGui(new GuiUpdate());
                    shouldLoadGUI = false;
                    return;
                }

                if (Utils.hasNotice()) {
                    event.setGui(new GuiNotice());
                    shouldLoadGUI = false;
                    return;
                }

                if (ConfigHandler.showGuiOnStartup && Utils.shouldLoadFromModSearch()) {
                    event.setGui(new GuiStartup());
                    shouldLoadGUI = false;
                }
            }
        }
    }
}
