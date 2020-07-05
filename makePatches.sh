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
nms="${1%/}/net/minecraft/"
show_diff_msg=true

if [ $# -ge 2 ]
then
    show_diff_msg=$2
    if [ "$show_diff_msg" = false ]
    then
        echo "Suppressing normal output. Will only output for changed or created patches."
    fi
fi

newPatches=0
updatedPatches=0
unchangedPatches=0

for file in $(find $src -name '*.java')
do
    pathRelativeToSrc=${file#$src}

    if [ "$show_diff_msg" = true ]
    then
        echo "Diffing $pathRelativeToSrc"
    fi

    strip_cr "$nms$pathRelativeToSrc" > /dev/null
    strip_cr "$src$pathRelativeToSrc" > /dev/null
    outName=$(echo patches/"$(echo $pathRelativeToSrc | cut -d. -f1)".patch)

    patchNew=$(diff -u --label a/net/minecraft/$pathRelativeToSrc "$nms$pathRelativeToSrc" --label b/net/minecraft/$pathRelativeToSrc "$src$pathRelativeToSrc")

    # Check if the difference is empty
    if [ -z "$patchNew" ]
    then
        continue
    fi

    # Check if the patch existed
    if [ -f "$outName" ]
    then
        patchCut=$(echo "$patchNew" | tail -n +3)
        patchOld=$(cat "$outName" | tail -n +3)
        if [ "$patchCut" != "$patchOld" ] ; then
            echo "$outName changed"
            echo "$patchNew" > "$outName"
            ((updatedPatches=updatedPatches+1))
        else
            ((unchangedPatches=unchangedPatches+1))
        fi
    else
        echo "New patch $outName"
        mkdir -p "$(dirname "$outName")"
        echo "$patchNew" > "$outName"
        ((newPatches=newPatches+1))
    fi
done

echo ""
echo "Unchanged patches: $unchangedPatches"
echo "Updated patches: $updatedPatches"
echo "New patches: $newPatches"