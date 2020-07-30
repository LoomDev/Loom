#!/bin/bash

if [ -z "$1" ]
then
    echo "Please run this script again with the clean decompile sources as an argument. In most cases this will be ./decompiled"
    exit
fi

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

src="src/main/java/net/minecraft/"
minecraftSource="${1%/}/net/minecraft/"
fullSource=false

if [ $# -ge 2 ]
then
    fullSource=$2
    if [ "$fullSource" = true ]
    then
        echo "Copying full minecraft source."
    else
        echo "Only copying patched files."
    fi
fi

rm -rf "$src" # remove existing nms source from /src/main/java
mkdir -p "$src" # make sure the nms folder exists

if [ "$fullSource" = true ]
then
    cp -a "$minecraftSource." "$src"
fi

for patchFile in $(find "patches" -name '*.patch')
do
  patchFileClean=${patchFile#"patches/"}
  file="$(echo $patchFileClean | cut -d. -f1).java"

  if [ -f "$minecraftSource$file" ]
  then
    echo "Patching $file < $patchFileClean"
    strip_cr "$minecraftSource$file"
    mkdir -p "$(dirname "$src$file")"
    cp "$minecraftSource$file" "$src$file"
    patch -d "src/main/java/" "net/minecraft/$file" < "$patchFile"
  #else
    # TEMP --- echo "Unable to apply $patchFileClean: $file not found"
  fi

done
