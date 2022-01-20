# ICFG Creator
## Contents
### `ICFGCreator.sh`
This script finds the class in which init is present in an applet and then passes the classs name as a parameter to `myIcfg.jar` file to generate the ICFG. 
```
USAGE : ./ICFGCreator.sh <JAR File>
``` 

### `execICFGCreator.sh`
This script autmates generating ICFG for a set of JAR files using ICFGCreator.sh.
```
USAGE: ./execICFGCreator.sh <Path to JAR Files>
```

### `decompiler.jar`
This is used to decompile JAR files. Downlaoded from [mstrobel/procyon](https://bitbucket.org/mstrobel/procyon/downloads/)
```
USAGE : java -jar decompiler.jar <JAR File> -o <output dir>
```

### `myIcfg.jar`
This JAR file generates ICFG for applets by setting the entry point as `inti()`. 
```
USAGE : java -jar myIcfg.jar <Class in which init is present> <dotfile path>
```
