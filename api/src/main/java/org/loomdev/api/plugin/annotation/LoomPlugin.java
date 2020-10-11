package org.loomdev.api.plugin.annotation;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.ApiVersion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to describe a Loom plugin.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LoomPlugin {

    /**
     * Id of the plugin. This id should be unique so it does not conflict with any other plugins and
     * should only contain alphanumeric characters, dashed and underscored.
     *
     * @return The id of the plugin.
     */
    @NotNull String id();

    /**
     * A human readable name of the plugin.
     *
     * @return The plugin name.
     */
    String name() default "";

    /**
     * The version of the plugin.
     *
     * @return The version of then plugin.
     */
    String version() default "";

    /**
     * A human readable description of the plugin.
     *
     * @return The description of the plugin.
     */
    String description() default "";

    /**
     * The author(s) of the plugin.
     *
     * @return The names of the author(s) of the plugin.
     */
    String[] authors() default "";

    /**
     * The dependencies required to load before this plugin.
     *
     * @return List of dependencies.
     */
    Dependency[] dependencies() default {};

    /**
     * The minimum api version required to run the plugin.
     *
     * @return The minimum api version
     */
    @NotNull ApiVersion minimumApiVersion() default ApiVersion.UNKNOWN; // should always be latest but ApiVersion.LATEST can't be used. UNKNOWN is overridden by the annotation processor to the latest version.

}
