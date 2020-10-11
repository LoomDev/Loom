package org.loomdev.api.plugin.ap;

import com.google.gson.Gson;
import org.loomdev.api.plugin.annotation.LoomPlugin;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;
import java.util.Set;

@SupportedAnnotationTypes({"org.loomdev.api.plugin.annotation.LoomPlugin"})
public class PluginAnnotationProcessor extends AbstractProcessor {

    private ProcessingEnvironment environment;
    private String pluginClassFound;
    private boolean warnedAboutMultiplePlugins;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        this.environment = processingEnv;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            return false;
        }

        for (Element element : roundEnv.getElementsAnnotatedWith(LoomPlugin.class)) {
            if (element.getKind() != ElementKind.CLASS) {
                environment.getMessager().printMessage(Diagnostic.Kind.ERROR, "Only classes can be annotated with " + LoomPlugin.class.getCanonicalName());
                return false;
            }

            Name qualifiedName = ((TypeElement) element).getQualifiedName();

            if (Objects.equals(pluginClassFound, qualifiedName.toString())) {
                if (!warnedAboutMultiplePlugins) {
                    environment.getMessager()
                            .printMessage(Diagnostic.Kind.WARNING, "Loom does not yet currently support "
                                    + "multiple plugins. We are using " + pluginClassFound
                                    + " for your plugin's main class.");
                    warnedAboutMultiplePlugins = true;
                }
                return false;
            }

            LoomPlugin loomPlugin = element.getAnnotation(LoomPlugin.class);
            if (!SerializedPluginMetadata.ID_PATTERN.matcher(loomPlugin.id()).matches()) {
                environment.getMessager().printMessage(Diagnostic.Kind.ERROR, "Invalid ID for plugin "
                        + qualifiedName
                        + ". IDs must start alphabetically, have alphanumeric characters, and can "
                        + "contain dashes or underscores.");
                return false;
            }

            SerializedPluginMetadata metadata = SerializedPluginMetadata.from(loomPlugin, qualifiedName.toString());
            try {
                FileObject object = environment.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", "loom.json");
                try (Writer writer = new BufferedWriter((object.openWriter()))) {
                    new Gson().toJson(metadata, writer);
                }
                pluginClassFound = qualifiedName.toString();
            } catch (IOException e) {
                environment.getMessager().printMessage(Diagnostic.Kind.ERROR, "Unable to generate plugin file.");
            }
        }
        return false;
    }
}
