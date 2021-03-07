package one.tika.tide.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class SubcommandBase {
    public abstract String getName();
    public abstract String getDescription();
    public abstract String[] getAliases();
    public abstract void perform(CommandSender sender, String[] args);
    public abstract List<String> getParameters(CommandSender sender, String[] args);
}