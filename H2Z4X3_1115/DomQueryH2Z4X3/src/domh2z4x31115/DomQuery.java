package domh2z4x31115;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class DomQuery {
	
	public static void main(String[] args) {
		
		 try {
			 
			// List<String> subs = new ArrayList<>();
			 HashSet<String> subs = new HashSet<String>();
			 
			 
			 
			 
	         File inputFile = new File("orarendH2Z4X3.xml");
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         System.out.print("Root element: ");
	         System.out.println(doc.getDocumentElement().getNodeName());
	         NodeList nList = doc.getElementsByTagName("ora");
	        
	         System.out.println("----------------------------");
	         
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nJelenlegi ora.");
	            System.out.print(nNode.getNodeName());
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               System.out.print("tipus : ");
	               System.out.println(eElement.getAttribute("tipus"));
	               NodeList tantargyList = eElement.getElementsByTagName("targy");
	              
	               
	               for (int count = 0; count < tantargyList.getLength(); count++) {
	                  Node node1 = tantargyList.item(count);
	                  
	                  if (node1.getNodeType() == node1.ELEMENT_NODE) {
	                     Element tantargy = (Element) node1;
	                     System.out.print("tantargy : ");
	                     System.out.println(tantargy.getTextContent());
	                  }
	               }
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }

}
