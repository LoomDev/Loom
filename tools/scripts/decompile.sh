#!/usr/bin/env bash

(
set -e
PS1="$"
basedir="$(cd "$1" && pwd -P)"
toolsdir="$basedir/tools"
yarndir="$toolsdir/yarn"
decompdir="$toolsdir/decomp"

mcVersion="20w45a"
mcServerJar="https://launcher.mojang.com/v1/objects/043ec38297d0ec58abd6f636bc92f5664a8ccecb/server.jar"
mcServerMappings="https://launcher.mojang.com/v1/objects/e13520140ed6bdbe2ca05f59ce12700e9081a8cf/server.txt"

function setup {
    git submodule update --init --recursive

    echo "Downloading vanilla server jar and Mojang mappings."
    cd "$toolsdir/decomp"
    wget $mcServerJar
    wget $mcServerMappings

    echo "Mapping vanilla server jar with Mojang mappings."
    java -jar $toolsdir/reconstruct-cli-1.3.2.jar -jar server.jar -mapping server.txt -output server-deobf.jar -exclude "com.google.,com.mojang.,io.netty.,it.unimi.dsi.fastutil.,javax.,joptsimple.,org.apache."
    mvn install:install-file -Dfile="server-deobf.jar" -DgroupId="org.loomdev" -DartifactId="minecraft-server" -Dversion="$mcVersion-SNAPSHOT" -Dpackaging="jar"
}

function decomp {
    echo "Extracting mapped Minecraft source."
    mkdir -p "$decompdir/extracted"
    cd "$decompdir/extracted"
    unzip -o "$decompdir/server-deobf.jar" "net/**/*" || exit 1

    echo "Decompiling mapped Minecraft source."
    mkdir -p "$decompdir/decompiled"
    cd "$decompdir/decompiled"
    java -jar "$toolsdir/fernflower.jar" -dgs=1 -hdc=0 -rbr=0 -asc=1 -udv=0 "$decompdir/extracted" "$decompdir/decompiled"
}

setup
decomp
) || exit 1
