package tehnut.launchgui.utils;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Loader;
import tehnut.launchgui.ConfigHandler;
import tehnut.launchgui.LaunchGui;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class Utils {

    private static boolean checkUpdate = true;
    private static boolean checkText = true;

    public static boolean hasNotice() {
        return ConfigHandler.enableNoticeGui && !LaunchGui.remoteText.equals("") && !ConfigHandler.infoUrl.equals("");
    }

    public static boolean hasUpdate() {
        return ConfigHandler.enableUpdateChecker && !LaunchGui.remoteVersion.equals(ConfigHandler.currentPackVersion) && !LaunchGui.remoteVersion.equals("") && !ConfigHandler.updateCheckerUrl.equals("");
    }

    public static String getRemoteText() {
        if (checkText) {
            try {
                URL url = new URL(ConfigHandler.infoUrl);
                Scanner scanner = new Scanner(url.openStream());
                checkText = false;
                return scanner.nextLine();
            } catch (IOException e) {
                LogHelper.error("Error returned while obtaining the notice information.");
            }
        }

        checkText = false;

        return "";
    }

    public static String getRemoteVersion() {
        if (checkUpdate) {
            try {
                URL url = new URL(ConfigHandler.updateCheckerUrl);
                Scanner scanner = new Scanner(url.openStream());
                checkUpdate = false;
                return scanner.nextLine();
            } catch (IOException e) {
                LogHelper.error("Error returned while attempting to check for an update.");
            }
        }

        checkUpdate = false;

        return "";
    }

    public static boolean shouldLoadFromModSearch() {
        if (ConfigHandler.invertModFinder) {
            return !Loader.isModLoaded(ConfigHandler.modToFind);
        } else {
            return Loader.isModLoaded(ConfigHandler.modToFind) || ConfigHandler.modToFind.equals("");
        }
    }

    public static String replaceTextCodes(String toReplace) {
        return toReplace
                .replace("\\n", "\n")
                .replace("%name%", ConfigHandler.modpackName)
                .replace("%acro%", ConfigHandler.modpackAcronym)
                .replace("%version%", ConfigHandler.modpackVersion)
                .replace("%player%", Minecraft.getMinecraft().getSession().getUsername());
    }

    /**
     * @param uri - The {@link URI} to browse to.
     * @return - Whether the {@link URI} was opened.
     */
    public static boolean browse(URI uri) {
        return browseDESKTOP(uri);
    }

    private static boolean browseDESKTOP(URI uri) {

        LogHelper.info("Attempting to open the page at " + uri);
        try {
            if (!Desktop.isDesktopSupported()) {
                LogHelper.error("Sorry, it appears that your platform is not supported.");
                return false;
            }

            if (!Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                LogHelper.error("Sorry, it appears that the BROWSE action is not supported.");
                return false;
            }

            Desktop.getDesktop().browse(uri);
            LogHelper.info("Attempt successful!");
            return true;

        } catch (Throwable throwable) {
            LogHelper.error("Error using desktop browse.", throwable);
            throwable.printStackTrace();
            return false;
        }
    }
}
