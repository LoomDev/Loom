package org.loomdev.loom;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import net.minecraft.server.Main;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.ApiVersion;
import org.loomdev.api.plugin.metadata.PluginDependency;
import org.loomdev.api.plugin.metadata.PluginMetadata;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Loom {

    public static final PluginMetadata LOOM_PLUGIN = new PluginMetadata() {
        @Override
        public @NotNull String getId() {
            return "loom";
        }

        @Override
        public @NotNull String getName() {
            return "Loom";
        }

        @Override
        public @NotNull String getMainClass() {
            return null;
        }

        @Override
        public @NotNull String getVersion() {
            return "";
        }

        @Override
        public @NotNull Optional<String> getDescription() {
            return Optional.empty();
        }

        @Override
        public @NotNull String[] getAuthors() {
            return new String[] { "The Loom Team" };
        }

        @Override
        public @NotNull PluginDependency[] getDependencies() {
            return new PluginDependency[0];
        }

        @Override
        public @NotNull ApiVersion getMinimumApiVersion() {
            return ApiVersion.LATEST;
        }

    };

    public static final Yaml YAML = new Yaml();

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
