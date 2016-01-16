package testing;

import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import org.junit.runner.RunWith;
import testing.tests.FilterTestTest;

/**
 * Class of project testing 
 * @author Александр
 *
 */
public class Testing {
	/**
	 * start of project testing
	 * @throws IOException
	 */
	public void startTests() throws IOException{
		TestRunner runner = new TestRunner();
		TestSuite suite = new TestSuite();
	    /*suite.addTest(new TestHandler("testFileHandler"));
	    suite.addTest(new TestHandler("testFileHandler","other message"));
	    suite.addTest(new TestHandler("testHTMLHandler"));
	    suite.addTest(new TestHandler("testHTMLHandler","other message"));
	    suite.addTest(new TestFilter("testIsAllowed"));
	    suite.addTest(new TestIO("testReadConf"));
	    suite.addTest(new TestIO("testReadXMLConf"));*/
		//suite.addTest(FilterTestTest.class);
	    runner.doRun(suite);
	}
}
