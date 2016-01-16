package dir.test;


import java.io.IOException;
import exception.ConfFileFormatException;
import exception.WrongKeyException;
import loggers.Logger;
import managers.LogManager;


public class MyTestClass {
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
	public MyTestClass() throws WrongKeyException, IOException, ConfFileFormatException{
		l= LogManager.getInstance().getLog(MyTestClass.class);
	}
	/**
	 * Method for writing log messages 
	 */
	public void startTestOfMyTestClass() throws IOException, ConfFileFormatException, WrongKeyException {
		//l= LogManager.getInstance().getLog(Test.class);
		l.log("warning", "new message");
		l.log("warning", "123");
		l.log("warning", "ololo");
		l.log("warning", "error", new Exception());
	}

}
