#!/bin/bash

if [ $# -lt 1 ]
then
    echo "Usage : $0 <JAR Dir>"
    exit 1
fi
inputJarDir=$1
files=$(grep -inr "applet" $inputJarDir | grep -v "abstract" | grep "extends" | head -n1)
fileName=$(echo $files | cut -d':' -f1)
# echo "File name : $fileName"
# echo "Search results: $files"
filePath=$(echo $fileName | rev | cut -d'/' -f2- | rev)
# echo "____ FILE PATH : $filePath"
className=$(echo $files | awk -F 'class' '{print $NF}' )
className=$(sed -e 's/^[[:space:]]*//' -e 's/[[:space:]]*$//' <<< $className | cut -d' ' -f1)
# echo "Class name : $className"
newClassName=$(echo $inputJarDir | sed 's/\///' | cut -d'.' -f1)
echo "public class $newClassName
{
public static void main(String args[])
{
    $className obj = new $className();
    obj.init();
}
}" | cat >  "$filePath/$newClassName.java"
