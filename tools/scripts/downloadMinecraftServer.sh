#!/usr/bin/env bash

mcVersion=$(cat ".loomversion")

downloadVersionManifest() {
    printf "Downloading main version_manifest.json..."
    curl --create-dirs -s -o .cache/version_manifest.json "https://launchermeta.mojang.com/mc/game/version_manifest.json" || exit 1
    printf " Done!\n"
}

downloadTargettedVersion() {
    printf "Downloading $mcVersion version_manifest.json..."
    versionSpecificManifest=$(grep -o "https:\/\/launchermeta\.mojang\.com\/v1\/packages\/.\{40\}\/$mcVersion.json" .cache/version_manifest.json)
    curl --create-dirs -s -o ".cache/$mcVersion/version_manifest.json" "$versionSpecificManifest" || exit 1
    printf " Done!\n"

    printf "Downloading $mcVersion Minecraft server jar and mappings..."
    versionSpecificServerUri=$(grep -o "https:\/\/launcher\.mojang\.com\/v1\/objects\/.\{40\}\/server\.jar" .cache/$mcVersion/version_manifest.json)
    versionSpecificMappingsUri=$(grep -o "https:\/\/launcher\.mojang\.com\/v1\/objects\/.\{40\}\/server\.txt" .cache/$mcVersion/version_manifest.json)

    serverProGuard=".cache/$mcVersion/server.txt"
    serverTiny=".cache/$mcVersion/server.tiny"
    curl --create-dirs -s -o ".cache/$mcVersion/server.jar" "$versionSpecificServerUri" || exit 1
    curl --create-dirs -s -o $serverProGuard "$versionSpecificMappingsUri" || exit 1
    java -jar tools/enigma-cli-0.21.6+build.229-all.jar convert-mappings proguard $serverProGuard tinyv2:obf:deobf $serverTiny || exit 1
    rm $serverProGuard
    printf " Done!\n"
}

downloadVersionManifest
downloadTargettedVersion
