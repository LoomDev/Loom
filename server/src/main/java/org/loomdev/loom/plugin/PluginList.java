package org.loomdev.loom.plugin;

import org.loomdev.api.plugin.PluginState;
import org.loomdev.api.plugin.metadata.PluginDependency;
import org.loomdev.api.plugin.metadata.PluginMetadata;
import org.loomdev.loom.plugin.exceptions.PluginAlreadyTrackedException;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PluginList {

    private final Set<String> trackedPlugins;
    private final Map<String, PluginContainer> containerById;
    private final DependencyGraph dependencyGraph;

    public PluginList() {
        this.trackedPlugins = new HashSet<>();
        this.containerById = new HashMap<>();
        this.dependencyGraph = new DependencyGraph();
    }

    public void add(PluginContainer pluginContainer) {
        var metadata = pluginContainer.getMetadata();
        var id = metadata.getId();

        if (trackedPlugins.contains(id)) {
            throw new PluginAlreadyTrackedException(id);
        }

        this.trackedPlugins.add(id);
        this.containerById.put(id, pluginContainer);

        this.dependencyGraph.addNode(
                id,
                Arrays.stream(metadata.getDependencies())
                        .collect(Collectors.toMap(
                                PluginDependency::getId,
                                dep -> dep.isRequired() ? DependencyGraph.RelationType.REQUIRED
                                                        : DependencyGraph.RelationType.OPTIONAL
                        ))
        );
    }

    public Optional<PluginContainer> getContainer(String pluginId) {
        var container = this.containerById.get(pluginId);
        return Optional.ofNullable(container);
    }

    public boolean contains(String pluginId) {
        return this.containerById.containsKey(pluginId);
    }

    public void forEach(Consumer<String> idConsumer) {
        containerById.keySet().forEach(idConsumer);
    }

    public Stream<PluginMetadata> getAllMetadata() {
        return containerById.values().stream().map(PluginContainer::getMetadata);
    }

    public Stream<PluginContainer> all() {
        return containerById.values().stream();
    }

    public Set<String> getAllPluginIds() {
        return containerById.keySet();
    }

    public DependencyGraph getDependencyGraph() {
        return dependencyGraph;
    }

    public void clear() {
        trackedPlugins.clear();
        containerById.clear();
        dependencyGraph.clear();
    }
}
