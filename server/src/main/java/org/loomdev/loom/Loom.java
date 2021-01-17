package org.loomdev.loom;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import net.minecraft.server.Main;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.ApiVersion;
import org.loomdev.api.plugin.PluginMetadata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Loom {

    public static final PluginMetadata LOOM_PLUGIN = new PluginMetadata() {
        @Override
        public @NotNull String getId() {
            return "loom";
        }

        @Override
        public @NotNull ApiVersion getMinimumApiVersion() {
            return ApiVersion.LATEST;
        }

        @Override
        public @NotNull Path getSource() {
            return null;
        }

        @Override
        public @NotNull String getMain() {
            return "";
        }

        @Override
        public State getState() {
            return State.ENABLED;
        }
    };

    public static void main(@NotNull String[] args) {
        OptionParser parser = new OptionParser() {
            {
                acceptsAll(asList("nogui"), "Console only");
                acceptsAll(asList("initSettings"), "Initializes 'server.properties' and 'eula.txt', then quits");
                acceptsAll(asList("demo"), "Run in demo mode");
                acceptsAll(asList("forceUpgrade"), "Whether to force a world upgrade");
                acceptsAll(asList("eraseCache"), "Whether to erase the cache during a world upgrade");
                acceptsAll(asList("safeMode"), "Loads level with vanilla datapack only");
                acceptsAll(asList("?", "help"), "Show the help");

                acceptsAll(asList("W", "world-dir", "universe", "world-container"), "World container")
                        .withRequiredArg()
                        .ofType(File.class)
                        .defaultsTo(new File("."))
                        .describedAs("Directory containing worlds");

                acceptsAll(asList("w", "world", "level-name"), "World name")
                        .withRequiredArg()
                        .ofType(String.class)
                        .describedAs("World name");

                acceptsAll(asList("h", "host", "server-ip"), "Host to listen on")
                        .withRequiredArg()
                        .ofType(String.class)
                        .describedAs("Hostname or IP");

                acceptsAll(asList("p", "port", "server-port"), "Port to listen on")
                        .withRequiredArg()
                        .ofType(Integer.class)
                        .describedAs("Port");

                acceptsAll(asList("P", "plugins"), "Plugin directory to use")
                        .withRequiredArg()
                        .ofType(File.class)
                        .defaultsTo(new File("plugins/"))
                        .describedAs("Plugin directory");

                acceptsAll(asList("c", "config"), "Properties file to use")
                        .withRequiredArg()
                        .ofType(File.class)
                        .defaultsTo(new File("server.properties"))
                        .describedAs("Properties file");
            }
        };

        OptionSet options = null;
        try {
            options = parser.parse(args);
        } catch (joptsimple.OptionException ex) {
            System.err.println(ex.getLocalizedMessage());
        }

        if (options == null || options.has("?")) {
            try {
                parser.printHelpOn(System.out);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(1);
        }

        System.out.println("Starting Minecraft server...");
        Main.main(options);
    }

    private static List<String> asList(@NotNull String... params) {
        return Arrays.asList(params);
    }
}
