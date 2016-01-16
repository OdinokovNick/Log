package filters;

import exception.WrongKeyException;
import loggers.Level;

public class Filter {

	private boolean info = true;
	private boolean warning = true;
	private boolean error = true;
	private boolean fatal = true;

	/**
	 * set filter of message
	 * @param msg level of message
	 * @throws WrongKeyException
	 */
	private void setFilter(String msg) throws WrongKeyException{
		switch(getLevel(msg)){
			case INFO: { break;}
			case WARNING: {info=false; break;}
			case ERROR: {info=false; warning=false; break;}
			case FATAL: {info=false; warning=false; error=false; break;}
			default: throw new WrongKeyException();
		}
	}

	private Level getLevel(String message) throws WrongKeyException{
		try {
			return Level.valueOf(message.toUpperCase());
		} catch (IllegalArgumentException e){
			throw new WrongKeyException();
		}
	}
	/**
	 * constructor of filter
	 * @param s level of massage
	 * @throws WrongKeyException
	 */
	public Filter(String s) throws WrongKeyException{
			setFilter(s);
	}
	/**
	 * Checking of level of message
	 * @param msg level of message
	 * @return true if this message is allowed by filter 
	 * @throws WrongKeyException
	 */
	public boolean isAllowed(String msg) throws WrongKeyException {
		switch (getLevel(msg)) {
			case INFO:
				return info;
			case WARNING:
				return warning;
			case ERROR:
				return error;
			case FATAL:
				return fatal;
			default: throw new WrongKeyException();
		}
	}
}
