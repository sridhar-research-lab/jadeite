package services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import heros.InterproceduralCFG;

import java.util.Map;

import soot.*;
import soot.jimple.toolkits.ide.icfg.JimpleBasedInterproceduralCFG;
import soot.options.Options;
import soot.util.dot.DotGraph;

import java.nio.file.Files;
import java.nio.file.Paths;


public class Processor {
    public ArrayList<Unit> visited;
    public InterproceduralCFG<Unit, SootMethod> icfg;
    public ArrayList<String> argsList = new ArrayList<String>();
    public DotGraph dotIcfg;

    public static boolean isExistExecutedMethod(String executedClassName, String jarFileName) {
        ArrayList<String> argsArrayList = new ArrayList<String>();
        argsArrayList.clear();
        argsArrayList.addAll(Arrays.asList(new String[]{

                "-w",

                "-pp",

                "-allow-phantom-refs",

                "-process-dir",

                jarFileName

        }));
        String[] args2 = argsArrayList.toArray(new String[0]);

        Options.v().parse(args2);
        SootClass c = Scene.v().forceResolve(executedClassName, SootClass.BODIES);
        c.setApplicationClass();

        Scene.v().loadNecessaryClasses();
        try {
            c.getMethodByName("init");
            return true;
        } catch (Exception e) {
            try {
                c.getMethodByName("main");
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }

    public static void process(String executedClassName, String jarFileName) {
        ArrayList<String> argsArrayList = new ArrayList<String>();
        argsArrayList.clear();
        argsArrayList.addAll(Arrays.asList(new String[]{

                "-w",

                "-pp",

                "-allow-phantom-refs",

                "-process-dir",

                jarFileName

        }));
        String[] args2 = argsArrayList.toArray(new String[0]);

        Options.v().parse(args2);
        Options.v().set_output_format(Options.output_format_jimple);
//        Scene.v().addBasicClass(android.app.Service,SootClass.HIERARCHY);
        Options.v().set_whole_program(true);

        Scene.v().loadClassAndSupport(executedClassName);

        SootClass c = Scene.v().forceResolve(executedClassName, SootClass.BODIES);
        c.setApplicationClass();
        try {
            Scene.v().loadNecessaryClasses();
        } catch (Error e) {
            return;
        }
        SootMethod mainMethod;
        try {
            mainMethod = c.getMethodByName("init");
        } catch (Exception e) {
            try {
                mainMethod = c.getMethodByName("main");
            } catch (Exception ex) {
                try {
                    mainMethod = Scene.v().getMainClass().getMethodByName("main");
                } catch (Exception exx) {
                    try {
                        mainMethod = Scene.v().getMainClass().getMethodByName("init");
                    } catch (Exception exxx) {
                        try {
                            mainMethod = Scene.v().getMainClass().getMethodByName("start");
                        } catch (Exception exxxx) {
                            try {
                                mainMethod = Scene.v().getMainClass().getMethodByName("run");
                            } catch (Exception exxxxx) {
                                return;
                            }
                        }
                    }
                }
            }
        }
        List entryPoints = new ArrayList();
        entryPoints.add(mainMethod);
        Scene.v().setEntryPoints(entryPoints);
        PackManager.v().runPacks();
        Processor icfgObject = new Processor();
        String filePath = IO.getOutputPath() + "/" + IO.getFileName(jarFileName);
        Path path = Paths.get(filePath + ".dot");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (Files.exists(path)) {
            icfgObject.constructICFG(mainMethod, filePath + timestamp + ".dot");
        }

        if (Files.notExists(path)) {
            icfgObject.constructICFG(mainMethod, filePath + ".dot");
        }
    }

    public void constructICFG(SootMethod mainMethod, String icfgGraphName) {
        try {

            String javaHome = System.getenv("JAVA_HOME");
            String userdir = System.getProperty("user.dir");
            if (javaHome == null) {
                System.out.println("Please set JAVA_HOME");
                return;
            }
            if (!javaHome.endsWith("/"))
                javaHome += "/";

            if (!Files.exists(Paths.get(userdir + "/Tester/"))) {
                System.out.println("Tester folder Does Not Exist");
                return;
            }


            Scene.v()
                    .setSootClassPath(
                            javaHome + "jre/lib/rt.jar:" + javaHome + "jre/lib/jce.jar:"
                                    + javaHome + "jre/lib/jsse.jar:" + userdir + "/Tester/");

            dotIcfg = new DotGraph("");

            icfg = new JimpleBasedInterproceduralCFG();
            System.out.println("Custom Entry Points are: ");
            System.out.println(Scene.v().getEntryPoints());
            System.out.println("Constructing the ICFG");

            icfg = new JimpleBasedInterproceduralCFG();

            Unit startPoint = null;
            for (Unit temp : icfg.getStartPointsOf(mainMethod)) {
                startPoint = temp;
                System.out.println("Start Point Set!");
                break;
            }
            visited = new ArrayList<Unit>();
            graphTraverse(startPoint, icfg);
            G.v().out.println(icfgGraphName + dotIcfg.DOT_EXTENSION);
            dotIcfg.plot(icfgGraphName);
        } catch (Exception e) {
            System.out.println("Exception occured!");
            e.printStackTrace();
        }

    }

    public void graphTraverse(Unit startPoint,
                              InterproceduralCFG<Unit, SootMethod> icfg) {
        List<Unit> currentSuccessors = this.icfg.getSuccsOf(startPoint);
        System.out.println("Visited Size: " + this.visited.size());
        if (currentSuccessors.size() == 0) {
            System.out.println("Traversal complete");
            return;
        } else {
            for (Unit succ : currentSuccessors) {
                System.out.println("Succesor: " + succ.toString());
                if (!this.visited.contains(succ)) {
                    this.dotIcfg.drawEdge(startPoint.toString(),
                            succ.toString());
                    this.visited.add(succ);
                    graphTraverse(succ, this.icfg);

                }

            }
        }
    }
}
