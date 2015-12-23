package tehnut.launchgui;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tehnut.launchgui.utils.EventHandler;
import tehnut.launchgui.utils.Utils;

import java.io.File;

@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION, canBeDeactivated = true, acceptedMinecraftVersions = "[1.8,)")
public class LaunchGui {

    @Mod.Instance
    public static LaunchGui instance;
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
