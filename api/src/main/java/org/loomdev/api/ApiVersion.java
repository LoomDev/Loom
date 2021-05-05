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
    V1_16_5R1("1.16.5", ReleaseType.RELEASE),
    v20W45A("20w45a", ReleaseType.SNAPSHOT),
    v20W46A("20w46a", ReleaseType.SNAPSHOT),
    v20w48A("20w48a", ReleaseType.SNAPSHOT),
    v20w49A("20w49a", ReleaseType.SNAPSHOT),
    v20w51A("20w51a", ReleaseType.SNAPSHOT),
    v21w03A("21w03a", ReleaseType.SNAPSHOT),
    v21w05A("21w05a", ReleaseType.SNAPSHOT),
    v21w05B("21w05b", ReleaseType.SNAPSHOT),
    v21w06A("21w06a", ReleaseType.SNAPSHOT),
    v21w07A("21w07a", ReleaseType.SNAPSHOT),
    v21w08A("21w08a", ReleaseType.SNAPSHOT),
    v21w08B("21w08b", ReleaseType.SNAPSHOT),
    V21W10A("21w10a", ReleaseType.SNAPSHOT),
    V21W11A("21w11a", ReleaseType.SNAPSHOT),
    V21W13A("21w13a", ReleaseType.SNAPSHOT),
    V21W14A("21w14a", ReleaseType.SNAPSHOT),
    V21W15A("21w15a", ReleaseType.SNAPSHOT),
    V21W16A("21w16a", ReleaseType.SNAPSHOT),
    V21W17A("21w17a", ReleaseType.SNAPSHOT),
    V21W18A("21w18a", ReleaseType.SNAPSHOT);

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

    /**
     * Gets whether the version is newer than another version.
     *
     * @param other The other version.
     * @return {@code true} if the version is newer than the other version.
     */
    public boolean isNewerThan(ApiVersion other) {
        return this.ordinal() > other.ordinal();
    }

    /**
     * Gets whether the version is newer than or equal to another version.
     *
     * @param other The other version.
     * @return {@code true} if the version is at least the other version.
     */
    public boolean isAtLeast(ApiVersion other) {
        return this.ordinal() >= other.ordinal();
    }

    /**
     * Gets whether the version is older than another version.
     *
     * @param other The other version.
     * @return {@code true} if the version is older than the other version.
     */
    public boolean isOlderThan(ApiVersion other) {
        return this.ordinal() < other.ordinal();
    }

    /**
     * Gets whether the version is older than or equal to another version.
     *
     * @param other The other version.
     * @return {@code true} if the version is at most the other version.
     */
    public boolean isAtMost(ApiVersion other) {
        return this.ordinal() <= other.ordinal();
    }

    /**
     * Gets an api version by its name.
     *
     * @param name The name.
     * @return The version.
     */
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
