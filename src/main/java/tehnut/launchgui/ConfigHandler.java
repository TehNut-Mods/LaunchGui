package tehnut.launchgui;

import net.minecraftforge.common.config.Configuration;
import tehnut.launchgui.utils.LogHelper;

import java.io.*;
import java.util.ArrayList;

public class ConfigHandler {

    public static Configuration config;

    public static boolean showGuiOnStartup;
    public static boolean invertModFinder;
    public static String modToFind;
    public static boolean disableGuiAfterViewed;
    public static boolean enableLinkButton;
    public static String linkButtonText;
    public static String linkButtonUrl;
    public static String continueButtonText;
    public static String startupGuiTitle;
    public static String startupGuiLines;

    public static boolean enableUpdateChecker;
    public static boolean disableContinueButtonIfUpdate;
    public static boolean checkUpdateEarly;
    public static String updateGuiLines;
    public static String updateCheckerUrl;
    public static String updateInformationButtonText;
    public static String updateInformationUrl;
    public static String currentPackVersion;

    public static boolean enableNoticeGui;
    public static String infoButtonText;
    public static String infoButtonUrl;
    public static String infoTitle;
    public static String infoUrl;

    public static String modpackName;
    public static String modpackAcronym;
    public static String modpackVersion;

    public static boolean enableLogging;

    public static File cfg;

    public static void init(File file) {
        config = new Configuration(file);
        syncConfig();
    }

    public static void syncConfig() {
        String category;

        category = "Startup Gui";
        config.addCustomCategoryComment(category, "Settings for the GUI shown on startup.");
        config.addCustomCategoryComment(category + ".button", "Settings related to the shown buttons.");
        config.addCustomCategoryComment(category + ".internal", "Settings for the internal checking that the GUI does.");
        config.addCustomCategoryComment(category + ".information", "Information to provide to players.");
        showGuiOnStartup = config.getBoolean("showGuiOnStartup", category + ".internal", true, "Whether or not to show the GUI on startup. Used internally, do not touch.");
        modToFind = config.getString("modToFind", category + ".internal", "", "Put a modid here to only load if that mod is installed. Leave blank to not check for a mod at all.");
        invertModFinder = config.getBoolean("invertModFinder", category + ".internal", false, "False- Displays Gui when the specified mod is found.\nTrue- Displays Gui when the specified mod is *not* found.");
        disableGuiAfterViewed = config.getBoolean("disableGuiAfterViewed", category + ".internal", true, "Whether to disable the GUI after it has been viewed before.\nSet to false to show GUI on every startup. Still requires showGuiOnStartup to be true.");
        enableLinkButton = config.getBoolean("enableLinkButton", category + ".button", true, "Add a second button that has a link attached to it. Clicking the button will open the link in the user's default browser.");
        linkButtonText = config.getString("linkButtonText", category + ".button", "My Website", "Text to display on the link button.");
        linkButtonUrl = config.getString("linkButtonUrl", category + ".button", "http://tehnut.info/", "Link to open when clicked.");
        continueButtonText = config.getString("continueButtonText", category + ".button", "Continue", "Text to display on the continue button.");
        startupGuiTitle = config.getString("startupGuiTitle", category + ".information", "TITLE", "Title of the startup GUI. Shows as yellow text.");
        startupGuiLines = config.getString("startupGuiLines", category + ".information", "", "These are your information info lines in the GUI\n" +
                "Use \"\\n\" to define a new line. If the line is still too long, it will split for you.\n" +
                "If you do not use custom splits, it will just use the automated ones.\n" +
                "Valid text codes you can use are:\n" +
                "%player% - Provides the player's username.\n" +
                "%name% - Provides modpackName\n" +
                "%version% - Provides modpackVersion\n" +
                "%acro% - Provides modpackAcronym");

        category = "Update Checker";
        config.addCustomCategoryComment(category, "Settings for the GUI shown when an update is available.");
        config.addCustomCategoryComment(category + ".button", "Settings related to the shown buttons.");
        config.addCustomCategoryComment(category + ".internal", "Settings for the internal checking that the GUI does.");
        config.addCustomCategoryComment(category + ".information", "Information to provide to players.");
        checkUpdateEarly = config.getBoolean("checkUpdateEarly", category, true, "Checks for a pack update during the Pre-Initialization phase instead of when the main menu displays.\nThis will open a new window that always displays on top. It will pause loading until closed.");
        enableUpdateChecker = config.getBoolean("enableUpdateChecker", category + ".internal", false, "Enables the update checker.");
        disableContinueButtonIfUpdate = config.getBoolean("disableContinueButtonIfUpdate", category + ".internal", false, "Disable the Continue button if there is a pack update available.");
        updateGuiLines = config.getString("updateGuiLines", category + ".information", "Click the information button below to find out more!", "Information to display to your players whenever a new update is available.\n" +
                "Use \"\\n\" to define a new line. If the line is still too long, it will split for you.\n" +
                "If you do not use custom splits, it will just use the automated ones.\n" +
                "Valid text codes you can use are:\n" +
                "%player% - Provides the player's username.\n" +
                "%name% - Provides modpackName\n" +
                "%version% - Provides modpackVersion\n" +
                "%acro% - Provides modpackAcronym");
        updateCheckerUrl = config.getString("updateCheckerUrl", category + ".information", "http://tehnut.info", "URL to check for a new version. Required a raw text file.\nSee here for an example: https://raw.githubusercontent.com/TehNut/LaunchGui/1.7.10/version.txt");
        updateInformationButtonText = config.getString("updateInformationButtonText", category + ".button", "Changelog", "Text to display on update information button");
        updateInformationUrl = config.getString("updateInformationUrl", category + ".information", "", "A URL to a forum page (or similar) with information about the pack/update.");
        currentPackVersion = config.getString("currentPackVersion", category + ".information", "", "The version of your pack currently being shipped.");

        category = "Notice";
        config.addCustomCategoryComment(category, "Gui that loads if a text file at a specified URL exists and is not empty.");
        config.addCustomCategoryComment(category + ".button", "Settings related to the shown buttons.");
        config.addCustomCategoryComment(category + ".internal", "Settings for the internal checking that the GUI does.");
        config.addCustomCategoryComment(category + ".information", "Information to provide to players.");
        enableNoticeGui = config.getBoolean("enableNoticeGui", category + ".internal", false, "Enables the notice GUI");
        infoButtonText = config.getString("infoButtonText", category + ".button", "Information", "Text to display on info button");
        infoButtonUrl = config.getString("infoButtonUrl", category + ".button", "", "URL that the info button sends you to");
        infoTitle = config.getString("infoTitle", category + ".information", "Important Notice", "Title to display at the top");
        infoUrl = config.getString("infoUrl", category + ".information", "http://tehnut.info", "URL to pull information from.\n" +
                "Use \"\\n\" to define a new line. If the line is still too long, it will split for you.\n" +
                "If you do not use custom splits, it will just use the automated ones.\n" +
                "Valid text codes you can use are:\n" +
                "%player% - Provides the player's username.\n" +
                "%name% - Provides modpackName\n" +
                "%version% - Provides modpackVersion\n" +
                "%acro% - Provides modpackAcronym");

        category = "Global";
        config.addCustomCategoryComment(category, "Global settings that can be used in all GUI's");
        modpackName = config.getString("modpackName", category, LaunchGui.NAME, "The name of your modpack.");
        modpackAcronym = config.getString("modpackAcronym", category, "LGUI", "The acronym of your modpack.");
        modpackVersion = config.getString("modpackVersion", category, LaunchGui.VERSION, "The current version of your modpack");

        category = "Miscellaneous";
        config.addCustomCategoryComment(category, "General settings that don't fall under other categories.");
        enableLogging = config.getBoolean("enableLogging", category, true, "Enables logging information to the console.");

        config.save();
    }

    public static boolean manuallyChangeConfigValue(String filePathFromConfigFolder, String prefix, String from, String to) {
        File config = filePathFromConfigFolder == null ? cfg : new File("config/LaunchGui.cfg");
        boolean found = false;

        try {
            FileReader fr1 = new FileReader(config);
            BufferedReader read = new BufferedReader(fr1);

            ArrayList<String> strings = new ArrayList<String>();

            while (read.ready())
                strings.add(read.readLine());

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
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return found;
    }
}
