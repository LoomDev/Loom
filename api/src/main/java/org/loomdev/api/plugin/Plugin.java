package org.loomdev.api.plugin;

import org.loomdev.api.ApiVersion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Plugin {

    String id();

    String name();

    String version();

    String description() default "";

    String[] authors() default {};

    Dependency[] dependencies() default {};

    ApiVersion minimumApiVersion() default ApiVersion.UNKNOWN;
}
