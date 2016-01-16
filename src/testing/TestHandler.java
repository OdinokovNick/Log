package testing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import junit.framework.*;
import handlers.File;
import handlers.HTML;

/**
 * Class of testing logger handlers
 * @author Александр
 *
 */
public class TestHandler extends TestCase{
	/**
	 * {@value file} new file handler
	 */
	private File file;
	/**
	 * {@value html} new file handler
	 */
	private HTML html;
	/**
	 * {@value fileName} file name of test file
	 */
	private String fileName = new String("src/testing/testFile.txt");
	/**
	 * {@value htmlName} file name of tesr html page
	 */
	private String htmlName = new String("src/testing/testHTML.html");
	/**
	 * {@value message} default test message
	 */
	private String message = new String("test message");
	
	/**
	 * constructor of TestHandler
	 * @param testName name of test method
	 * @throws IOException
	 */
	public TestHandler(String testName) throws IOException {
		super(testName);
	}
	/**
	 * constructor of TestHandler
	 * @param testName name of test method
	 * @param message other test message
	 * @throws IOException
	 */
	public TestHandler(String testName,String message) throws IOException {
		super(testName);
		this.message = message;
	}
	/**
	 * Testing for file output
	 * @throws IOException
	 */
	public void testFileHandler() throws IOException{
		file = new File(fileName);	
		try {
			file.processMassage(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader( new java.io.File(fileName).getAbsoluteFile()));
			int i=-1;
			ArrayList<String> list = new ArrayList<String>();
			String s;
			while ((s = in.readLine()) != null){
				list.add(s);
				i++;
			}
			assertEquals(list.get(i),message);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			in.close();
		}
		
	}
	/**
	 * Testing for html page output
	 * @throws IOException
	 */
	public void testHTMLHandler() throws IOException{
		html = new HTML(htmlName);	
		try {
			html.processMassage(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader( new java.io.File(htmlName).getAbsoluteFile()));
			int i=-1;
			ArrayList<String> list = new ArrayList<String>();
			String s;
			while ((s = in.readLine()) != null){
				list.add(s);
			}
			for(String str : list){
				i++;
				if ("</body>".equals(str)) break;
				
			}
			assertEquals(list.get(i-1),message+"<br>");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			in.close();
		}
		
	 }
}
