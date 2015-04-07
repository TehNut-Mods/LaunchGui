package tehnut.launchgui;

import net.minecraftforge.common.config.Configuration;
import tehnut.launchgui.utils.LogHelper;

import java.io.*;
import java.util.ArrayList;

public class ConfigHandler {

    public static Configuration config;

    public static boolean showGuiOnStartup;
    public static String modToFind;
    public static boolean disableGuiAfterViewed;
    public static boolean enableLinkButton;
    public static String linkButtonText;
    public static String linkButtonUrl;
    public static String continueButtonText;

    public static String startupGuiTitle;
    public static String startupGuiLine1;
    public static String startupGuiLine2;
    public static String startupGuiLine3;
    public static String startupGuiLine4;
    public static String startupGuiLine5;
    public static String startupGuiLine6;
    public static String startupGuiLine7;
    public static String startupGuiLine8;
    public static String startupGuiLine9;

    public static boolean enableUpdateChecker;
    public static boolean disableContinueButtonIfUpdate;
    public static String updateCheckerUrl;
    public static String updateInformationButtonText;
    public static String updateInformationUrl;
    public static String currentPackVersion;

    public static boolean enableLogging;

    public static File cfg;

    public static void init(File file) {
        config = new Configuration(file);
        syncConfig();
    }

    public static void syncConfig() {
        String category;

        category = "Startup Gui";
        showGuiOnStartup = config.getBoolean("showGuiOnStartup", category, true, "Whether or not to show the GUI on startup. Used internally, do not touch.");
        modToFind = config.getString("modToFind", category, "", "Put a modid here to only load if that mod is installed. Leave blank to not check for a mod at all.");
        disableGuiAfterViewed = config.getBoolean("disableGuiAfterViewed", category, true, "Whether to disable the GUI after it has been viewed before.\nSet to false to show GUI on every startup. Still requires showGuiOnStartup to be true.");
        enableLinkButton = config.getBoolean("enableLinkButton", category, true, "Add a second button that has a link attached to it. Clicking the button will open the link in the user's default browser.");
        linkButtonText = config.getString("linkButtonText", category, "My Website", "Text to display on the link button.");
        linkButtonUrl = config.getString("linkButtonUrl", category, "http://tehnut.info/", "Link to open when clicked.");
        continueButtonText = config.getString("continueButtonText", category, "Continue", "Text to display on the continue button.");

        category = "Startup Gui Text";
        startupGuiTitle = config.getString("startupGuiTitle", category, "TITLE", "Title of the startup GUI. Shows as yellow text.");
        startupGuiLine1 = config.getString("startupGuiLine1", category, "", "These are your information info lines in the GUI");
        startupGuiLine2 = config.get(category, "startupGuiLine2", "").getString();
        startupGuiLine3 = config.get(category, "startupGuiLine3", "").getString();
        startupGuiLine4 = config.get(category, "startupGuiLine4", "").getString();
        startupGuiLine5 = config.get(category, "startupGuiLine5", "").getString();
        startupGuiLine6 = config.get(category, "startupGuiLine6", "").getString();
        startupGuiLine7 = config.get(category, "startupGuiLine7", "").getString();
        startupGuiLine8 = config.get(category, "startupGuiLine8", "").getString();
        startupGuiLine9 = config.get(category, "startupGuiLine9", "").getString();

        category = "Update Checker";
        enableUpdateChecker = config.getBoolean("enableUpdateChecker", category, false, "Enables the update checker.");
        disableContinueButtonIfUpdate = config.getBoolean("disableContinueButtonIfUpdate", category, false, "Disable the Continue button if there is a pack update available.");
        updateCheckerUrl = config.getString("updateCheckerUrl", category, "", "URL to check for a new version. Required a raw text file. See here for an example:\nhttps://raw.githubusercontent.com/TehNut/LaunchGui/1.7.10/version.txt");
        updateInformationButtonText = config.getString("updateInformationButtonText", category, "Changelog", "Text to display on update information button");
        updateInformationUrl = config.getString("updateInformationUrl", category, "", "A URL to a page with information about the pack/update.");
        currentPackVersion = config.getString("currentPackVersion", category, "", "The version of your pack currently being shipped.");

        category = "Miscellaneous";
        enableLogging = config.getBoolean("enableLogging", category, true, "Enables logging information to the console.");

        config.save();
    }

    public static boolean manuallyChangeConfigValue(String filePathFromConfigFolder, String prefix, String from, String to)	{
        File config = filePathFromConfigFolder == null ? cfg : new File("config/LaunchGui.cfg");
        boolean found = false;

        try {
            FileReader fr1 = new FileReader(config);
            BufferedReader read = new BufferedReader(fr1);

            ArrayList<String> strings = new ArrayList<String>();

            while (read.ready()) {
                strings.add(read.readLine());
            }

            fr1.close();
            read.close();

            FileWriter fw = new FileWriter(config);
            BufferedWriter bw = new BufferedWriter(fw);

            for (String s : strings) {
                if (!found && s.contains(prefix + "=" + from) && !s.contains("=" + to)) {
                    s = s.replace(prefix + "=" + from, prefix + "=" + to);
                    LogHelper.info("Successfully changed config value " + prefix + " from " + from + " to " + to);
                    found = true;
                }

                fw.write(s + "\n");
            }

            bw.flush();
            bw.close();
        }
        catch (Throwable t) {
            t.printStackTrace();
        }

        return found;
    }
}
