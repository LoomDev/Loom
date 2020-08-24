#!/usr/bin/env bash

(
set -e
PS1="$"
basedir="$(cd "$1" && pwd -P)"
toolsdir="$basedir/tools"
yarndir="$toolsdir/yarn"
decompdir="$toolsdir/decomp"
fernflower="https://hub.spigotmc.org/stash/projects/SPIGOT/repos/builddata/raw/bin/fernflower.jar"
mcVersion="1.16.2"

function setup {
    git submodule update --init --recursive

    if [ ! -f "$toolsdir/fernflower.jar" ]; then
        echo "Downloading necessary libraries."
        cd "$toolsdir"
        wget "$fernflower"
    fi

    echo "Mapping vanilla server jar."
    cp "$toolsdir/build-patched.gradle" "$yarndir/build.gradle" || exit 1
    rm -rf "$toolsdir/decomp"
    mkdir -p "$toolsdir/decomp"
    cd "$yarndir"
    eval "./gradlew mapNamedJar" || exit 1
    cp "$yarndir/$mcVersion-named.jar" "$toolsdir/decomp" || exit 1

    mvn install:install-file -Dfile="$toolsdir/decomp/$mcVersion-named.jar" -DgroupId="org.loomdev" -DartifactId="minecraft-server" -Dversion="$mcVersion-SNAPSHOT" -Dpackaging="jar"
}

function decomp {
    echo "Extracting mapped Minecraft source."
    mkdir -p "$decompdir/extracted"
    cd "$decompdir/extracted"
    unzip -o "$decompdir/$mcVersion-named.jar" "net/minecraft/**/*" || exit 1

    echo "Decompiling mapped Minecraft source."
    mkdir -p "$decompdir/decompiled"
    cd "$decompdir/decompiled"
    java -jar "$toolsdir/fernflower.jar" -dgs=1 -hdc=0 -rbr=0 -asc=1 -udv=0 "$decompdir/extracted" "$decompdir/decompiled"
}

setup
decomp
) || exit 1
