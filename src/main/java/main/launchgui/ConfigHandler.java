package main.launchgui;

import net.minecraftforge.common.config.Configuration;

import java.io.*;
import java.util.ArrayList;

public class ConfigHandler {

	//sections
	public static String internal = "Internal";
	public static String info = "Information";
	public static String buttons = "Buttons";

	//options
	public static boolean displayGuiOnLaunch;
	public static String modToFind;

	public static String guiTitle;
	public static String line1;
	public static String line2;
	public static String line3;
	public static String line4;
	public static String line5;
	public static String line6;
	public static String line7;
	public static String line8;
	public static String line9;

	public static boolean disableGuiAfterFirstLaunch;
	public static String continueButtonText;
	public static boolean addLinkButton;
	public static String linkButtonText;
	public static String linkButtonLink;

	public static File cfg;

	public static void init(Configuration config) {
		config.load();

		config.addCustomCategoryComment(buttons, "Everything to do with the buttons.");
		config.addCustomCategoryComment(info, "These are the options for what your GUI says. Change them as you please, but try to keep them short. It won't split lines for you. Leave blank if unused.");
		config.addCustomCategoryComment(internal, "Internally used options.");

		displayGuiOnLaunch = config.get(internal, "displayGuiOnLaunch", true, "Whether or not to display the GUI on launch. Should not be touched.").getBoolean(displayGuiOnLaunch);
		modToFind = config.get(internal, "modToFind", "launchgui", "Requires this mod to load the GUI. To always load no matter what, use a mod that is always installed.").getString().toLowerCase();

		guiTitle = config.get(info, "guiTitle", "TITLE", "Title of your GUI. Appears at the top.").getString();
		line1 = config.get(info, "line1", "", "These are your information info in the GUI").getString();
		line2 = config.get(info, "line2", "").getString();
		line3 = config.get(info, "line3", "").getString();
		line4 = config.get(info, "line4", "").getString();
		line5 = config.get(info, "line5", "").getString();
		line6 = config.get(info, "line6", "").getString();
		line7 = config.get(info, "line7", "").getString();
		line8 = config.get(info, "line8", "").getString();
		line9 = config.get(info, "line9", "").getString();

		disableGuiAfterFirstLaunch = config.get(buttons, "disableGuiAfterFirstLaunch", true, "Whether or not to disable the GUI from showing again after the player presses continue.").getBoolean(disableGuiAfterFirstLaunch);
		continueButtonText = config.get(buttons, "continueButtonText", "Continue to Game", "Text to display on the button").getString();
		addLinkButton = config.get(buttons, "addLinkButton", true, "Add a second button that has a link attached to it. Clicking the button will open the link in the user's default browser.").getBoolean(displayGuiOnLaunch);
		linkButtonText = config.get(buttons, "linkButtonText", "Latest Release", "Text to display on the button.").getString();
		linkButtonLink = config.get(buttons, "linkButtonLink", "http://tehnut.info/jenkins/job/LaunchGUI-1.7.10/", "Link to open when button is clicked.").getString();

		if(config.hasChanged()) {
			config.save();
		}
	}

	public static boolean manuallyChangeConfigValue(String filePathFromConfigFolder, String prefix, String from, String to)	{
		File config = filePathFromConfigFolder == null ? cfg : new File("config/launchgui.cfg");
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
					LaunchGui.logger.info("Successfully changed config value " + prefix + " from " + from + " to " + to);
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
