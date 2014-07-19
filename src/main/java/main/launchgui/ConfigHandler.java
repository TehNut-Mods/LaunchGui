package main.launchgui;

import net.minecraftforge.common.config.Configuration;

import java.io.*;
import java.util.ArrayList;

public class ConfigHandler {

	//sections
	public static String main = "Main";

	//options
	public static boolean displayGuiOnLaunch;

	public static File cfg;

	public static void init(Configuration config) {
		config.load();

		displayGuiOnLaunch = config.get(main, "displayGuiOnLaunch", true, "Whether or not to display the GUI on launch. Should not be touched.").getBoolean(displayGuiOnLaunch);

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
