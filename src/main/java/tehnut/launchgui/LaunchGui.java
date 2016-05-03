package tehnut.launchgui;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tehnut.launchgui.utils.EventHandler;
import tehnut.launchgui.utils.Utils;

import java.io.File;

@Mod(modid = LaunchGui.ID, name = LaunchGui.NAME, version = LaunchGui.VERSION)
public class LaunchGui {

    public static final String NAME = "LaunchGui";
    public static final String ID = "launchgui";
    public static final String CHANNEL = "LaunchGui";
    public static final String VERSION = "@VERSION@";

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

        if (event.getSide().isClient())
            MinecraftForge.EVENT_BUS.register(new EventHandler());
    }
}
