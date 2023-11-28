package hu.domparse.h2z4x3;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomReadH2Z4X3 {
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
		
		
		//xml file megnyitasa adatok beolvasahoz
		File xmlFile = new File("XMLh2z4x3.xml");
		
		//dokumentum letrehozasa
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//dokumentum letrehozasa a beolvasott filebol majd normalizalasa
		Document document = dBuilder.parse(xmlFile);		
		document.getDocumentElement().normalize();
		
		System.out.println("Root element: " + document.getDocumentElement().getNodeName());
		
		
		//beolvasasi metodusok 
		getTelep(document);
		getTulajdonos(document);
		getAutok(document);
		getBerlo(document);
		getAlkalmazott(document);
		getBonusz(document);
		getKepesites(document);
		
		

		 //a megadott adatok adatok kiiras txt fileba
		
        File modFile = new File("XMLH2Z4X3.txt");
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(document);   
        
        StreamResult resultModFile = new StreamResult(modFile);        
        transformer.transform(source, resultModFile );
		
		
	}
	public static void getTelep(Document doc) {
		
		NodeList nList = doc.getElementsByTagName("telep");
		
		System.out.println("\nTelep:\n---------------------");
		
		for (int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if (nNode.getNodeType()==Node.ELEMENT_NODE) {
				
				Element elem = (Element) nNode;
				String telepid = elem.getAttribute("TELEP_ID");
			
				Node node1 = elem.getElementsByTagName("kapacitás").item(0);
				String node1Text = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("település").item(0);
				String node2Text = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("utca").item(0);
				String node3Text = node3.getTextContent();
				
				Node node5 = elem.getElementsByTagName("házszám").item(0);
				String node5Text = node5.getTextContent();
				
				Node node6 = elem.getElementsByTagName("minősítés").item(0);
				String node6Text = node6.getTextContent();	
				
				System.out.println("Rendelés id: " + telepid);
						
				
				System.out.println("Kapacitás:" + node1Text);
				
				System.out.println("  <település>: " + node2Text + "</település>");
				
				System.out.println("  <utca>: " + node3Text+"</utca>");
				
				System.out.println("  <házszám>: " + node5Text + "</házszám>");
				
				System.out.println("<minősítés>: " + node6Text+"</minősítés>");
			}
			
		}
		
		
	}
		
	
	public static void getTulajdonos(Document doc) {
		
	NodeList nList = doc.getElementsByTagName("tulajdonos");
		
		System.out.println("\nTulajdonos:\n---------------------");
		
		for (int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			//System.out.println("\nCurrent Element: " + nNode.getNodeName());
			System.out.println("\n");
			
			if (nNode.getNodeType()==Node.ELEMENT_NODE) {
				
				Element elem = (Element) nNode;
				String tulajdonosid = elem.getAttribute("TUL_ID");
				String fk = elem.getAttribute("TUL_TELEP");
			
				Node node1 = elem.getElementsByTagName("név").item(0);
				String node1Text = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("telszám").item(0);
				String node2Text = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("adoszam").item(0);
				String node3Text = node3.getTextContent();
				
				Node node5 = elem.getElementsByTagName("bevétel").item(0);
				String node5Text = node5.getTextContent();
				
				
				
				System.out.println("Tulajdonos_ID: " + tulajdonosid);
				System.out.println("TUL_TELEP FK: " + fk);
						
				
				System.out.println(" <Név>:" + node1Text + "</Név>:");
				
				System.out.println(" <telefonszám> " + node2Text + " </település>");
				
				System.out.println(" <adószám> " + node3Text+" </utca>");
				
				System.out.println(" <bevétel> " + node5Text + " </házszám>");
				
				
			}
			
		}		
		
		
		
	}
	
	public static void getAutok(Document doc) {
		
	  NodeList nList = doc.getElementsByTagName("autok");
		
		System.out.println("\nAutok:\n---------------------");
		
		for (int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			//System.out.println("\nCurrent Element: " + nNode.getNodeName());
			System.out.println("\n");
			
			if (nNode.getNodeType()==Node.ELEMENT_NODE) {
				
				Element elem = (Element) nNode;
				String id = elem.getAttribute("AUTO_ID");
				String fk = elem.getAttribute("AUTO_TELEP");
			
				Node node1 = elem.getElementsByTagName("rendszám").item(0);
				String node1Text = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("modell").item(0);
				String node2Text = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("alvázszám").item(0);
				String node3Text = node3.getTextContent();
				
				Node node5 = elem.getElementsByTagName("gyártásiév").item(0);
				String node5Text = node5.getTextContent();
				
				
				
				System.out.println("AUTO_ID: " + id);
				System.out.println("AUTO_TELEP FK: " + fk);
										
				System.out.println(" <rendszám>:" + node1Text + "</rendszám>");
				
				System.out.println(" <modell> " + node2Text + " </modell>");
				
				System.out.println(" <alvázszám> " + node3Text+" </alvázszám>");
				
				System.out.println(" <gyártásiév> " + node5Text + " </gyártásiév>");
				
				
			}
			
		}
			
		
	}
	public static void getBerlo(Document doc) {
		
		 NodeList nList = doc.getElementsByTagName("bérlő");
			
			System.out.println("\nBérlők:\n---------------------");
			
			for (int i = 0; i < nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
				
				//System.out.println("\nCurrent Element: " + nNode.getNodeName());
				System.out.println("\n");
				
				if (nNode.getNodeType()==Node.ELEMENT_NODE) {
					
					Element elem = (Element) nNode;
					String id = elem.getAttribute("BÉRLŐ_ID");
					String fk = elem.getAttribute("BÉRLŐ_AUTO");
				
					Node node1 = elem.getElementsByTagName("név").item(0);
					String node1Text = node1.getTextContent();
					
					Node node2 = elem.getElementsByTagName("telszám").item(0);
					String node2Text = node2.getTextContent();
					
					Node node3 = elem.getElementsByTagName("email").item(0);
					String node3Text = node3.getTextContent();
					
					Node node5 = elem.getElementsByTagName("jogositvanyszam").item(0);
					String node5Text = node5.getTextContent();
					
					
					
					System.out.println("AUTO_ID: " + id);
					System.out.println("AUTO_TELEP FK: " + fk);
											
					System.out.println(" <név>:" + node1Text + "</név>");
					
					System.out.println(" <telszám> " + node2Text + " </telszám>");
					
					System.out.println(" <email> " + node3Text+" </email>");
					
					System.out.println(" <jogosítványszám> " + node5Text + " </jogosítványszám>");
					
					
				}
				
			}
			
	}
	public static void getAlkalmazott(Document doc) {
		
		NodeList nList = doc.getElementsByTagName("alkalmazott");
		
		System.out.println("\nAlkalmazottak:\n---------------------");
		
		for (int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			//System.out.println("\nCurrent Element: " + nNode.getNodeName());
			System.out.println("\n");
			
			if (nNode.getNodeType()==Node.ELEMENT_NODE) {
				
				Element elem = (Element) nNode;
				String id = elem.getAttribute("ALK_ID");
				String fk = elem.getAttribute("ALK_ID");
			
				Node node1 = elem.getElementsByTagName("név").item(0);
				String node1Text = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("beosztás").item(0);
				String node2Text = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("életkor").item(0);
				String node3Text = node3.getTextContent();
				
				Node node5 = elem.getElementsByTagName("születésiév").item(0);
				String node5Text = node5.getTextContent();
				
				
				
				System.out.println("ALK_ID: " + id);
				System.out.println("ALK_ID FK: " + fk);
										
				System.out.println(" <név>:" + node1Text + "</név>");
				
				System.out.println(" <beosztás> " + node2Text + " </beosztás>");
				
				System.out.println(" <életkor> " + node3Text+" </életkor>");
				
				System.out.println(" <születésiév> " + node5Text + " </születésiév>");
				
				
			}
			
		}
		
		
	}
	public static void getBonusz(Document doc) {
		
		NodeList nList = doc.getElementsByTagName("bonusz");
		
		System.out.println("\nBonusz:\n---------------------");
		
		for (int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			//System.out.println("\nCurrent Element: " + nNode.getNodeName());
			System.out.println("\n");
			
			if (nNode.getNodeType()==Node.ELEMENT_NODE) {
				
				Element elem = (Element) nNode;
				String id = elem.getAttribute("BONUSZ_ID");
				String fk = elem.getAttribute("ALKALMAZOTT_FK");
				String fk2 = elem.getAttribute("KÉPESÍTÉS_FK");
			
				Node node1 = elem.getElementsByTagName("extra").item(0);
				String node1Text = node1.getTextContent();
			
				
				
				
				System.out.println("ALK_ID: " + id);
				System.out.println("ALK_ID FK: " + fk);
				System.out.println("ALK_ID FK: " + fk2);
										
				System.out.println(" <extra>:" + node1Text + "</extra>");
				
						
			}
			
		}
		
		
	}
	public static void getKepesites(Document doc) {

		NodeList nList = doc.getElementsByTagName("képesítés");
		
		System.out.println("\nKépsítés:\n---------------------");
		
		for (int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			//System.out.println("\nCurrent Element: " + nNode.getNodeName());
			System.out.println("\n");
			
			if (nNode.getNodeType()==Node.ELEMENT_NODE) {
				
				Element elem = (Element) nNode;
				String id = elem.getAttribute("KÉPESÍTÉS_ID");
				
			
				Node node1 = elem.getElementsByTagName("bizoszítványszám").item(0);
				String node1Text = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("év").item(0);
				String node2Text = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("nyelv").item(0);
				String node3Text = node3.getTextContent();
				
				Node node5 = elem.getElementsByTagName("tipus").item(0);
				String node5Text = node5.getTextContent();
				
				
				
				System.out.println("KÉPESÍTÉS_ID: " + id);
				
										
				System.out.println(" <bizoszítványszám>:" + node1Text + "</bizoszítványszám>");
				
				System.out.println(" <év> " + node2Text + " </év>");
				
				System.out.println(" <nyelv> " + node3Text+" </nyelv>");
				
				System.out.println(" <tipus> " + node5Text + " </tipus>");
				
				
			}
			
		}
		
	}
	
	

}