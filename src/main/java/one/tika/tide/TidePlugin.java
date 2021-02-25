package one.tika.tide;

import one.tika.tide.command.CommandBase;
import one.tika.tide.utils.Hue;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class TidePlugin extends JavaPlugin {
    public void registerEvents(Listener... listeners) {
        for (Listener listener : listeners)
            getServer().getPluginManager().registerEvents(listener, this);
    }

    /**
     * Don't forget to register in plugin.yml!
     * @param commands
     */
    public void registerCommands(CommandBase... commands) {
        for (CommandBase command : commands)
            getCommand(command.getName()).setExecutor(command);
    }

    /**
     * Prints plugin information
     */
    public void prettyPrintDescription(String color) {
        String complement = Hue.getContrast(color);
        Hue.get(this).print(
                color + getName() + String.format(" &8(&7%s&8)", getDescription().getVersion()),
                "&8 - &7" + getDescription().getDescription(),
                "&8 - &7Author(s): " + complement + Hue.stringifyList(getDescription().getAuthors()),
                "&8 - &7Dependencies: " + complement + Hue.stringifyList(getDescription().getDepend()),
                "&8 - &7Built using &bTide &ov" + Tide.getVersion()
        );
    }
}
