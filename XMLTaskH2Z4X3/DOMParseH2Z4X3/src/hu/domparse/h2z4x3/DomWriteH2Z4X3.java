package hu.domparse.h2z4x3;

import java.io.File;
import java.io.FileOutputStream;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DomWriteH2Z4X3 {
	public static void main(String args[]) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
	        Document document = dbBuilder.newDocument();
	        
	        // root elements megadasa
	        Element root = document.createElement("autokolcsonzo_H2Z4X3");
	        root.setAttribute("xmlns:xs", "http://www.w3.org/2001/XMLSchema-instance");
	        root.setAttribute("xs:noNamespaceSchemaLocation", "XMLSchemaH2Z4X3.xsd");
	        document.appendChild(root);
	        
	        // Telep
	        root.appendChild(document.createComment("Telep"));
	        addTelep(document, root, "1", "5", "Budapest", "Benczúr_utca", "23", "B");
	        addTelep(document, root, "2", "8", "Budapest", "Jókai_utca", "14", "A");
	        addTelep(document, root, "3", "6", "Szeged", "Szabadság_utca", "23", "C");
	        addTelep(document, root, "4", "6", "Siófok", "Balaton_utca", "45", "A");
	 

	        // Tulajdonos
	        root.appendChild(document.createComment("Tulajdonos"));
	        addTulajdonos(document, root, "1", "1", "Kis János", "067022344555", "12345678-1-11","1500000" );
	        addTulajdonos(document, root, "2", "2", "Cicz Imre", "06205678899", "18384763-2-24","1234560" );
	        addTulajdonos(document, root, "3", "3", "Cset Elek", "06703452233", "2345679-3-13","1800000" );
	        addTulajdonos(document, root, "4", "4", "Kis Anikó", "06304567788", "12341236-2-31","1850000" );
	 
	        
	        
	        // Autok
	        root.appendChild(document.createComment("Autok"));
	        addAuto(document, root, "1", "1", "PSY-213", "Jaguar_F-TYPE", "JZMMA18P200411817","2020" );
	        addAuto(document, root, "2", "2", "RYZ-022", "Mercedes_S63_AMG", "JZMMA18P200411817","2021" );
	        addAuto(document, root, "3", "3", "SUV-123", "Volvo_XC-90", "JZMMA18P201111111","2018");
	        addAuto(document, root, "4", "4", "SRY-345", "Audi_A5", "JZMMA18P200411817","2020" );
	        addAuto(document, root, "5", "4", "PSY-234", "BMW_M5_Competition", "PZMMA18P200434567","2018" );
	     
	        
	        // Berlo
	        root.appendChild(document.createComment("Berlo"));
	        addBerlo(document, root, "1", "2", "Diz Elek", "06703439988", "dizelek@gmail.com","WW001002" );
	        addBerlo(document, root, "2", "3", "Farkas Piroska", "06703439988", "piroksa@gfreemail.hu","WW001002" );
	        addBerlo(document, root, "3", "4", "Feles Elek", "06703439988", "felelek@gmail.com","MM020105" );
	        addBerlo(document, root, "4", "5", "Git Áron", "06204563322", "gitaron@citromail.hu","WW001002" );
	        
	        
	        // Alkalmazott
	        root.appendChild(document.createComment("Akalmazott"));
	        addAlkalmazott(document, root, "1", "2", "Hideg Ottó", "Adminisztrátor", "48","1975" );
	        addAlkalmazott(document, root, "2", "3", "Kis Eszter", "Üzletvezető", "45","1978" );
	        addAlkalmazott(document, root, "3", "1", "Pat Tamás", "supervisor", "35","1988" );
	        addAlkalmazott(document, root, "4", "4", "Tra Pista", "marketing_manager", "39","1984" );
	        addAlkalmazott(document, root, "5", "2", "Tra Pista", "hr_vezető", "45","1978" );
	        

	        // Bonusz
	        root.appendChild(document.createComment("Bonusz"));
	        addBonus(document, root, "1", "1", "1", "10000");
	        addBonus(document, root, "2", "2", "2", "25000");
	        addBonus(document, root, "3", "3", "3", "30000");
	        
	        
	    
	        
	        
	        // Kepesites
	        root.appendChild(document.createComment("Képsítés"));
	        addKepesites(document, root, "1",  "7245889", "2021","angol", "A2" );
	        addKepesites(document, root, "2",  "7245867", "2015","magyar", "B2" );
	        addKepesites(document, root, "3",  "7224589", "2010","angol", "C1" );
	        addKepesites(document, root, "4",  "7298174", "2011","angol", "B1" );

	        
	        // Documentum felépítése
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");
			
			// Kimeneti fájl
			DOMSource source = new DOMSource(document);
			File outputFile = new File("XmlH2Z4X3_out.xml");
			StreamResult file = new StreamResult(outputFile);
			transformer.transform(source, file);
			
			//Konzolra kiírás
			StreamResult console = new StreamResult(System.out);
			transformer.transform(source, console);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void addTelep(Document document, Element root, String id, String kapacitas, String varos,
			String utca, String hazszam, String minosites) {
		
		
		// Létrehoz egy "telep" elemet a megadott paraméterekkel.
		Element carstation = document.createElement("telep");
		carstation.setAttribute("TELEP_ID", id);
		
	    // Létrehoz egy "kapacitás" elemet és hozzáadja az értéket.

		Element capacity = createElementValue(document, "kapacitás", kapacitas);
	    // Létrehoz egy "cim" elemet és hozzáadja az "irányítószám", "város", "utca_házszám" elemeket.

		Element address = document.createElement("cim");
		Element city = createElementValue(document, "irányítószám", varos);
		Element street = createElementValue(document, "város", utca);
		Element number = createElementValue(document, "utca_házszám", hazszam);
		
		Element quality = createElementValue(document, "mennyiség", minosites);

	    // Hozzáadja az elemeket a megfelelő hierarchiában az "telep" elemhez.
		address.appendChild(city);
		address.appendChild(street);
		address.appendChild(number);
		
		carstation.appendChild(capacity);
		carstation.appendChild(address);
		carstation.appendChild(quality);
		
		 // Hozzáadja az "telep" elemet a gyökérelemhez.
		root.appendChild(carstation);
	}
	
	public static void addTulajdonos(Document document, Element root, String id, String fk, String nev,
			String telszam, String adoszam, String bevetel) {
		
		Element owners = document.createElement("tulajdonos");
		owners.setAttribute("TELEP_ID", id);
		owners.setAttribute("TUL_TELEP", fk);
		
		// Elem létrehozása  a megadott paraméterekkel.
		Element name = createElementValue(document, "név", nev);	
		Element phone = createElementValue(document, "telszám", telszam);		
		Element taxid = createElementValue(document, "adoszám", adoszam);
		Element income = createElementValue(document, "bevétel", bevetel);

		
		owners.appendChild(name);
		owners.appendChild(phone);
		owners.appendChild(taxid);
		owners.appendChild(income);
		
		root.appendChild(owners);
	}
	
	
	public static void addAuto(Document document, Element root, String id, String fk, String rendszam,
			String modell, String alvazszam, String gyartev) {
		
		Element cars = document.createElement("autok");
		cars.setAttribute("AUTO_ID", id);
		cars.setAttribute("AUTO_TELEP", fk);
		
		// Elem létrehozása  a megadott paraméterekkel.
		Element licence = createElementValue(document, "rendszám", rendszam);	
		Element model = createElementValue(document, "modell", modell);		
		Element framenumber = createElementValue(document, "alvázszám", alvazszam);
		Element buildyear = createElementValue(document, "gáyrtásiév", gyartev);

		 // Hozzáadja az elemeket a megfelelő hierarchiában
		cars.appendChild(licence);
		cars.appendChild(model);
		cars.appendChild(framenumber);
		cars.appendChild(buildyear);
		
		 // Hozzáadja az "telep" elemet a gyökérelemhez.
		root.appendChild(cars);
	}
	
	public static void addBerlo(Document document, Element root, String id, String fk, String p1,
			String p2, String p3, String p4) {
		
		Element cars = document.createElement("bérlő");
		cars.setAttribute("BÉRLŐ_ID", id);
		cars.setAttribute("BÉRLŐ_AUTO", fk);
		
		// Elem létrehozása  a megadott paraméterekkel.
		Element name = createElementValue(document, "név", p1);	
		Element phone = createElementValue(document, "telszám", p2);		
		Element email = createElementValue(document, "email", p3);
		Element income = createElementValue(document, "jogositvanyszam", p4);

		 // Hozzáadja az elemeket a megfelelő hierarchiában
		cars.appendChild(name);
		cars.appendChild(phone);
		cars.appendChild(email);
		cars.appendChild(income);
		
		 // Hozzáadja az "telep" elemet a gyökérelemhez.
		root.appendChild(cars);
	}
	
	public static void addAlkalmazott(Document document, Element root, String id, String fk, String p1,
			String p2, String p3, String p4) {
		
		Element cars = document.createElement("alkalmazottak");
		cars.setAttribute("AUTO_ID", id);
		cars.setAttribute("AUTO_TELEP", fk);
		
		// Elem létrehozása  a megadott paraméterekkel.
		Element value1 = createElementValue(document, "név", p1);	
		Element value2 = createElementValue(document, "beosztás", p2);		
		Element value3 = createElementValue(document, "életkor", p3);
		Element value4 = createElementValue(document, "születésiév", p4);

		 // Hozzáadja az elemeket a megfelelő hierarchiában
		cars.appendChild(value1);
		cars.appendChild(value2);
		cars.appendChild(value3);
		cars.appendChild(value4);
		
		 // Hozzáadja az "telep" elemet a gyökérelemhez.
		root.appendChild(cars);
	}
	
	public static void addBonus(Document document, Element root, String id, String div_fk,
			String kepesites_fk, String bonus) {
		Element extra = document.createElement("bonusz");
		extra.setAttribute("BONUSZ_ID", id);
		extra.setAttribute("ALKALMAZOTT_FK", div_fk);
		extra.setAttribute("KÉPESÍTÉS_FK", kepesites_fk);
		// Elem létrehozása  a megadott paraméterekkel.
		Element _date = createElementValue(document, "mikortól", bonus);
		extra.appendChild(_date);
		
		 // Hozzáadja az "telep" elemet a gyökérelemhez.
		root.appendChild(extra);
	}
	
	public static void addKepesites(Document document, Element root, String id,  String p1,
			String p2, String p3, String p4) {
		
		Element certificate = document.createElement("képsítés");
		certificate.setAttribute("AUTO_ID", id);
		
		// Elem létrehozása  a megadott paraméterekkel.
		Element value1 = createElementValue(document, "kapacitás", p1);	
		Element value2 = createElementValue(document, "kapacitás", p2);		
		Element value3 = createElementValue(document, "kapacitás", p3);
		Element value4 = createElementValue(document, "kapacitás", p4);

		 // Hozzáadja az elemeket a megfelelő hierarchiában
		certificate.appendChild(value1);
		certificate.appendChild(value2);
		certificate.appendChild(value3);
		certificate.appendChild(value4);
		
		 // Hozzáadja az "telep" elemet a gyökérelemhez.
		root.appendChild(certificate);
	}


	
	public static Element createElementValue(Document doc, String name, String value) {
		Element elem = doc.createElement(name);
		elem.appendChild(doc.createTextNode(value));
		return elem;
	}
}