package ase.cts.iulia.testare;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSuit extends TestCase {

	public static Test suite(){
		TestSuite suite = new TestSuite();
		
		suite.addTestSuite(UserTest.class);
		suite.addTestSuite(BookTest.class);
		
		return suite;
	}
	
}


