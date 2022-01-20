import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import heros.InterproceduralCFG;
import soot.G;
import soot.PackManager;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Unit;
import soot.jimple.toolkits.ide.icfg.JimpleBasedInterproceduralCFG;
import soot.options.Options;
import soot.util.dot.DotGraph;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ICFG_Applets {

	public ArrayList<Unit> visited;
	public InterproceduralCFG<Unit, SootMethod> icfg;
	public ArrayList<String> argsList = new ArrayList<String>();
	public DotGraph dotIcfg;
	String class_name_having_init = "";
	String jarFileName = "";
	public static void main(String[] args) {
		String class_name_having_init = "aTicker";
		String icfgGraphName = class_name_having_init;	
		String jarFileName = "/home/khuebc/malware/Interprocedural-CFG/Tester/0ab5c0fda71707dcc570a552cae6936f.jar";
		
		ArrayList<String> argsList = new ArrayList<String>();
		argsList.clear();
		//String userdir = System.getProperty("user.dir");
		argsList.addAll(Arrays.asList(new String[] {

		"-w",

		"-pp",

		"-allow-phantom-refs",

		"-process-dir",

		jarFileName,

		//userdir+"/" + jarFileName,

		}));
		String[] args2 = argsList.toArray(new String[0]);

		Options.v().parse(args2);

		SootClass c = Scene.v().forceResolve(class_name_having_init, SootClass.BODIES);
		c.setApplicationClass();

		Scene.v().loadNecessaryClasses();
		SootMethod main_method = null;
		try{
			main_method = c.getMethodByName("init");
		}catch(Exception e){
			main_method = c.getMethodByName("main");
		}
//		SootMethod main_method = c.getMethodByName("init");
		List entryPoints = new ArrayList();
		entryPoints.add(main_method);
		Scene.v().setEntryPoints(entryPoints);
		PackManager.v().runPacks();
		ICFG_Applets icfgObject = new ICFG_Applets();
		icfgObject.constructICFG(main_method, icfgGraphName);

	}

	public void constructICFG(SootMethod main_method, String icfgGraphName) {

		try {

			String javaHome = System.getenv("JAVA_HOME");
			String userdir = System.getProperty("user.dir");
			if (javaHome == null)
			{
				System.out.println("Please set JAVA_HOME");
				return;
			}
			if (!javaHome.endsWith("/"))
				javaHome += "/";

			if (!Files.exists(Paths.get(userdir+"/Tester/")))
			{
				System.out.println("Tester folder Does Not Exist");
				return;
			}


			Scene.v()
					.setSootClassPath(
							javaHome+"jre/lib/rt.jar:"+javaHome+"jre/lib/jce.jar:"
									+ javaHome+"jre/lib/jsse.jar:"+userdir+"/Tester/");

			dotIcfg = new DotGraph("");

			icfg = new JimpleBasedInterproceduralCFG();
			System.out.println("Custom Entry Points are: ");
			System.out.println(Scene.v().getEntryPoints());
			System.out.println("Constructing the ICFG");

			icfg = new JimpleBasedInterproceduralCFG();

			Unit startPoint = null;
			for (Unit temp : icfg.getStartPointsOf(main_method)) {
				startPoint = temp;
				System.out.println("Start Point Set!");
				break;
			}
			visited = new ArrayList<Unit>();
			graphTraverse(startPoint, icfg);
			G.v().out.println(icfgGraphName.toString() + dotIcfg.DOT_EXTENSION);
			dotIcfg.plot(icfgGraphName.toString());
		} catch (Exception e) {
			System.out.println("Exception occured!");
			e.printStackTrace();
		}

	}

	public void graphTraverse(Unit startPoint,
			InterproceduralCFG<Unit, SootMethod> icfg) {
		List<Unit> currentSuccessors = this.icfg.getSuccsOf(startPoint);
		System.out.println("Visited Size: "+this.visited.size());
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