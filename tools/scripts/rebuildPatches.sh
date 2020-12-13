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

rebuildPatches() {
    newPatches=0
    updatedPatches=0
    unchangedPatches=0

    echo "Rebuilding patches against $mcVersion decompiled source..."

    if [ ! -d ".cache/$mcVersion/decompiled/" ]
    then
        echo ""
        echo "No decompiled directory found for $mcVersion"
        echo "Make sure that you have ran 'loom setup'..."
        exit 1
    fi

    for javaFile in $(find "./server/src/main/java/net/minecraft/" -name "*.java")
    do
        javaFileClean=${javaFile#"./server/src/main/java/net/minecraft/"}
        patchOutPath="./server/patches/"$(echo $javaFileClean | cut -d. -f1)".patch"

        printf "%-80s" "Diffing $javaFileClean... "
        #printf "Diffing $javaFileClean... "

        strip_cr ".cache/$mcVersion/decompiled/net/minecraft/$javaFileClean" > /dev/null
        strip_cr "./server/src/main/java/net/minecraft/$javaFileClean" > /dev/null
        
        difference=$(diff -u --label a/net/minecraft/$javaFileClean ".cache/$mcVersion/decompiled/net/minecraft/$javaFileClean" --label b/net/minecraft/$javaFileClean "./server/src/main/java/net/minecraft/$javaFileClean")

        # Check if the difference is empty
        if [ -z "$difference" ]
        then
            continue
        fi

        if [ ! -f "$patchOutPath" ]
        then
            printf "New patch!\n"
            mkdir -p "$(dirname "$patchOutPath")"
            echo "$difference" > "$patchOutPath"
            ((newPatches=newPatches+1))
            continue
        fi

        patchCut=$(echo "$difference" | tail -n +3)
        patchOld=$(cat "$patchOutPath" | tail -n +3)
        if [ "$patchCut" == "$patchOld" ] ; then
            printf "Unchanged!\n"
            ((unchangedPatches=unchangedPatches+1))
            continue
        fi

        printf "Updated!\n"
        echo "$difference" > "$patchOutPath"
        ((updatedPatches=updatedPatches+1))
    done

    echo ""
    echo "Unchanged patches: $unchangedPatches"
    echo "Updated patches: $updatedPatches"
    echo "New patches: $newPatches"
}

rebuildPatches