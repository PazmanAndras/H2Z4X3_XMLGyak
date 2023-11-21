package domreadpackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomApi {
	
	
	 public static void main(String[] args) 
	    {
	        
	    	try 
	    	{
	            File inputFile = new File("XMLh2z4x3.xml");
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(inputFile);
	            doc.getDocumentElement().normalize();

	            
	          //Fa struktúra
	            printNode(doc.getDocumentElement(), 0); 
	           
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Bekezdések hozzáadása
	            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // Indentálási mélység

	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(new File("XMLh2z4x3.xml"));
	            transformer.transform(source, result);

	        } 
	    	catch (Exception e) 
	    	{
	            e.printStackTrace();
	        }
	    }

	    private static void printNode(Node node, int depth) 
	    {
	    	
	        String indent = " ".repeat(depth * 4);

	        if (node.getNodeType() == Node.ELEMENT_NODE) 
	        {
	        	
	            System.out.println(indent + "<" + node.getNodeName() + ">");
	            NodeList nodeList = node.getChildNodes();
	            
	            for (int i = 0; i < nodeList.getLength(); i++) 
	            {
	                printNode(nodeList.item(i), depth + 1);
	            }
	            System.out.println(indent + "</" + node.getNodeName() + ">");
	        } 
	        else if (node.getNodeType() == Node.TEXT_NODE && !node.getTextContent().trim().isEmpty()) 
	        {
	            System.out.println(indent + node.getTextContent().trim());
	        }
	        
	    }

}
