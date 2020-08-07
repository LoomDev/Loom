#!/usr/bin/env bash

(
set -e
PS1="$"
basedir="$(cd "$1" && pwd -P)"
toolsdir="$basedir/tools"
yarndir="$toolsdir/yarn"
decompdir="$toolsdir/decomp"
fernflower="https://hub.spigotmc.org/stash/projects/SPIGOT/repos/builddata/raw/bin/fernflower.jar"

function setup {
    git submodule update --init --recursive

    if [ ! -f "$toolsdir/fernflower.jar" ]; then
        echo "Downloading necessary libraries."
        cd "$toolsdir"
        wget "$fernflower"
    fi

    echo "Mapping vanilla server jar."
    cp "$toolsdir/build-patched.gradle" "$yarndir/build.gradle" || exit 1
    mkdir -p "$toolsdir/decomp"
    cd "$yarndir"
    eval "./gradlew mapNamedJar" || exit 1
    cp "$yarndir/1.16.1-named.jar" "$toolsdir/decomp" || exit 1
}

function decomp {
    echo "Extracting mapped Minecraft source."
    mkdir -p "$decompdir/extracted"
    cd "$decompdir/extracted"
    unzip "$decompdir/1.16.1-named.jar" || exit 1

    echo "Decompiling mapped Minecraft source."
    mkdir -p "$decompdir/decompiled"
    cd "$decompdir/decompiled"
    java -jar "$toolsdir/fernflower.jar" -dgs=1 -hdc=0 -rbr=0 -asc=1 -udv=0 "$decompdir/extracted" "$decompdir/decompiled"
}

setup
decomp
) || exit 1
