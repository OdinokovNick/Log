package dir;

import java.io.IOException;
import loggers.Logger;
import managers.LogManager;
import exception.ConfFileFormatException;
import exception.WrongKeyException;

/**
 * Test class
 * @author Александр
 *
 */
public class MyClass {
	/**
	 * logger of this class
	 */
	private static Logger l;
	/**
	 * initialization of logger
	 * @throws WrongKeyException
	 * @throws IOException
	 * @throws ConfFileFormatException
	 */
	public MyClass() throws WrongKeyException, IOException, ConfFileFormatException{
		l= LogManager.getInstance().getLog(MyClass.class);
	}
	/**
	 * Method for writing log messages 
	 */
	public void startTestOfMyClass() throws IOException, WrongKeyException, ConfFileFormatException{
		//l= LogManager.getInstance().getLog(MyClass.class);
		l.log("warning", "new message");
		l.log("warning", "123");
		l.log("warning", "ololo");
		l.log("warning", "error", new Exception());
		
	}
}