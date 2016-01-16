package use;


import java.io.IOException;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import testing.Testing;
import dir.MyClass;
import dir.test.MyTestClass;
import exception.ConfFileFormatException;
import exception.WrongKeyException;
import testing.tests.FilterTestTest;

/**
 * Main class of testing log system
 * @author Александр
 *
 */


public class Main {
	/**
	 * point of entry into the program
	 * @param args
	 * @throws WrongKeyException
	 * @throws IOException
	 * @throws ConfFileFormatException
	 */
	public static void main(String[] args) throws WrongKeyException, IOException, ConfFileFormatException {
		//new MyClass().startTestOfMyClass();
		//new MyTestClass().startTestOfMyTestClass();
		//new Testing().startTests();
	}

}
