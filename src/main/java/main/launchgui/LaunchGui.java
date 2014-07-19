package main.launchgui;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import main.launchgui.gui.GuiEventHandler;
import main.launchgui.proxies.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION)
public class LaunchGui {

	@SidedProxy(clientSide = "main.launchgui.proxies.ClientProxy", serverSide = "main.launchgui.proxies.CommonProxy")
	public static CommonProxy proxy;

	public static Logger logger = LogManager.getLogger(ModInformation.NAME);

	@Mod.Instance
	public static LaunchGui instance;
	public static Configuration config;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());
		ConfigHandler.init(config);

		MinecraftForge.EVENT_BUS.register(GuiEventHandler.instance);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
