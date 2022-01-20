# DOT File Pruner
## USAGE
```
./processDotFile.sh <inputFile> <outputFile>
```
or 
```
./processDotFile.sh <inputFile>
```
When no input file is provided, the output file is `MOD_<inputfileName>`

## CONTENTS
#### `refactorDot.sh`
This script finds all the edges in the input dot file and discard unnecessary information. 

#### `DotFileManipulator.py` 
This script takes input from `refactorDot.sh` and then prepares a DOT file that contains all the system level APIs.

#### `processDotFile.sh`
This is an automated script to perform the above tasks collectively.

## Samples
### Input for `refactorDot.sh`
```
digraph "" {
    "r0 := @parameter0: java.lang.String[]"
    "$r3 = new java.awt.Frame"
    "r0 := @parameter0: java.lang.String[]"->"$r3 = new java.awt.Frame";
    "specialinvoke $r3.<java.awt.Frame: void <init>(java.lang.String)>(\"Mines\")"
    "$r3 = new java.awt.Frame"->"specialinvoke $r3.<java.awt.Frame: void <init>(java.lang.String)>(\"Mines\")";
    "r1 = $r3"
    "specialinvoke $r3.<java.awt.Frame: void <init>(java.lang.String)>(\"Mines\")"->"r1 = $r3";
    "virtualinvoke r1.<java.awt.Component: void setSize(int,int)>(640, 350)"
    "r1 = $r3"->"virtualinvoke r1.<java.awt.Component: void setSize(int,int)>(640, 350)";
    "$r4 = new Mines"
    "virtualinvoke r1.<java.awt.Component: void setSize(int,int)>(640, 350)"->"$r4 = new Mines";
    "specialinvoke $r4.<Mines: void <init>()>()"
    "$r4 = new Mines"->"specialinvoke $r4.<Mines: void <init>()>()";
    "r2 = $r4"
    "specialinvoke $r4.<Mines: void <init>()>()"->"r2 = $r4";
    "virtualinvoke r1.<java.awt.Container: void add(java.awt.Component,java.lang.Object)>(r2, \"Center\")"
    "r2 = $r4"->"virtualinvoke r1.<java.awt.Container: void add(java.awt.Component,java.lang.Object)>(r2, \"Center\")";
    "virtualinvoke r2.<Mines: void init()>()"
    "virtualinvoke r1.<java.awt.Container: void add(java.awt.Component,java.lang.Object)>(r2, \"Center\")"->"virtualinvoke r2.<Mines: void init()>()";
    "virtualinvoke r2.<java.applet.Applet: void start()>()"
    "virtualinvoke r2.<Mines: void init()>()"->"virtualinvoke r2.<java.applet.Applet: void start()>()";
    "$r5 = new AppletHost$1"
    "virtualinvoke r2.<java.applet.Applet: void start()>()"->"$r5 = new AppletHost$1";
    "specialinvoke $r5.<AppletHost$1: void <init>()>()"
    "$r5 = new AppletHost$1"->"specialinvoke $r5.<AppletHost$1: void <init>()>()";
    "virtualinvoke r1.<java.awt.Window: void addWindowListener(java.awt.event.WindowListener)>($r5)"
    "specialinvoke $r5.<AppletHost$1: void <init>()>()"->"virtualinvoke r1.<java.awt.Window: void addWindowListener(java.awt.event.WindowListener)>($r5)";
    "virtualinvoke r1.<java.awt.Component: void setVisible(boolean)>(1)"
    "virtualinvoke r1.<java.awt.Window: void addWindowListener(java.awt.event.WindowListener)>($r5)"->"virtualinvoke r1.<java.awt.Component: void setVisible(boolean)>(1)";
    "return"
    "virtualinvoke r1.<java.awt.Component: void setVisible(boolean)>(1)"->"return";
}
```
### Pruned DOT file from `DotFileManipulator.py`
```
digraph "" {
"r0 := @parameter0: java.lang.String[]"->"$r3 = new java.awt.Frame";
 "$r3 = new java.awt.Frame"->"specialinvoke $r3.<java.awt.Frame: void <init>(java.lang.String)>('Mines')";
 "specialinvoke $r3.<java.awt.Frame: void <init>(java.lang.String)>('Mines')"->"virtualinvoke r1.<java.awt.Component: void setSize(int,int)>(640, 350)";
 "virtualinvoke r1.<java.awt.Component: void setSize(int,int)>(640, 350)"->"virtualinvoke r1.<java.awt.Container: void add(java.awt.Component,java.lang.Object)>(r2, 'Center')";
 "virtualinvoke r1.<java.awt.Container: void add(java.awt.Component,java.lang.Object)>(r2, 'Center')"->"virtualinvoke r2.<java.applet.Applet: void start()>()";
 "virtualinvoke r2.<java.applet.Applet: void start()>()"->"virtualinvoke r1.<java.awt.Window: void addWindowListener(java.awt.event.WindowListener)>($r5)";
 "virtualinvoke r1.<java.awt.Window: void addWindowListener(java.awt.event.WindowListener)>($r5)"->"virtualinvoke r1.<java.awt.Component: void setVisible(boolean)>(1)";
 "virtualinvoke r1.<java.awt.Component: void setVisible(boolean)>(1)"->"return";
}
```

### Contributors
* [Rahul Rachapalli](https://github.com/rahulr56)
