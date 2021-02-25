package one.tika.tide;

import one.tika.tide.listeners.MenuListener;
import one.tika.tide.utils.Hue;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class Tide extends JavaPlugin {
    private static Tide instance;

    @Override
    public void onEnable() {
        instance = this;

        getServer().getPluginManager().registerEvents(new MenuListener(), this);

        Hue.get(this).print(
                "&bTide &8> &7Loaded Version &b" + getDescription().getVersion(),
                "&bTide &8> &7Loaded &b" + getDepending() + "&7 plugins depending Tide"
        );
    }

    private int getDepending() {
        return (int) Arrays.stream(getServer()
                .getPluginManager()
                .getPlugins())
                .filter(pl -> pl.getDescription().getDepend().contains(getDescription().getName()))
                .count();
    }

    public static String getVersion() {
        return instance.getDescription().getVersion();
    }
}
