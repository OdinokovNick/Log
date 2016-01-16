package managers;

import handlers.Handler;
import io.ReadConf;
import io.ReadXMLConf;
import io.TypeOfHandlers;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import exception.ConfFileFormatException;
import exception.WrongKeyException;
import loggers.Logger;

/**
 * manager of loggers
 * creates and controls loggers
 * @author Александр
 *
 */
public class LogManager {
	/**
	 * {@value lm} instance of LogManager
	 */
	private static LogManager lm;
	/**
	 * {@value dir} list of package
	 * {@value file} list of log files
	 * {@value level} list levels for log message
	 * index i in these collections corresponds to one logger
	 */
	private ArrayList<String> dir, file,level;
	/**
	 * {@value handler} list of handlers list
	 */
	private ArrayList<ArrayList<String> >handler;
	/**
	 * {@value loggers} lower loggers
	 */
	private ArrayList<Logger>  loggers;
	
	/**
	 * Creates single instance of LogManager
	 * Reads configuration file
	 * Initialization of collections
	 * Sets loggers
	 * @throws IOException
	 * @throws ConfFileFormatException
	 * @throws WrongKeyException
	 */
	private LogManager() throws IOException, ConfFileFormatException, WrongKeyException{
		//ReadConf rc = new ReadConf();
		ReadXMLConf rc = new ReadXMLConf();
		dir=rc.getPackageList();
		file=rc.getFileList();
		level=rc.getLevelList();
		handler=rc.getHandlerList();
		loggers = new ArrayList<Logger>();
		setLoggers();
	}
	/**
	 * get instance of LogManager 
	 * @return LogManager
	 * @throws IOException
	 * @throws ConfFileFormatException
	 * @throws WrongKeyException
	 */
	public static LogManager getInstance() throws IOException, ConfFileFormatException, WrongKeyException{
		if(lm==null) lm = new LogManager();
		return lm;
	}
	/**
	 * set lower loggers for this logger
	 * @param l logger for which are determined by the lower Loggers
	 * @param className name of class 
	 * @return return collection of loggers
	 */
	private ArrayList<Logger> setLowerLoggers(Logger l, String className){
		ArrayList<Logger> logs = new ArrayList<Logger>();
		for (Logger l1 : loggers){
				if (l1.getPackage().compareTo(l.getPackage())<0) {
					if (l1.getPackage().equals(l.getPackage().substring(0, l1.getPackage().length()))) {l1.setClassName(className); logs.add(l1);} 
				}
		}
		
		return logs;
	}
	/**
	 * create loggers and set loggers for packages
	 * @throws WrongKeyException
	 */
	private void setLoggers() throws WrongKeyException{
		for(int i=0; i<dir.size(); i++){
			Logger l = new Logger();
			l.setFilter(level.get(i));
			ArrayList<Handler> h = new ArrayList<Handler>();
			for (String handl : handler.get(i)){
				TypeOfHandlers type = TypeOfHandlers.valueOf(handl.toUpperCase());
				Handler f;
				switch(type){
					case FILE: {f = new handlers.File(file.get(i));  break;}
					case EMAIL: {f = new handlers.Email(); break;}
					case HTML: {f = new handlers.HTML(); break;}
					default: throw new WrongKeyException();
				}
				h.add(f);
			}
			l.setHandlers(h);
			l.setPackage(dir.get(i));
			loggers.add(l);			
		}
	}
	/**
	 * get instance of logger for any class
	 * @param class1 any class
	 * @return Logger
	 * @throws WrongKeyException
	 */
	public Logger getLog(Class class1) throws WrongKeyException {
		String className = null;
		
		if (class1.getPackage()!=null){
			String s=class1.getPackage().toString().split(" ")[1];
			String s1=class1.toString().split(" ")[1];
			className= new String (s1.substring(s.length()+1, s1.length())+".class");
		}
		else className= new String (class1.toString().split(" ")[1]+".class");
		String[] g = class1.getPackage().toString().split(" ")[1].split("\\.");
		
		StringBuffer buf = new StringBuffer("");
		for(int i=0; i<g.length; i++)
			if (i!=g.length-1) buf.append(g[i]+"/");
				else buf.append(g[i]);

		Logger log = null;
		boolean b = true;
		for (Logger l : loggers){
				if (!b) break; 
				String[] list = new File(l.getPackage()).list(); 
				for(int i=0; i<list.length; i++){
					if (list[i].equals(className)) {b = false; log=l; break;}
			}
		}
		log.setLowerLoggers(setLowerLoggers(log,className));
		log.setClassName(className);
		return log;
	}
}
