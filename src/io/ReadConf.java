package io;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import managers.FileFormat;
import exception.ConfFileFormatException;

/**
 * This class read configuration of loggers for log manager
 * @author Александр
 *
 */
public class ReadConf {

	private String fileName = "src/conf.txt";
	private ArrayList<String> dir, file, level;

	private ArrayList<ArrayList<String>> handler;

	/**
	 * initialization of collections and performance of reading from a configuration file
	 * @throws IOException
	 * @throws ConfFileFormatException
	 */
	public ReadConf() throws IOException, ConfFileFormatException{
		dir=new ArrayList<String>();
		file=new ArrayList<String>();
		level=new ArrayList<String>();
		handler=new ArrayList<ArrayList<String>>();
		fileReader();
	}
	/**
	 * initialization of collections and performance of reading from a configuration file
	 * @param fileName file name of other configuration file
	 * @throws IOException
	 * @throws ConfFileFormatException
	 */
	public ReadConf(String fileName) throws IOException, ConfFileFormatException{
		this.fileName = fileName;
		dir=new ArrayList<String>();
		file=new ArrayList<String>();
		level=new ArrayList<String>();
		handler=new ArrayList<ArrayList<String>>();
		fileReader();
	}

	private void fileReader() throws IOException, ConfFileFormatException {
		BufferedReader in = new BufferedReader(new FileReader( new File(fileName).getAbsoluteFile()));
		try {
	 		String s = null;
	 		Pattern p = Pattern.compile("((#?file[ \t]+=[ \t]+([a-zA-Z_:]/?)+[.]txt$)|(#?package[ \t]+=[ \t]+([a-zA-Z:_]/?)+$)|(#?level[ \t]+=[ \t]+[a-zA-Z]+$)|(#?handler[ \t]+=[ \t]+[a-zA-Z, \t]+$)|()|(#.+))");
	 		while ((s = in.readLine()) != null){
				Matcher m = p.matcher(s);
	 			boolean b = m.matches();
	 			if (!b) throw new ConfFileFormatException(); else
	 				if (!"".equals(s) && !"#".equals(s.substring(0,1))) processLine(s);
	 		}
	 	}
	 	catch(IOException e) {
            e.printStackTrace();
        }
		catch(ConfFileFormatException e) {
			e.printStackTrace();
        }
	 	finally {
	 		in.close();
	    }
	}

	private void processLine(String s) throws ConfFileFormatException{
		String arrWords[] = s.split(" ");
		FileFormat ff = FileFormat.valueOf(arrWords[0].toUpperCase());
		switch(ff){
			case PACKAGE : {dir.add(arrWords[2]); break;}
			case FILE : {file.add(arrWords[2]); break;}
			case LEVEL : {level.add(arrWords[2]); break;}
			case HANDLER : {
					ArrayList<String> handl = new ArrayList<String>();
					for (int i=2; i <arrWords.length; i++){
						if (",".equals(arrWords[i].substring(arrWords[i].length()-1,arrWords[i].length()))) 
								handl.add(arrWords[i].substring(0,arrWords[i].length()-1));
						else handl.add(arrWords[i]);
					}
					handler.add(handl); break;
			}
			default: throw new ConfFileFormatException();
		}
		
	}
	/**
	 * 
	 * @return list of package
	 */
	public ArrayList<String> getPackageList(){
		return dir;
	}
	/**
	 * @return list of logs files
	 */
	public ArrayList<String> getFileList(){
		return file;
	}
	/**
	 * 
	 * @return list of levels of message
	 */
	public ArrayList<String> getLevelList(){
		return level;
	}
	/**
	 * 
	 * @return list of handler lists
	 */
	public ArrayList<ArrayList<String>> getHandlerList(){
		return handler;
	}
	
}
