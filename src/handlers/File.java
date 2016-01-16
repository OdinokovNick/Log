package handlers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class write log massage to file
 * @author Александр
 *
 */
public class File implements Handler{
	/**
	 * name of log file
	 */
	private String fileName;
	/**
	 * initialization of file name
	 * @param fileName name of log file
	 */
	public File(String fileName) {
		this.fileName=fileName;
	}
	/**
	 * write log message to log file
	 * @param msg log message
	 * @throws IOException
	 */
	private void writeToFile(String msg) throws IOException{
		BufferedWriter out=null;
		try {
			out = new BufferedWriter(new FileWriter(fileName,true));
			String separator = System.getProperty("line.separator");
			out.write(msg+separator);
			out.flush();
		} 
		catch (IOException e) {
		    e.printStackTrace();
		}
		finally{
			out.close();
		}
	}
	/**
	 * message processing
	 * @param msg log message
	 * @throws IOException
	 */
	public void processMassage(String msg) throws IOException {
		writeToFile(msg);
	}
}
