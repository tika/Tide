package one.tika.tide.data;

import one.tika.tide.utils.Hue;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

public class Config {
    private final JavaPlugin plugin;
    private FileConfiguration dataConfig = null;
    private final String fileName;
    private File configFile = null;

    public Config(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        saveDefaultConfig();
    }

    public void reloadConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), fileName);

        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.plugin.getResource(fileName);
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (this.dataConfig == null)
            reloadConfig();

        return this.dataConfig;
    }

    public void saveConfig() {
        if (this.dataConfig == null || this.configFile == null)
            return;

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save data to " + this.configFile, e);
        }
    }

    public void saveDefaultConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), fileName);

        if (!this.configFile.exists()) {
            this.plugin.saveResource(fileName, false);
        }
    }

    // Laziness means we don't want to do .getConfig(), so we'll do that here instead
    public void setValue(String path, Object value){
        getConfig().set(path,value);
    }

    public void addValue(String path, Object value){
        if(!getConfig().contains(path) )getConfig().set(path,value);
    }
    public String getStringValue(String path){
        return getConfig().getString(path);
    }

    public String getMessageValue(String path){
        return Hue.parse(getStringValue(path));
    }

    public int getIntValue(String path){
        return getConfig().getInt(path);
    }

    public double getDoubleValue(String path){
        return getConfig().getDouble(path);
    }

    public long getLongValue(String path){
        return getConfig().getLong(path);
    }

    public boolean getBooleanValue(String path){
        return getConfig().getBoolean(path);
    }

    public List<String> getStringListValue(String path){
        return getConfig().getStringList(path);
    }

    public List<Integer> getIntListValue(String path){
        return getConfig().getIntegerList(path);
    }

    public List<Double> getDoubleListValue(String path){
        return getConfig().getDoubleList(path);
    }

    public List<Long> getLongListValue(String path){
        return getConfig().getLongList(path);
    }

    public List<Boolean> getBooleanListValue(String path){
        return getConfig().getBooleanList(path);
    }

    public Object getValue(String path){
        return getConfig().getStringList(path);
    }

    public Collection<String> getKeys(String path){
        ConfigurationSection config = getConfig().getConfigurationSection(path);
        if(config != null) return config.getKeys(false);
        else return new ArrayList<>();
    }

    public Boolean contains(String path){
        return getConfig().contains(path);
    }

}
