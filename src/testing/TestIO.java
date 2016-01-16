package testing;

import java.io.IOException;
import exception.ConfFileFormatException;
import junit.framework.TestCase;
import io.*;

/**
 * class of configuration reading testing
 * @author Александр
 *
 */
public class TestIO extends TestCase{

	private String fileName = "src/testing/testConf.txt";
	private String xmlName = new String("src/testing/testConf.xml");

	/**
	 * constructor of TestIO
	 * @param testName name of testing method
	 * @throws IOException
	 */
	public TestIO(String testName) throws IOException {
		super(testName);
	}
	/**
	 * testing of configuration reading from file
	 * @throws IOException
	 * @throws ConfFileFormatException
	 */
	public void testReadConf() throws IOException, ConfFileFormatException{
		ReadConf rc = new ReadConf(fileName);
		assertEquals(rc.getPackageList().get(0),"bin/dir");
		assertEquals(rc.getFileList().get(0),"src/log/debug/dirlog.txt");
		assertEquals(rc.getLevelList().get(0),"warning");
		assertEquals(rc.getHandlerList().get(0).get(1),"email");
	}
	/**
	 * testing of configuration reading from xml file
	 * @throws IOException
	 * @throws ConfFileFormatException
	 */
	public void testReadXMLConf() throws IOException, ConfFileFormatException{
		ReadXMLConf rc = new ReadXMLConf(xmlName);
		assertEquals(rc.getPackageList().get(0),"bin/dir");
		assertEquals(rc.getFileList().get(0),"src/log/debug/dirlog.txt");
		assertEquals(rc.getLevelList().get(0),"warning");
		assertEquals(rc.getHandlerList().get(0).get(1),"email");
	}
}
