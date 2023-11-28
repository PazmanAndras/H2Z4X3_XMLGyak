package hu.domparse.h2z4x3;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryH2Z4X3 {
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		try {
			
			
			//xml file megnyitasa adatok beolvasasahoz
            File file = new File("XMLh2z4x3.xml");

            //dokumentum letrehozasa  a beolvasott file-ból
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            
            //dokumentum normalizálása
            document.getDocumentElement().normalize();    
            
            
            lekerdezes1(document);
            lekerdezes2(document);
            lekerdezes3(document);
            lekerdezes4(document);
            lekerdezes5(document);
            lekerdezes6(document);
            
        
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}   
         
	}
	
	public static void lekerdezes1(Document document) {
		
		try {
			
			System.out.println("\n1. Az A minősítésű telepek kapacitásának lekérdezése");
	         NodeList nList1 = document.getElementsByTagName("telep");
	         
	         for (int i = 0; i < nList1.getLength(); i++) {
	 			
	         	Node nNode = nList1.item(i);
	         	
	 			if (nNode.getNodeType()==Node.ELEMENT_NODE) {       				

	 				Element elem = (Element) nNode;
	 				NodeList childNodes = nNode.getChildNodes();
	 				
	 				for (int j = 0; j < childNodes.getLength(); j++) {
	 					
	 					Node childNode = childNodes.item(j);	
	 					
	 					if ("minősítés".equals(childNode.getNodeName())) {

	 						if ("A".equals(childNode.getTextContent())) {
	 							System.out.println("");
	 							
	 							String telepid = elem.getAttribute("TELEP_ID");    		    				
	 		    				Node node1 = elem.getElementsByTagName("kapacitás").item(0);
	 		    				String node1Text = node1.getTextContent();    		    				
	 		    				 				
	 		    				
	 		    				System.out.println("Telep_ID: " + telepid);
	 		    				System.out.println("kapacitás: " + node1Text);
	 		    				
	 		    				
	 						}
	 					}
	 				}	
	 			}
	 			
		     }
	         System.out.println("*****************************************************************");	
			
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
			
		
	
		
		
		
	}
		
	public static void lekerdezes2(Document document) {
		
		try {
			
			 System.out.println("\n2. Az 1500000ft-nál nagyobb bevételű telep tulajdonosának kiirása");
		        NodeList nList2 = document.getElementsByTagName("tulajdonos");
		        for (int i = 0; i < nList2.getLength(); i++) {
	    			
	            	Node nNode = nList2.item(i);
	            	
	    			if (nNode.getNodeType()==Node.ELEMENT_NODE) {       				

	    				Element elem = (Element) nNode;
	    				NodeList childNodes = nNode.getChildNodes();
	    				
	    				NamedNodeMap attr = nNode.getAttributes();
					
	    				
	    				if (nNode.getNodeType()==Node.ELEMENT_NODE) {
		    				for (int j = 0; j < childNodes.getLength(); j++) {
		    					
		    					Node childNode = childNodes.item(j);	
		    					
		    					if ("bevétel".equals(childNode.getNodeName())) {
		
		    						if (Integer.valueOf(childNode.getTextContent()) > 1500000 ) {
		    							System.out.println("");
		    							
		    							String tulajdonosid = elem.getAttribute("TUL_ID");    		    				
		    		    				Node node1 = elem.getElementsByTagName("név").item(0);
		    		    				String node1Text = node1.getTextContent();    		    						    				
		    		    				System.out.println("Tulajdonos_ID: " + tulajdonosid);
		    		    				System.out.println("név: " + node1Text);	    		    				
		    						}
		    					}
		    				}	
	    				}
	    			}
	    			
	    		}
		       System.out.println("*****************************************************************");
			
			
			
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
	}
		
	public static void lekerdezes3(Document document) {
		
		try {
			
			  System.out.println("\n3.: A '2'-es ID-jű telep autóinak kiiratása amelyek 2020 vagy attól kisebb évjáratúak.");
	            NodeList nList3 = document.getElementsByTagName("autok");
	            
	            for (int i = 0; i < nList3.getLength(); i++) {
	    			
	            	Node nNode = nList3.item(i);
	            	
	    			if (nNode.getNodeType()==Node.ELEMENT_NODE) {       				

	    				Element elem = (Element) nNode;
	    				NodeList childNodes = nNode.getChildNodes();
	    				
	    				
	    				NamedNodeMap attr = nNode.getAttributes();
	    				Node nodeAtribute = attr.getNamedItem("AUTO_TELEP");
	    				
	    				if (nodeAtribute.getTextContent().equals("2")) {
		    				for (int j = 0; j < childNodes.getLength(); j++) {
		    					
		    					Node childNode = childNodes.item(j);	
		    					
		    					if ("gyártásiév".equals(childNode.getNodeName())) {
		
		    						if (Integer.valueOf(childNode.getTextContent()) <= 2020 ) {
		    							System.out.println("");
		    							
		    							String autoid = elem.getAttribute("AUTO_ID");    		    				
		    		    				Node node1 = elem.getElementsByTagName("rendszám").item(0);
		    		    				String node1Text = node1.getTextContent();    		    				
		    		    				Node node2 = elem.getElementsByTagName("modell").item(0);
		    		    				String node2Text = node2.getTextContent();
		    		    				 				    		    				
		    		    				
		    		    				System.out.println("Auto_ID: " + autoid);
		    		    				System.out.println("Rensszám: " + node1Text);
		    		    				System.out.println("modell: " + node2Text);			    		    						    		    				
		    						}
		    					}
		    				}	
	    				}
	    			}
	    			
	    		}
	            System.out.println("*****************************************************************");
			
			
			
		 } catch (NullPointerException e) {
			e.printStackTrace();
		}
		
	}

	public static void lekerdezes4(Document document) {
		
		try {
			
			  System.out.println("\n3.: A '2'-es ID-jű telep autóinak kiiratása amelyek 2020 vagy attól kisebb évjáratúak.");
	            NodeList nList3 = document.getElementsByTagName("autok");
	            
	            for (int i = 0; i < nList3.getLength(); i++) {
	    			
	            	Node nNode = nList3.item(i);
	            	
	    			if (nNode.getNodeType()==Node.ELEMENT_NODE) {       				

	    				Element elem = (Element) nNode;
	    				NodeList childNodes = nNode.getChildNodes();
	    				
	    				
	    				NamedNodeMap attr = nNode.getAttributes();
	    				Node nodeAtribute = attr.getNamedItem("AUTO_TELEP");
	    				
	    				if (nodeAtribute.getTextContent().equals("2")) {
		    				for (int j = 0; j < childNodes.getLength(); j++) {
		    					
		    					Node childNode = childNodes.item(j);	
		    					
		    					if ("gyártásiév".equals(childNode.getNodeName())) {
		
		    						if (Integer.valueOf(childNode.getTextContent()) <= 2020 ) {
		    							System.out.println("");
		    							
		    							String autoid = elem.getAttribute("AUTO_ID");    		    				
		    		    				Node node1 = elem.getElementsByTagName("rendszám").item(0);
		    		    				String node1Text = node1.getTextContent();    		    				
		    		    				Node node2 = elem.getElementsByTagName("modell").item(0);
		    		    				String node2Text = node2.getTextContent();
		    		    				 				    		    				
		    		    				
		    		    				System.out.println("Auto_ID: " + autoid);
		    		    				System.out.println("Rensszám: " + node1Text);
		    		    				System.out.println("modell: " + node2Text);			    		    						    		    				
		    						}
		    					}
		    				}	
	    				}
	    			}
	    			
	    		}
	            System.out.println("*****************************************************************");
			
			
			
		 } catch (NullPointerException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void lekerdezes5(Document document) {
		
		try {			
			 System.out.println("\n5. Írjuk ki a 'Git Áron' elérhetőségeit");
	            NodeList nList5 = document.getElementsByTagName("bérlő");
	            
	            for (int i = 0; i < nList5.getLength(); i++) {
	    			
	            	Node nNode = nList5.item(i);
	            	
	    			if (nNode.getNodeType()==Node.ELEMENT_NODE) {       				

	    				Element elem = (Element) nNode;
	    				NodeList childNodes = nNode.getChildNodes();
	    				
	    				
	    				for (int j = 0; j < childNodes.getLength(); j++) {
	    					
	    					Node childNode = childNodes.item(j);	
	    					
	    					
	    					if ("név".equals(childNode.getNodeName())) {
	    						
					  							
	    							if ("Git Áron".equals(childNode.getTextContent())) {
	        							System.out.println("");
	        							
	        							String telepid = elem.getAttribute("BÉRLŐ_ID");    		    				
	        		    				Node node1 = elem.getElementsByTagName("telszám").item(0);
	        		    				String node1Text = node1.getTextContent();
	        		    				Node node2 = elem.getElementsByTagName("email").item(0);
	        		    				String node2Text = node2.getTextContent(); 

	        		    				System.out.println("Bérlő_id: " + telepid);
	        		    				System.out.println("telszám: " + node1Text);
	        		    				System.out.println("email: " + node2Text);
	        						}		   						    						  						
	    					}
	    				}	
	    			}
	    			
	            }
	            System.out.println("*****************************************************************"); 
			
			
		 } catch (NullPointerException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void lekerdezes6(Document document) {
		
		try {			
			  System.out.println("\n6. A 2-es telep alkalamzottianak az adatai");
	            NodeList nList6 = document.getElementsByTagName("alkalmazott");
	            
	            for (int i = 0; i < nList6.getLength(); i++) {
	    			
	            	Node nNode = nList6.item(i);
	            	
	            	if (nNode.getNodeType()==Node.ELEMENT_NODE) {       				

	    				Element elem = (Element) nNode;    				
	    				NamedNodeMap attr = nNode.getAttributes();
	    				Node nodeAttrM = attr.getNamedItem("ALK_TELEP");

	    				if (nodeAttrM.getTextContent().equals("2")) {	    				

		    					System.out.println("");
		    							
		    					String alkalmazottid = elem.getAttribute("ALK_ID");    		    				
		    		    		Node node1 = elem.getElementsByTagName("név").item(0);
		    		    		String node1Text = node1.getTextContent();    		    				
		    		    		Node node2 = elem.getElementsByTagName("beosztás").item(0);
		    		    		String node2Text = node2.getTextContent();
		    		    		Node node3 = elem.getElementsByTagName("életkor").item(0);
		    		    		String node3Text = node3.getTextContent();   
		    		    		Node node4 = elem.getElementsByTagName("születésiév").item(0);
		    		    		String node4Text = node4.getTextContent();  
		    		    				
		    		    				
		    		    		System.out.println("Alkalmazott_ID: " + alkalmazottid);
		    		    		System.out.println("Név: " + node1Text);
		    		    		System.out.println("Beosztás: " + node2Text);	
		    		    		System.out.println("Életkor: " + node3Text);
		    		    		System.out.println("Születési év: " + node4Text);
		    				}	
	    				
	            	}
	    			
	            }
	            System.out.println("*****************************************************************"); 
			
			
		 } catch (NullPointerException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

