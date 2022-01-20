#!/usr/bin/env bash

#the following is to export the JAVA_HOME environment variable path
export JAVA_HOME=`type -p javac|xargs readlink -f|xargs dirname|xargs dirname`

if [ $# -lt 1 ]
then
    echo "Usage : $0 <JAR File>"
    exit 1
fi
jarFile=$1
tempFolder="TempDecompiledJar/"
prob="Not_Processed/" #Files that were not processed

#Check if directory does not exist
#this will contain the files that were not processed
if [ ! -d "${prob}Not_Applet" ];then
    mkdir -p ${prob}Not_Applet
fi

if [ ! -d "${prob}No_Init" ];then
    mkdir -p ${prob}No_Init
fi

if [ ! -d "${prob}Corrupted" ];then
    mkdir -p ${prob}Corrupted
fi

echo "Working on .. $jarFile"

shopt -s extglob

#Decompile JarFile
# echo "java -jar decompiler.jar $jarFile -o $tempFolder"
java -jar decompiler.jar "$jarFile" -o "$tempFolder" 2> error.txt
x=$(cat error.txt)
if [ -z "$x" ]
then
    inputJarDir=$tempFolder
    files=$(grep -inr "applet" "$inputJarDir" | grep -v "abstract" | grep "extends" | head -n1)
    [[ -z "$files" ]] && rm -rf "$tempFolder" &&echo "Not applet, files: $files" && cp $jarFile ${prob}Not_Applet && exit 0s
    fileName=$(echo "$files" | cut -d':' -f1)
    # echo "File name : $fileName"
    # echo "Search results: $files"
    # filePath=$(echo "$fileName" | rev | cut -d'/' -f2- | rev)
    className=$(echo "$files" | awk -F 'class' '{print $NF}' )
    className=$(sed -e 's/^[[:space:]]*//' -e 's/[[:space:]]*$//' <<< "$className" | cut -d' ' -f1)
    echo "className: $className"
    echo "File path : $filePath"
    # echo "Class Name : $className"
    echo "file name: $fileName"
    pkg=$(grep "package" $fileName | awk -F " " '{print $NF}' | cut -d';' -f1 | sed -e 's/^[[:space:]]*//' -e 's/[[:space:]]*$//')
    echo "Package : $pkg"
    if [ -z "$pkg" ]
    then
        #echo java -jar myIcfg.jar "$className" "DotFiles$jarFile.dot" "$jarFile"
        #java -jar myIcfg.jar "$className" "$jarFile.dot" "$jarFile" 2>&1 | tee error.txt
        echo "************************"
        length=$(java -jar decompiler.jar "$jarFile" -o "$tempFolder")

        #echo $length
        counter=1
        while :
        do
            rem=$(( $counter % 2 ))
 
            if [ $rem -eq 0 ]; then
                tmp=$(echo $length | cut -d' ' -f${counter})
                tmp=$(echo $tmp | cut -d'.' -f1)
                echo $tmp

                if [ -z "$tmp" ]; then
                    echo "BREAK"
                    break
                fi

                # here, jar file has different structure than the package name
                if [[ $tmp = *"/"* ]]; then
                    echo "TRUE"
                    echo $className

                    if [ -d "tmp" ]; then
                        rm -rf tmp
                    fi
                    mkdir tmp
                    cd tmp
                    cp $jarFile .
                    fjar=$(ls)
                    #echo $fjar
                    jar xvf $fjar
                    rm -rf $fjar
                    tdir=$(ls -d */)


                    if [ -z "$tdir" ]
                    then
                          jar cf JAR.jar *
                    else
                          twd="${tmp}.class"
                          #echo $twd

                          DIR=$(dirname "${twd}")
                          DIR="${DIR}/."
                          #echo "${DIR}"
                          tmpdir=$(echo $DIR | cut -d'/' -f1)
                          #echo $tmpdir

                          cp -a $DIR .
                          rm -rf $tmpdir
                          #ls

                          jar cf JAR.jar *

                          #tdir=$(echo $tdir | cut -d'.' -f1)
                          #cd  $tdir
                          #jar cf ../JAR.jar *
                          #cd ..
                    fi


                    if [ -f "JAR.jar" ]
                    then
                        rm -rf -- !(JAR.jar)
                        cd ..
                    fi

                    twd=$(pwd)
                    twd="${twd}/tmp/JAR.jar"
                    #echo $twd

                    echo java -jar myIcfg.jar "$className" "DotFiles$jarFile.dot" "$twd"
                    java -jar myIcfg.jar "$className" "$jarFile.dot" "$twd" 2>&1 | tee error.txt

                    if grep -qs RuntimeException error.txt
					then
					    cp $jarFile ${prob}No_Init
					fi

                    rm -rf tmp
                    break
                else
                    # here, jar file has the same structure as the package name
                    echo "FALSE"

                    echo java -jar myIcfg.jar "$className" "DotFiles$jarFile.dot" "$jarFile"
                    java -jar myIcfg.jar "$className" "$jarFile.dot" "$jarFile" 2>&1 | tee error.txt

                    if grep -qs RuntimeException error.txt
					then
					    cp $jarFile ${prob}No_Init
					fi

                    break
                fi

            fi

            counter=$((counter+1))
        done

        echo "************************"
    else
        echo java -jar myIcfg.jar "$pkg.$className" "DotFiles$jarFile.dot" "$jarFile"
        java -jar myIcfg.jar "$pkg.$className" "$jarFile.dot" "$jarFile" 2>&1 | tee error.txt

        if grep -qs RuntimeException error.txt
		then
		    cp $jarFile ${prob}No_Init
		fi
    fi
    rm -rf "$tempFolder"
else
	[ -z "$jarFile" ] || cp $jarFile ${prob}Corrupted
    echo "Unable to process $jarFile"
fi
echo "#############################################################################################################################################"
