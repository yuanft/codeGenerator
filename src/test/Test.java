package test;

import cn.org.rapid_framework.generator.GeneratorFacade;

public class Test {
	public static void main(String[] args) throws Exception {

		GeneratorFacade g = new GeneratorFacade();

		g.deleteOutRootDir();

		g.generateByAllTable("template");
		
	}
}