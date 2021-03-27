package one.tika.tide.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

// Some color/message utils
public class Hue {
    private final JavaPlugin plugin;

    public Hue(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void print(String... rawString) {
        for (String string : rawString) {
            plugin.getServer().getConsoleSender().sendMessage(colorize(string));
        }
    }

    public static void message(Player player, String... messages) {
        for (String message : messages)
            player.sendMessage(parse(message));
    }

    // TODO: Add default formatting etc..
    public static String parse(String raw) {
        return colorize(raw);
    }

    public static String colorize(String raw) {
        return ChatColor.translateAlternateColorCodes('&', raw);
    }

    public static String getContrast(String initial) {
        Map<String, String> opposites = CollectionUtil.map(
                "&1", "&9",
                "&2", "&a",
                "&3", "&b",
                "&4", "&c",
                "&5", "&d",
                "&6", "&e",
                "&7", "&8",
                "&f", "&7",

                "&9", "&1",
                "&a", "&2",
                "&b", "&3",
                "&c", "&4",
                "&d", "&5",
                "&e", "&6",
                "&8", "&7",
                "&7", "&f"
        );

        return opposites.get(initial);
    }

    public static String stringifyList(List<String> list) {
        return String.join(", ", list);
    }

    public static ChatColor getColor(String coloredRaw) {
        return ChatColor.getByChar(ChatColor.stripColor(coloredRaw).substring(0, 2));
    }

    private final static List<String> trueList = Arrays.asList(
            "yes",
            "y",
            "true",
            "on"
    );

    private final static List<String> falseList = Arrays.asList(
            "no",
            "n",
            "false",
            "off"
    );

    public static boolean isBoolString(String raw) {
        return falseList.stream().anyMatch(it -> it.equalsIgnoreCase(raw))
                || trueList.stream().anyMatch(it -> it.equalsIgnoreCase(raw));
    }

    public static boolean isTrueString(String raw) {
        return trueList.stream().anyMatch(it -> it.equalsIgnoreCase(raw));
    }

    public static Hue get(JavaPlugin plugin) {
        return new Hue(plugin);
    }
}
