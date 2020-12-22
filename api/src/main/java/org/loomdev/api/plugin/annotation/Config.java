package org.loomdev.api.plugin.annotation;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.loomdev.api.config.Configuration;

/**
 * This annotation injects an {@link Configuration} instance.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@BindingAnnotation
public @interface Config {

    /**
     * The path of the configuration relative to the plugin directory.
     *
     * @return The path.
     */
    String path();

    /**
     * Should the default config be copied if configuration file is missing.
     *
     * @return {@code true} if the default config should be copied.
     */
    boolean copyDefault() default true;

    /**
     * The path of the default configuration file relative to the root of the plugin jar.
     *
     * When string is empty the value of {@link Config#path} will be used.
     *
     * @return The path to the default configuration.
     */
    String defaultPath() default "";
}
