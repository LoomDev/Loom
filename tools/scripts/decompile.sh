#!/bin/bash

mcVersion=$(cat ".loomversion")

mapServerJar() {
    printf "Mapping $mcVersion Minecraft server jar...\n"
    java -jar tools/tiny-remapper-0.3.1.72-fat.jar ".cache/$mcVersion/server.jar" ".cache/$mcVersion/server-deobf.jar" ".cache/$mcVersion/server.tiny" obf deobf --skipLocalVariableMapping || exit
    # rm -rf ./logs

    printf "Installing $mcVersion mapped Minecraft server jar in your local maven repo..."
    mvn install:install-file -Dfile=".cache/$mcVersion/server-deobf.jar" -DgroupId="org.loomdev" -DartifactId="minecraft-server" -Dversion="$mcVersion-SNAPSHOT" -Dpackaging="jar" > /dev/null
    printf " Done!\n"
}

decompile() {
    printf "Extracting $mcVersion mapped Minecraft server jar..."
    unzip -o ".cache/$mcVersion/server-deobf.jar" "net/**/*" -d ".cache/$mcVersion/extracted/" > /dev/null
    printf " Done!\n"

    printf "Decompiling $mcVersion mapped Minecraft source...\n"
    mkdir -p ".cache/$mcVersion/decompiled/"
    java -jar "tools/fernflower.jar" -dgs=1 -hdc=0 -rbr=0 -asc=1 -udv=0 ".cache/$mcVersion/extracted/" ".cache/$mcVersion/decompiled/"
    printf "Done!\n"
}

mapServerJar
decompile
