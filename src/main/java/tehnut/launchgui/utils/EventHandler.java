package tehnut.launchgui.utils;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
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
            if (event.gui instanceof GuiMainMenu) {
                if (Utils.hasUpdate()) {
                    event.gui = new GuiUpdate();
                    shouldLoadGUI = false;
                    return;
                }

                if (Utils.hasNotice()) {
                    event.gui = new GuiNotice();
                    shouldLoadGUI = false;
                    return;
                }

                if (ConfigHandler.showGuiOnStartup && (Loader.isModLoaded(ConfigHandler.modToFind) || ConfigHandler.modToFind.equals(""))) {
                    event.gui = new GuiStartup();
                    shouldLoadGUI = false;
                }
            }
        }
    }
}
