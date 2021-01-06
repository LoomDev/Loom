#!/bin/bash

mcVersion=$(cat ".loomversion")

buildJar() {
    printf "Building distributable Loom server jar for Minecraft $mcVersion...\n"
    ./gradlew build || exit 1
    cp "launcher/build/libs/loom-launcher-$mcVersion.jar" "./loom-launcher-$mcVersion.jar"

    printf "Done!"
}

buildJar
