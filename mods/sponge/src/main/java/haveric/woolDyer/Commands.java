package haveric.woolDyer;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;

import com.google.common.base.Optional;

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
            public Optional<CommandResult> process(CommandSource source, String args) throws CommandException {
                source.sendMessage(Texts.of(msgColor, "github.com/haveric/WoolDyer - v" + plugin.getVersion()));
                return Optional.of(CommandResult.success());
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
                return Optional.absent();
            }
            @Override
            public Optional<Text> getShortDescription(CommandSource arg0) {
                return Optional.absent();
            }
            @Override
            public Text getUsage(CommandSource arg0) {
                return Texts.of("");
            }
        };

        List<String> aliases = new ArrayList<String>();
        aliases.add("wooldyer");
        aliases.add("wd");

        plugin.getGame().getCommandDispatcher().register(plugin, callable, aliases);
    }
}
