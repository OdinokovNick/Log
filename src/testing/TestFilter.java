package testing;

import java.io.IOException;
import exception.WrongKeyException;
import filters.Filter;
import junit.framework.TestCase;

/**
 * Class of testing logger filter 
 * @author Александр
 *
 */
public class TestFilter extends TestCase{
	/**
	 * {@value filter} new filter of message
	 */
	private Filter filter;
	/**
	 * default level of message
	 */
	private String testLevel = new String ("info");
	/**
	 * constructor of TestFilter
	 * @param testName name of testing method
	 * @throws IOException
	 */
	public TestFilter(String testName) throws IOException {
		super(testName);
	}
	/**
	 * testing filtration of message
	 * @throws WrongKeyException
	 */
	public void testIsAllowed() throws WrongKeyException{
		filter = new Filter(testLevel);
		assertTrue(filter.isAllowed("warning"));
		filter = new Filter("warning");
		assertFalse(filter.isAllowed("info"));
		
	}
}
