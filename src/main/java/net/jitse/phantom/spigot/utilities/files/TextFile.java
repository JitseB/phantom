package net.jitse.phantom.spigot.utilities.files;

import net.jitse.phantom.spigot_old.logging.SpigotLogger;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public class TextFile {

    private final JavaPlugin plugin;
    private final String name;
    private final File file;

    public TextFile(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;
        this.file = new File(plugin.getDataFolder(), name + ".txt");
    }

    /**
     * Creates the text file in the plugin's data folder.
     *
     * @return Whether the file was created successfully. (Auto returns true if file exists.)
     */
    public boolean createIfNotExists() {
        if (file.exists()) {
            return true;
        }

        // Create file.
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();

            try {
                InputStream initialStream = plugin.getResource(name + ".txt");
                byte[] buffer = new byte[initialStream.available()];
                initialStream.read(buffer);
                OutputStream outStream = new FileOutputStream(file);
                outStream.write(buffer);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } catch (IOException exception) {
            SpigotLogger.log(plugin, SpigotLogger.LogLevel.ERROR, "Could not create \"" + name + "\" file. " + exception.getMessage());
            return false;
        }

        return true;
    }
}
