package org.loomdev.ap.plugin;

import org.loomdev.api.plugin.Dependency;
import org.loomdev.api.plugin.Plugin;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.StandardLocation;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;

@SupportedAnnotationTypes("org.loomdev.api.plugin.Plugin")
@SupportedSourceVersion(SourceVersion.RELEASE_16)
public class PluginProcessor extends AbstractProcessor {

    private static final Pattern PLUGIN_ID_REGEX = Pattern.compile("^[a-z0-9_-]*$");
    public static final String LOOM_JAR_FILE = "loom.yml";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) return false;

        Messager messager = this.processingEnv.getMessager();
        var annotatedElements = roundEnv.getElementsAnnotatedWith(Plugin.class);

        // If possible there should also be a check here to make sure the Plugin annotation is used
        // Currently I could not find a way to make this work.

        if (annotatedElements.size() > 1) {
            messager.printMessage(Diagnostic.Kind.ERROR, "Only one @Plugin annotation should be present in the project.");
            return false;
        }

        var element = annotatedElements.iterator().next();
        var annotation = element.getAnnotation(Plugin.class);

        if (!isValidPluginIdFormat(annotation.id())) {
            messager.printMessage(Diagnostic.Kind.ERROR, "Invalid plugin id. Ids can only contain lowercase alphanumeric characters, underscores ('_') and hyphens ('-').", element);
            return false;
        }

        if (isReservedPluginId(annotation.id())) {
            messager.printMessage(Diagnostic.Kind.ERROR, "Invalid plugin id. Plugin id cannot be 'loom' or 'minecraft' these are reserved.", element);
            return false;
        }

        var mainClass = ((TypeElement) element).getQualifiedName().toString();
        var serializable = new SerializablePluginMetadata(
                mainClass,
                annotation.id(),
                annotation.name(),
                annotation.version(),
                annotation.description(),
                annotation.authors(),
                mapDependencies(annotation.dependencies()),
                annotation.minimumApiVersion().getName()
        );

        try {
            var resource = this.processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", LOOM_JAR_FILE);
            try (var writer = new BufferedWriter((resource.openWriter()))) {
                new Yaml().dump(serializable, writer);
            }
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, "Unable to generate plugin file.");
        }

        return false;
    }

    private boolean isValidPluginIdFormat(String pluginId) {
        var matcher = PLUGIN_ID_REGEX.matcher(pluginId);
        return matcher.matches();
    }

    private boolean isReservedPluginId(String pluginId) {
        return pluginId.equalsIgnoreCase("loom") || pluginId.equalsIgnoreCase("minecraft");
    }

    private SerializablePluginMetadata.Dependency[] mapDependencies(Dependency[] dependencies) {
        return Arrays.stream(dependencies)
                .map(dependency -> new SerializablePluginMetadata.Dependency(
                        dependency.id(),
                        dependency.version(),
                        dependency.required()
                ))
                .toArray(SerializablePluginMetadata.Dependency[]::new);
    }
}
