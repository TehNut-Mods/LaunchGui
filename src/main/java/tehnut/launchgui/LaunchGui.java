package tehnut.launchgui;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import tehnut.launchgui.utils.EventHandler;
import tehnut.launchgui.utils.Utils;

import java.io.File;

@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION, canBeDeactivated = true)
public class LaunchGui {

    @Mod.Instance
    public static Configuration config;

    public static String remoteVersion;
    public static String remoteText;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.init(new File(event.getModConfigurationDirectory() + "/LaunchGui.cfg"));

        if (ConfigHandler.enableUpdateChecker)
            remoteVersion = Utils.getRemoteVersion();

        if (ConfigHandler.enableNoticeGui)
            remoteText = Utils.getRemoteText();

        FMLCommonHandler.instance().bus().register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }
}
