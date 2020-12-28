package org.loomdev.loom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.Completer;
import org.jline.reader.ParsedLine;
import org.jline.reader.Candidate;
import net.minecrell.terminalconsole.SimpleTerminalConsole;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.commands.CommandSourceStack;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.suggestion.Suggestions;

import java.util.List;
import java.nio.file.Paths;

public class LoomConsole extends SimpleTerminalConsole {

    private static final Logger LOGGER = LogManager.getLogger();
    private DedicatedServer server;

    public LoomConsole(DedicatedServer server) {
        this.server = server;
    }

    @Override
    protected LineReader buildReader(LineReaderBuilder builder) {
        return super.buildReader(builder
                .appName("Server")
                .variable(LineReader.HISTORY_FILE, Paths.get(".command_history"))
                .completer(new CommandCompleter())
        );
    }

    @Override
    protected boolean isRunning() {
        return server.isRunning() && !server.isStopped();
    }

    @Override
    protected void runCommand(String command) {
        server.handleConsoleInput(command, server.createCommandSourceStack());
    }

    @Override
    protected void shutdown() {
        server.halt(false);
    }

    public class CommandCompleter implements Completer {

        @Override
        public void complete(LineReader reader, ParsedLine line, List<Candidate> candidates) {
            StringReader stringReader = new StringReader(line.line());

            try {
                ParseResults<CommandSourceStack> results = server.getCommands().getDispatcher().parse(stringReader, server.createCommandSourceStack());
                Suggestions suggestions = server.getCommands().getDispatcher().getCompletionSuggestions(results).get();
                for (Suggestion suggestion : suggestions.getList()) {
                    String text = suggestion.getText();
                    if(!text.isEmpty()) {
                        candidates.add(new Candidate(text));
                    }
                }
            } catch (Throwable error) {
                LOGGER.error("Error finding command completions", error);
            }
        }

    }

}
