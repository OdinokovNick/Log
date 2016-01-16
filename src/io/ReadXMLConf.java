package io;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import exception.ConfFileFormatException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
 
public class ReadXMLConf {

	private String fileName = "src/conf.xml";

	private ArrayList<String> dir;
	private ArrayList<String> file;
	private ArrayList<String> level;
	private ArrayList<ArrayList<String>> handlers;
	/**
	 * initialization of collections and performance of reading from a configuration file
	 * @throws IOException
	 * @throws ConfFileFormatException
	 */
	public ReadXMLConf() throws IOException, ConfFileFormatException{
		dir = new ArrayList<String>();
		file = new ArrayList<String>();
		level = new ArrayList<String>();
		handlers = new ArrayList<ArrayList<String>>();
		xmlReader();
	}
	/**
	 * initialization of collections and performance of reading from a configuration file
	 * @param fileName file name of other configuration file
	 * @throws IOException
	 * @throws ConfFileFormatException
	 */
	public ReadXMLConf(String fileName) throws IOException, ConfFileFormatException{
		this.fileName = fileName;
		dir = new ArrayList<String>();
		file = new ArrayList<String>();
		level = new ArrayList<String>();
		handlers = new ArrayList<ArrayList<String>>();
		xmlReader();
	}
	/**
	 * reading settings from a configuration xml file
	 * @throws IOException
	 * @throws ConfFileFormatException
	 */
    public void xmlReader(){
        try{
            File xmlFile = new File(fileName);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName(document.getDocumentElement().getChildNodes().item(1).getNodeName());
            for(int tmp = 0; tmp < nodeList.getLength(); tmp++){
                Node node = nodeList.item(tmp);
                ArrayList<String> h = new ArrayList<String>();
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element)node;
                    dir.add(element.getElementsByTagName("package").item(0).getChildNodes().item(0).getNodeValue());
                    file.add(element.getElementsByTagName("logfile").item(0).getChildNodes().item(0).getNodeValue());
                    level.add(element.getElementsByTagName("level").item(0).getChildNodes().item(0).getNodeValue());
                    String[] s = element.getElementsByTagName("handlers").item(0).getChildNodes().item(0).getNodeValue().split(", ");
                    for(int i = 0; i<s.length; i++)
                    	h.add(s[i]);
                }
                handlers.add(h);
            }
        }
        catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
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
		return handlers;
	}
}