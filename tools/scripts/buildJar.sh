#!/bin/bash

mcVersion=$(cat ".loomversion")

buildJar() {
    printf "Building distributable Loom server jar for Minecraft $mcVersion...\n"
    rootPath=$(pwd)

    cd "tools/Paperclip"
    mvn clean package "-Dmcver=$mcVersion" "-Dpaperjar=$rootPath/server/target/loom-$mcVersion.jar" "-Dvanillajar=$rootPath/.cache/$mcVersion/server.jar" || exit 1
    cp "assembly/target/paperclip-$mcVersion.jar" "$rootPath/loom-$mcVersion.jar"
    cd ../..

    printf "Done!"
}

buildJar
