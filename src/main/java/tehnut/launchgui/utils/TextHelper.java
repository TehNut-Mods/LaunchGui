package tehnut.launchgui.utils;

import net.minecraft.util.StatCollector;

public class TextHelper {

    /**
     * @param key - Unlocalized key to localize
     * @return - Localized string
     */
    public static String localize(String key) {
        return StatCollector.translateToLocal(key);
    }

    /**
     * @param key       - Unlocalized key to localize
     * @param keyFormat - Unlocalized key to localize and format with
     * @return - Localized string
     */
    public static String localizeFormatted(String key, String keyFormat) {
        return String.format(localize(key), localize(keyFormat));
    }
}
