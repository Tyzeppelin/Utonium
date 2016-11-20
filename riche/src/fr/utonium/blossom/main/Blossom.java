package fr.utonium.blossom.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.utonium.blossom.compiler.JCompiler;

public class Blossom {

	public static void main(String[] args) {


		File fi = new File("res/test-classes/Test.java");
		List<File> lf = new ArrayList<>();
		lf.add(fi);

		JCompiler jc = JCompiler.getInstance();

		jc.setFiles(lf);
		jc.compile();

		try {
			jc.run("Test");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
