package hu.domparse.h2z4x3;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomModifyH2Z4X3 {
	
	public static void main(String[] args) {
		
		try {
			
			//xxml file megnyitása adatok boelvasásához
			File file = new File("XMLh2z4x3.xml");
	
			//dokumentum létrehozasa a beolvasott filebol
	        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        Document document = documentBuilder.parse(file);
	        
	        //dokumentum normalizásálsa
	        document.getDocumentElement().normalize();
	       
	        
	        //1. képsítés nyelv módosítása maygarról németre 
	              
	        NodeList kepesitesNyelvList = document.getElementsByTagName("képesítés");
	        for (int temp = 0; temp < kepesitesNyelvList.getLength(); temp++) {
				Node node = kepesitesNyelvList.item(temp);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
	
					NodeList childNodes = node.getChildNodes();
					
					for (int j = 0; j < childNodes.getLength(); j++) {
						
						Node childNode = childNodes.item(j);	
						
						if ("nyelv".equals(childNode.getNodeName())) {
	
							if ("magyar".equals(childNode.getTextContent())) {
								childNode.setTextContent("német");							
							}
						}
					}	
				}
			}
	        
	        
	        //2. Bevétel növelése 130 000ft-al Cset Elek-nek.
	        NodeList telepBevetelList = document.getElementsByTagName("tulajdonos");
	        for (int temp = 0; temp < telepBevetelList.getLength(); temp++) {
				Node node = telepBevetelList.item(temp);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
	
					NodeList childNodes = node.getChildNodes();
					
					for (int j = 0; j < childNodes.getLength(); j++) {					
						Node childNode = childNodes.item(j);	
						
						if ("név".equals(childNode.getNodeName())) {						
							if ("Cset Elek".equals(childNode.getTextContent())) {
								
								for (int k = 0; k < childNodes.getLength(); k++) {
									childNode = childNodes.item(k);	
									if ("bevétel".equals(childNode.getNodeName())) {
										childNode.setTextContent("1930000");		
									}
								}												
							}
						}
					}	
				}
			}
	        
	        
	        //3. Ahol a telep kapacitas 5 módosítsuk 6ra
	        NodeList telepKapacatisList = document.getElementsByTagName("telep");
	        for (int temp = 0; temp < telepKapacatisList.getLength(); temp++) {
				Node node = telepKapacatisList.item(temp);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
	
					NodeList childNodes = node.getChildNodes();
					
					for (int j = 0; j < childNodes.getLength(); j++) {					
						Node childNode = childNodes.item(j);	
						
						if ("kapacitás".equals(childNode.getNodeName())) {											
								
							if ("5".equals(childNode.getTextContent())) {
								childNode.setTextContent("6");							
							}
								
						}
					}	
				}
			}
	      
	        
	        //4. Az 5. alkalmazott munkahelyének (telepid) módosítása
	        NodeList alkalmazottNList = document.getElementsByTagName("alkalmazott");
	        for (int temp = 0; temp < alkalmazottNList.getLength(); temp++) {
				Node node = alkalmazottNList.item(temp);
				
				NamedNodeMap attribute = node.getAttributes();
				Node nodeAttributeID = attribute.getNamedItem("ALK_ID");
				Node nodeAttributeFK = attribute.getNamedItem("ALK_TELEP");
				
				if (nodeAttributeID.getTextContent().equals("5")) {
					nodeAttributeFK.setTextContent("4");
				}
			}
			
	        
	        //5. Ahol a bónusz kevesebb mint 20000 , az értéket módosítsuk 15000-re
	        NodeList bonuszList = document.getElementsByTagName("bonusz");
	        for (int temp = 0; temp < bonuszList.getLength(); temp++) {
				Node node = bonuszList.item(temp);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
	
					NodeList childNodes = node.getChildNodes();
					
					for (int j = 0; j < childNodes.getLength(); j++) {
						
						Node childNode = childNodes.item(j);	
						
						if ("extra".equals(childNode.getNodeName())) {
							
							if (Integer.valueOf(childNode.getTextContent()) <= 20000) {
								
								childNode.setTextContent("15000");							
							}
						}
					}	
				}
			}
	        		
	        
	        //a modositott adatok kiirasa konzolra
	    	        
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	
	        DOMSource source = new DOMSource(document);
	
	        System.out.println("---- Modositott fajl -----");       
	        
	        StreamResult consoleResult = new StreamResult(System.out);
	        
	        transformer.transform(source, consoleResult);
	       
	        
		} catch (Exception e ) {
            e.printStackTrace();
        }
	}
	
	

}
