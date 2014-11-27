package main.launchgui;

import java.awt.*;
import java.net.URI;

public class Utils {

	public static boolean browse(URI uri) {
		return browseDESKTOP(uri);
	}

	private static boolean browseDESKTOP(URI uri) {

		LaunchGui.logger.info("Attempting to open the page at " + uri.toString());
		try {
			if (!Desktop.isDesktopSupported()) {
				LaunchGui.logger.error("Sorry, it appears that your platform is not supported.");
				return false;
			}

			if (!Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				LaunchGui.logger.error("Sorry, it appears that the BROWSE action is not supported.");
				return false;
			}

			Desktop.getDesktop().browse(uri);
			LaunchGui.logger.info("Attempt successful!");
			return true;

		} catch (Throwable throwable) {
			LaunchGui.logger.error("Error using desktop browse.", throwable);
			throwable.printStackTrace();
			return false;
		}
	}
}
