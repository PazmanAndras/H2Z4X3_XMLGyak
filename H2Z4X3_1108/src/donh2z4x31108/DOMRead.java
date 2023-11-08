package donh2z4x31108;

import java.io.File;

import java.io.IOException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DOMRead {
	
	public static void main(String[] args )  {
		
		 try {
			 
	         File inputFile = new File("orarendH2Z4X3.xml");
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	         NodeList nList = doc.getElementsByTagName("ora");
	         System.out.println("----------------------------");
	         
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            //System.out.println("\nCurrent Element :" + nNode.getNodeName());
	            System.out.println(" ");
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               System.out.println("ID: " + eElement.getAttribute("id"));
	               System.out.println("Tipus:  " 
	                  + eElement.getAttribute("tipus") );
	               System.out.println("Targy : " 
	 	                  + eElement
	 	                  .getElementsByTagName("targy")
	 	                  .item(0)
	 	                  .getTextContent());
	               /*
	               System.out.println("Idopont : " 
	                  + eElement
	                  .getElementsByTagName("idopont")
	                  .item(0)
	                  .getTextContent());
	               */
	               System.out.println("Idopont : " 
	 	                  + eElement
	 	                  .getElementsByTagName("nap")
	 	                  .item(0)
	 	                  .getTextContent() + " "+ eElement .getElementsByTagName("tol") .item(0) .getTextContent() + " - "+eElement .getElementsByTagName("ig") .item(0) .getTextContent() );
	               
	               System.out.println("Helyszin : " 
	                  + eElement
	                  .getElementsByTagName("helyszin")
	                  .item(0)
	                  .getTextContent());
	               System.out.println("Oktato : " 
	                  + eElement
	                  .getElementsByTagName("oktato")
	                  .item(0)
	                  .getTextContent());
	               System.out.println("Szak : " 
	                  + eElement
	                  .getElementsByTagName("szak")
	                  .item(0)
	                  .getTextContent());
	            }
	         }
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
		
		
	}
	
	
	

}
