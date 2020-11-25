#!/usr/bin/env bash

(
set -e
PS1="$"
basedir="$(cd "$1" && pwd -P)"
toolsdir="$basedir/tools"
yarndir="$toolsdir/yarn"
decompdir="$toolsdir/decomp"

mcVersion="$2"
mcServerJar="https://launcher.mojang.com/v1/objects/a14d24f89d5a4ec7521b91909caf4fee89c997f4/server.jar"
mcServerMappings="https://launcher.mojang.com/v1/objects/a54f1c40e3b9a63a7a23fc268df7e79c2cb7e6cb/server.txt"

function setup {
    git submodule update --init --recursive

    echo "Downloading vanilla server jar and Mojang mappings."
    mkdir -p "$toolsdir/decomp"
    cd "$toolsdir/decomp"
    curl -O $mcServerJar
    curl -O $mcServerMappings

    echo "Mapping vanilla server jar with Mojang mappings."
    java -jar $toolsdir/reconstruct-cli-1.3.2.jar -jar server.jar -mapping server.txt -output server-deobf.jar -exclude "com.google.,com.mojang.,io.netty.,it.unimi.dsi.fastutil.,javax.,joptsimple.,org.apache." -agree
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
