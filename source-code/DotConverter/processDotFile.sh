#!/bin/bash
inputFile="" 
outputFile=""
if [ $# -eq 1 ]
then
    inputFile=$1 
    outputFile="MOD_$1"
    echo "As output file is not provided, output is copied into \"MOD_$inputFile\""
elif [ $# -eq 2 ]
then 
    inputFile=$1
    outputFile=$2
else
    echo "USAGE : $0 <input dot file> <output file name>"
    echo "If output file is not provided, the input file name is appeneded with \"MOD_ \" to create a new file"
    exit -1
fi

bash refactorDot.sh "$inputFile" > temp.dot
python DotFileManipulator.py temp.dot "$outputFile" > "$outputFile"
rm -rf temp.dot
