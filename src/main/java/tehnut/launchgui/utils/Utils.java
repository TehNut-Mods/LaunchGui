package tehnut.launchgui.utils;

import tehnut.launchgui.ConfigHandler;
import tehnut.launchgui.LaunchGui;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class Utils {

    private static boolean checkUpdate = true;

    public static boolean hasUpdate() {
        return ConfigHandler.enableUpdateChecker && !LaunchGui.remoteVersion.equals(ConfigHandler.currentPackVersion) && !LaunchGui.remoteVersion.equals("") && !ConfigHandler.updateCheckerUrl.equals("");
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

    /**
     * @param uri - The {@link java.net.URI} to browse to.
     * @return - Whether the {@link java.net.URI} was opened.
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
