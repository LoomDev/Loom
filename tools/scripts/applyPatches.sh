#!/bin/bash

# https://stackoverflow.com/a/38595160
# https://stackoverflow.com/a/800644
if sed --version >/dev/null 2>&1; then
  strip_cr() {
    sed -i -- "s/\r//" "$@"
  }
else
  strip_cr () {
    sed -i "" "s/$(printf '\r')//" "$@"
  }
fi

mcVersion=$(cat ".loomversion")

applyPatches() {
    echo "Applying patches to $mcVersion decompiled source..."

    if [ ! -d ".cache/$mcVersion/decompiled/" ]
    then
        echo ""
        echo "No decompiled directory found for $mcVersion"
        echo "Make sure that you have ran 'loom setup'..."
        exit 1
    fi

    rm -rf "./server/src/main/java/net/minecraft/"

    for patchFile in $(find "./server/patches/" -name "*.patch")
    do
        patchFileClean=${patchFile#"./server/patches/"}
        javaFile="$(echo $patchFileClean | cut -d. -f1).java"

        if [ -f ".cache/$mcVersion/decompiled/net/minecraft/$javaFile" ]
        then
            strip_cr ".cache/$mcVersion/decompiled/net/minecraft/$javaFile"
            mkdir -p "$(dirname "./server/src/main/java/net/minecraft/$javaFile")"
            cp ".cache/$mcVersion/decompiled/net/minecraft/$javaFile" "server/src/main/java/net/minecraft/$javaFile"
            patch -d "server/src/main/java/" "net/minecraft/$javaFile" < "$patchFile"
        fi
    done

    echo " Done!\n"
}

applyPatches