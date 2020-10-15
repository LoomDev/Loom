package org.loomdev.api;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum ApiVersion {

    UNKNOWN("unknown", false),
    v1_16_1R1("1.16.1-R1", true),
    v1_16_2R1("1.16.2-R1", true),
    v1_16_3RC1("1.16.3-RC1", false),
    v1_16_3R1("1.16.3-R1", true),
    v1_16_4PRE1("1.16.4-PRE1", false);

    public static final ApiVersion LATEST = values()[ApiVersion.values().length - 1];
    public static final ApiVersion LATEST_RELEASE = Arrays.stream(values())
            .filter(v -> !v.isRelease())
            .reduce((a, b) -> b).get();
    public static final ApiVersion LATEST_SNAPSHOT = Arrays.stream(values())
            .filter(ApiVersion::isRelease)
            .reduce((a, b) -> b).get();

    private static final Map<String, ApiVersion> NAME_VERSION_MAP = Arrays.stream(values()).collect(Collectors.toMap(ApiVersion::getName, apiVersion -> apiVersion));
    private final String name;
    private final boolean release;

    ApiVersion(String name, boolean release) {
        this.name = name;
        this.release = release;
    }

    public String getName() {
        return name;
    }

    public boolean isRelease() {
        return release;
    }

    public boolean isLatest() {
        return this == LATEST;
    }

    public boolean isNewerThan(ApiVersion other) {
        return this.ordinal() > other.ordinal();
    }

    public boolean isAtLeast(ApiVersion other) {
        return this.ordinal() >= other.ordinal();
    }

    public boolean isOlderThan(ApiVersion other) {
        return this.ordinal() < other.ordinal();
    }

    public boolean isAtMost(ApiVersion other) {
        return this.ordinal() <= other.ordinal();
    }

    public static ApiVersion getByName(String name) {
        return NAME_VERSION_MAP.get(name);
    }

    public enum ReleaseType {
        SNAP,
        PRE,
        RC,
        RELEASE,
    }
}
