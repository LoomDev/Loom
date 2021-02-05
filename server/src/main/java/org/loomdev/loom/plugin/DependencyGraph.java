package org.loomdev.loom.plugin;

import com.google.common.collect.Maps;

import java.util.*;
import java.util.stream.Collectors;

public class DependencyGraph {

    private final Map<String, Node> nodes;

    public DependencyGraph() {
        this.nodes = new HashMap<>();
    }

    public void addNode(String id, Map<String, RelationType> dependencies) {
        var node = nodes.getOrDefault(id, new Node(id));
        node.setPresence(Presence.PRESENT);

        for (var dependency : dependencies.entrySet()) {
            var dependencyId = dependency.getKey();
            var dependencyNode = nodes.getOrDefault(dependencyId, new Node(dependencyId));
            dependencyNode.addDependant(id, dependency.getValue());
            nodes.put(dependencyId, dependencyNode);

            node.addDependency(dependency.getKey(), dependency.getValue());
        }

        nodes.put(id, node);
    }

    public List<Node> getLoadingOrder() {
        var stack = new Stack<Node>();

        var visited = new ArrayList<Node>();
        for (var node : nodes.values()) {
            if (!visited.contains(node)) {
                dfs(node, visited, stack);
            }
        }

        var list = new ArrayList<Node>();
        while (!stack.empty()) {
            list.add(stack.pop());
        }

        return list;
    }

    public void dfs(Node current, List<Node> visited, Stack<Node> stack) {
        visited.add(current);

        for (String dependencyId : current.getDependantIds()) {
            var next = nodes.get(dependencyId);

            if (!visited.contains(next)) {
                dfs(next, visited, stack);
            }
        }

        stack.push(current);
    }

    public void clear() {
        nodes.clear();
    }

    public static class Node {

        private final String id;

        private Presence presence;

        /**
         * Plugins this plugin depends on
         */
        private final Map<String, RelationType> dependencies;

        /**
         * Plugins dependant on this plugin
         */
        private final Map<String, RelationType> dependants;

        private Node(String id) {
            this.id = id;
            this.presence = Presence.ABSENT;
            this.dependencies = Maps.newHashMap();
            this.dependants = Maps.newHashMap();
        }

        public Presence getPresence() {
            return presence;
        }

        public void setPresence(Presence presence) {
            this.presence = presence;
        }

        public void addDependency(String dependencyId, RelationType relationType) {
            this.dependencies.put(dependencyId, relationType);
        }

        public void addDependant(String dependantId, RelationType relationType) {
            this.dependants.put(dependantId, relationType);
        }

        public String getId() {
            return id;
        }

        public Set<String> getDependantIds() {
            return this.dependants.keySet();
        }

        public List<String> getRequiredDependencies() {
            return this.dependencies.entrySet()
                    .stream()
                    .filter(kvp -> kvp.getValue() == RelationType.REQUIRED)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }
    }

    public enum Presence {
        PRESENT,
        ABSENT
    }

    public enum RelationType {
        REQUIRED,
        OPTIONAL
    }
}
