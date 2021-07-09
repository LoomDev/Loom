package org.loomdev.ap.plugin.lifecycle;

import org.loomdev.api.plugin.lifecycle.LifecycleEvent;
import org.loomdev.api.plugin.lifecycle.LifecycleHook;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("org.loomdev.api.plugin.lifecycle.LifecycleHook")
@SupportedSourceVersion(SourceVersion.RELEASE_16)
public class LifecycleProcessor extends AbstractProcessor {

    private static final String LifecycleEventCanonicalName = LifecycleEvent.class.getCanonicalName();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        var messager = this.processingEnv.getMessager();
        var annotatedElements = roundEnv.getElementsAnnotatedWith(LifecycleHook.class);

        for (Element element : annotatedElements) {
            if (element.getKind() != ElementKind.METHOD) {
                messager.printMessage(Diagnostic.Kind.ERROR, "@LifecycleHook annotation can only be used on methods.", element);
                continue;
            }

            var method = (ExecutableElement) element;
            if (!method.getModifiers().contains(Modifier.PUBLIC)) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Lifecycle methods should be public.", element);
            }

            if (method.getModifiers().contains(Modifier.ABSTRACT)) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Lifecycle methods should not be abstract.", element);
            }

            if (method.getModifiers().contains(Modifier.STATIC)) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Lifecycle methods should not be static.", element);
            }

            if (method.getEnclosingElement().getKind().isInterface()) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Lifecycle methods cannot be defined of an interface.", element);
            }

            if (method.getReturnType().getKind() != TypeKind.VOID) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Lifecycle methods should return void.", element);
            }

            var parameters = method.getParameters();
            if (parameters.size() != 1 || !isLifecycleEvent(parameters.get(0))) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Lifecycle methods should only have one parameter of type LifecycleEvent.", element);
            }
        }

        return false;
    }

    private boolean isLifecycleEvent(Element element) {
        var elements = this.processingEnv.getElementUtils();
        var types = this.processingEnv.getTypeUtils();

        var lifecycleEvent = elements.getTypeElement(LifecycleEventCanonicalName).asType();
        return types.isAssignable(element.asType(), lifecycleEvent);
    }
}
