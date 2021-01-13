package org.loomdev.api;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public enum ApiVersion {

    UNKNOWN("unknown", ReleaseType.UNKNOWN),
    v1_16_1R1("1.16.1-R1", ReleaseType.RELEASE),
    v1_16_2R1("1.16.2-R1", ReleaseType.RELEASE),
    v1_16_3RC1("1.16.3-RC1", ReleaseType.RELEASE_CANDIDATE),
    v1_16_3R1("1.16.3-R1", ReleaseType.RELEASE),
    v1_16_4PRE1("1.16.4-PRE1", ReleaseType.PRE_RELEASE),
    v1_16_4PRE2("1.16.4-PRE2", ReleaseType.PRE_RELEASE),
    v1_16_4RC1("1.16.4-RC1", ReleaseType.RELEASE_CANDIDATE),
    v1_16_4R1("1.16.4", ReleaseType.RELEASE),
    V1_16_5RC1("1.16.5-RC1", ReleaseType.RELEASE_CANDIDATE),
    v20W45A("20w45a", ReleaseType.SNAPSHOT),
    v20W46A("20w46a", ReleaseType.SNAPSHOT),
    v20w48A("20w48a", ReleaseType.SNAPSHOT),
    v20w49A("20w49a", ReleaseType.SNAPSHOT);

    public static final ApiVersion LATEST = values()[ApiVersion.values().length - 1];
    public static final ApiVersion LATEST_SNAPSHOT = getLatestByType(ReleaseType.SNAPSHOT);
    public static final ApiVersion LATEST_PRE_RELEASE = getLatestByType(ReleaseType.PRE_RELEASE);
    public static final ApiVersion LATEST_RELEASE_CANDIDATE = getLatestByType(ReleaseType.RELEASE_CANDIDATE);
    public static final ApiVersion LATEST_RELEASE = getLatestByType(ReleaseType.RELEASE);

    private static final Map<String, ApiVersion> NAME_VERSION_MAP = Arrays.stream(values()).collect(Collectors.toMap(ApiVersion::getName, apiVersion -> apiVersion));
    private final String name;
    private final ReleaseType releaseType;

    ApiVersion(String name, ReleaseType releaseType) {
        this.name = name;
        this.releaseType = releaseType;
    }

    public String getName() {
        return name;
    }

    public boolean isSnapshot() {
        return releaseType == ReleaseType.SNAPSHOT;
    }

    public boolean isPreRelease() {
        return releaseType == ReleaseType.PRE_RELEASE;
    }

    public boolean isReleaseCandidate() {
        return releaseType == ReleaseType.RELEASE_CANDIDATE;
    }

    public boolean isRelease() {
        return releaseType == ReleaseType.RELEASE;
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

    private static ApiVersion getLatestByType(ReleaseType type) {
        return Arrays.stream(values())
                .filter(apiVersion -> apiVersion.releaseType == type)
                .sorted(Comparator.comparingInt(Enum::ordinal))
                .sorted(Comparator.reverseOrder())
                .reduce((a, b) -> b)
                .orElse(ApiVersion.UNKNOWN);
    }

    public enum ReleaseType {
        UNKNOWN,
        SNAPSHOT,
        PRE_RELEASE,
        RELEASE_CANDIDATE,
        RELEASE,
    }
}
