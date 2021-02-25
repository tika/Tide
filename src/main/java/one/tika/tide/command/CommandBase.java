package one.tika.tide.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CommandBase implements CommandExecutor, TabCompleter {

    public abstract String getName();
    public abstract List<SubcommandBase> getSubcommands();

    public abstract void perform(CommandSender sender, org.bukkit.command.Command command, String label, String[] args);

    @Override
    public final boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        // If subcommands exist && args exist
        if (getSubcommands() != null && getSubcommands().size() > 0 && args.length > 0) {
            for (SubcommandBase subcommand : getSubcommands()) {
                if (args[0].equalsIgnoreCase(subcommand.getName()) ||
                        Arrays.stream(subcommand.getAliases()).anyMatch(name -> args[0].equalsIgnoreCase(name)))
                    subcommand.perform(sender, args);
            }
        } else perform(sender, command, label, args);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 0) return null;
        if (args.length == 1) return getSubcommands().stream().map(SubcommandBase::getName).collect(Collectors.toList());

        for (SubcommandBase subcommand : getSubcommands()){
            if (args[0].equalsIgnoreCase(subcommand.getName()))
                return subcommand.getParameters(sender, args);
        }

        return null;
    }
}