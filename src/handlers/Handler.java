package handlers;

import java.io.IOException;

/**handler of massage*/
public interface Handler {
	/**
	 * process the massage
	 * @param msg massage
	 * @throws IOException
	 */
	void processMassage(String msg) throws IOException;
}
