package main.launchgui;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;

public class GuiEventHandler {

	private boolean shouldLoadGUI = true;

	public static GuiEventHandler instance = new GuiEventHandler();

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void openMainMenu(GuiOpenEvent event) {
		if (shouldLoadGUI) {
            if (event.gui instanceof GuiMainMenu) {
                if (((ConfigHandler.displayGuiOnLaunch && Loader.isModLoaded(ConfigHandler.modToFind)) || (ConfigHandler.enableUpdateChecker && Utils.isUpdateAvailable()))) {
                    event.gui = new StartupGui();
                    shouldLoadGUI = false;
                }
            }
        }
	}
}
