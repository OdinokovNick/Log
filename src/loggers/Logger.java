package loggers;

import exception.WrongKeyException;
import filters.Filter;
import handlers.Handler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Logger {
	/**
	 * {@value className} name of class that use this logger 
	 */
	private String className;
	/**
	 * {@value pac} package of class
	 */
	private String pac;
	/**
	 * {@value handlers} collection of massage handlers*
	 */
	private ArrayList<Handler> handlers;
	/**
	 * {@value filter} filter of massage
	 */
	private Filter filter;
	/**
	 * {@value handlers} collection of lower loggers
	 */
	private ArrayList<Logger> lowerLoggers;
	
	/**
	 * default constructor
	 * @throws WrongKeyException
	 */
	public Logger() throws WrongKeyException{		
		handlers= new ArrayList<Handler>();
		lowerLoggers = new ArrayList<Logger>();
	}
	/**
	 * set class name for logger
	 * @param className name of class
	 */
	public void setClassName(String className){
		this.className=className;		
	}
	/**
	 * set package for logger
	 * @param pac package
	 */
	public void setPackage(String pac){
		this.pac = pac;
	}
	/**
	 * set lower loggers for sending message to these loggers
	 * @param l list of lower logger
	 */
	public void setLowerLoggers(ArrayList<Logger> l){
		this.lowerLoggers=l;
	}
	/**
	 * set filter for logger
	 * @param level level of message
	 * @throws WrongKeyException
	 */
	public void setFilter(String level) throws WrongKeyException{
		filter = new Filter(level);
	}
	/**
	 * set handlers of message
	 * @param handlers list of handlers
	 */
	public void setHandlers(ArrayList<Handler> handlers){
		this.handlers = handlers;  
	}
	/**
	 * get package of logger
	 * @return package
	 */
	public String getPackage(){
		return pac;
	}
	/**
	 * get handlers of logger
	 * @return collection of handlers
	 */
	public Collection<Handler> getHandlers(){
		return handlers;
	}
	/**
	 * get filter of logger
	 * @return filter
	 */
	public Filter getFilter(){
		return filter;
	}
	/**
	 * get lower loggers
	 * @return collection of loggers
	 */
	public Collection<Logger> getLowerLoggers(){
		return lowerLoggers;
	}
	/**
	 * transfer massage to handlers
	 * @param msg massage
	 * @throws IOException 
	 */
	private void giveToHandler(String msg) throws IOException{
		for (Handler h : handlers)
			h.processMassage(msg);
	}
	/**
	 * create log message and give this message to handlers
	 * @param level level of message
	 * @param message log message
	 * @throws IOException
	 */
	public void log(String level, String message) throws IOException{
		String newMessage = new String(level.toUpperCase()+" "+className+" "+ message+" "+ new Date());
		giveToHandler(newMessage);
		for (Logger l : lowerLoggers)
			l.log(level, message);
	}
	/**
	 * create log message and give this message to handlers
	 * @param level level of message
	 * @param message log message
	 * @param th exception or error
	 * @throws IOException
	 */
	public void log(String level, String message, Throwable th) throws IOException{
		String newMessage = new String(level.toUpperCase()+" "+className+" "+message+" "+th.toString()+" "+ new Date());
		giveToHandler(newMessage);
		for (Logger l : lowerLoggers)
			l.log(level, message, th);
	}	
}
