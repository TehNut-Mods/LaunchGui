package main.launchgui;

import net.minecraftforge.common.config.Configuration;

import java.io.*;
import java.util.ArrayList;

public class ConfigHandler {

	//sections
	public static String main = "Main";
	public static String lines = "Lines";

	//options
	public static boolean displayGuiOnLaunch;

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
	public static String buttonText;

	public static File cfg;

	public static void init(Configuration config) {
		config.load();

		config.addCustomCategoryComment(lines, "These are the options for what your GUI says. Change them as you please, but try to keep them short. It won't split lines for you. Leave blank if unused.");

		displayGuiOnLaunch = config.get(main, "displayGuiOnLaunch", true, "Whether or not to display the GUI on launch. Should not be touched.").getBoolean(displayGuiOnLaunch);

		guiTitle = config.get(lines, "guiTitle", "TITLE", "Title of your GUI. Appears at the top.").getString();
		line1 = config.get(lines, "line1", "These are your information lines in the GUI").getString();
		line2 = config.get(lines, "line2", "").getString();
		line3 = config.get(lines, "line3", "").getString();
		line4 = config.get(lines, "line4", "").getString();
		line5 = config.get(lines, "line5", "").getString();
		line6 = config.get(lines, "line6", "").getString();
		line7 = config.get(lines, "line7", "").getString();
		line8 = config.get(lines, "line8", "").getString();
		line9 = config.get(lines, "line9", "").getString();
		buttonText = config.get(lines, "buttonText", "Continue to Game", "Text to display on the button").getString();

		if(config.hasChanged()) {
			config.save();
		}
	}

	public static boolean manuallyChangeConfigValue(String prefix, String from, String to) {
		return manuallyChangeConfigValue(null, prefix, from, to);
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
