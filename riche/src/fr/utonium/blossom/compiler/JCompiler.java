/**
 * 
 */
package fr.utonium.blossom.compiler;

import java.io.File;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * @author francois
 *
 */
public class JCompiler {
	
	private static JCompiler INSTANCE = null;
	private JavaCompiler compiler;
	private StandardJavaFileManager fileManager;
	private Iterable<? extends JavaFileObject> unit;
	
	private JCompiler() {
		compiler = ToolProvider.getSystemJavaCompiler();
		fileManager = compiler.getStandardFileManager(null, null, null);
	}
	
	public static JCompiler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new JCompiler();
		return INSTANCE;
	}
	
	public void setFiles(List<File> files) {
		unit = fileManager.getJavaFileObjectsFromFiles(files);
	}
	
	public void compile() {
		compiler.getTask(null, fileManager, null, null, null, unit).call();
		//compile
	}
	
	public void run(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		/*Iterable<JavaFileObject> e = null;
		try {
			e = fileManager.list(null, className, null, false);
		} catch (IOException e1) {
			System.out.println("not found");
			e1.printStackTrace();
		}	
		System.out.println(e);*/
		fileManager.getClassLoader(null).loadClass(className);
		
		Class<? extends Runnable> c = Class.forName(className).asSubclass(Runnable.class);
		Runnable r = c.newInstance();
		new Thread(r).start();
		
	}
	
}
