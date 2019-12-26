package dev.aubique.jcalc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final String file = "config.xml";
    public static Properties settings;
    public static ClassLoader loader;

    static {
        settings = new Properties();
        loader = Thread.currentThread().getContextClassLoader();

        try (InputStream in = loader.getResourceAsStream(file)) {
            settings.loadFromXML(in);
        } catch (IOException ioEx) {
            System.err.println(
                    "IOException encountered while reading from" + file);
            ioEx.printStackTrace();
        }
    }

    public static String getBotToken() {
        return settings.getProperty("token");
    }

    public static String getBotUsername() {
        return settings.getProperty("username");
    }

    public static String getInternalUrl() {
        return settings.getProperty("internal");
    }

    public static String getExternalUrl() {
        return settings.getProperty("external");
    }
}
