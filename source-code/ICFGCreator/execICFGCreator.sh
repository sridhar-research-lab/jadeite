#!/usr/bin/env bash

outputPath="GeneratedDotFiles"

if [ ! -d "$outputPath" ];then
    mkdir -p $outputPath
fi

currentPath=$(pwd)
echo "Current Path : $currentPath"
echo "PATH : $1"

outputPath=${currentPath}/$outputPath
echo "outputPath : $outputPath"

files=$(cd "$1"; ls -l --color=none *.jar | awk -F " " '{print $NF}'; cd $currentPath)
for jarFile in $files
do
    echo "$jarFile"
    echo cp "${1}$jarFile" "$outputPath"
    # echo ./ICFGCreator.sh "$outputPath/$jarFile"
    # echo rm -rf "$outputPath/$jarFile"
    # ./ICFGCreator.sh "$outputPath/$jarFile"
    cp "${1}$jarFile" "$outputPath"
    jarFile=$(echo $jarFile | awk -F "/" '{print $NF}')
    ./ICFGCreator.sh "$outputPath/$jarFile"
    rm -rf $outputPath/$jarFile
done