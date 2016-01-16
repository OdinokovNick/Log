package handlers;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * this class print log message to html page
 * @author Александр
 *
 */
public class HTML implements Handler{

	private String fileName = "src/logs.html";

	public HTML() {
		
	}
	/**
	 * initialization of file name of html page
	 * @param fileName name of html page
	 */
	public HTML(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * message processing
	 * @param msg log message
	 * @throws IOException
	 */
	public void processMassage(String msg) throws IOException {
		writeToHTML(msg);
	}

	/**
	 * print log message to html page
	 * @param msg log message
	 * @throws IOException
	 */
	private void writeToHTML(String msg) throws IOException {
		String separator = System.getProperty("line.separator");
		String endBody = "</body>" + separator + "</html>";
		RandomAccessFile raf = null;
		  try{
			  raf = new RandomAccessFile(fileName,"rw");
			  raf.seek(raf.length() - endBody.length()-1);
			  raf.writeBytes(msg + "<br>" + separator+endBody);
			  
		  }
		  catch (IOException e){
			  System.err.println(e.getMessage());
		  }
		  finally{
			  if (raf != null) raf.close();
		  }
	}


}