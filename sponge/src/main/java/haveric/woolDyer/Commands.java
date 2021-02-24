package haveric.woolDyer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;


public class Commands {

    private WoolDyer plugin;

    public Commands(WoolDyer woolDyer) {
        plugin = woolDyer;

        registerBaseCommand();
    }

    private void registerBaseCommand() {
        final TextColor msgColor = TextColors.DARK_AQUA;
/*
        final Message title = Messages.builder("[").color(msgColor).append(
                Messages.builder(plugin.getName()).color(TextColors.GRAY).build()).append(
                Messages.builder("] ").color(msgColor).build())
            .build();
*/
        CommandCallable callable = new CommandCallable() {

            @Override
            public List<String> getSuggestions(CommandSource source, String arguments) throws CommandException {
                return new ArrayList<String>();
            }

            @Override
            public CommandResult process(CommandSource source, String args) throws CommandException {
                source.sendMessage(Text.of(msgColor, "github.com/haveric/WoolDyer - v" + plugin.getVersion()));
                return CommandResult.success();
            }
            /*
            @Override
            public boolean call(CommandSource source, String arguments, List<String> parents) throws CommandException {
                Message infoLine = Messages.builder("github.com/haveric/WoolDyer - v" + plugin.getVersion()).color(msgColor).build();

                source.sendMessage(Messages.builder().append(title, infoLine).build());


                return true;
            }
*/
            @Override
            public boolean testPermission(CommandSource source) {

                return false;
            }

            @Override
            public Optional<Text> getHelp(CommandSource arg0) {
                return Optional.empty();
            }
            @Override
            public Optional<Text> getShortDescription(CommandSource arg0) {
                return Optional.empty();
            }
            @Override
            public Text getUsage(CommandSource arg0) {
                return Text.of("");
            }
        };

        List<String> aliases = new ArrayList<String>();
        aliases.add("wooldyer");
        aliases.add("wd");

        Sponge.getCommandManager().register(plugin, callable, aliases);
    }
}
