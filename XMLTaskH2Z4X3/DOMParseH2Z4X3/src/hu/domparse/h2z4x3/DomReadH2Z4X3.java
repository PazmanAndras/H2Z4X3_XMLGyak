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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomReadH2Z4X3 {
	
	
	
     public static void main(String[] args) {
    	 
    	 
    	//xml read meghívása
        readXMLDocument("XMLh2z4X3.xml");
                
        
    }
     
     
     /*
      * Ez a metodus beolvassa 
      */
     private static void readXMLDocument(String filePath) {
        try {
        	
            File newXMLFile = new File(filePath);
            StreamResult newXmlStream = new StreamResult(newXMLFile);
            
                
               
            Document document = parseXML("./XMLh2z4X3.xml");
            
            writeDocument(document, newXmlStream);

            
            System.out.println(formatXML(document)); //strukturáltan kiiratás
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


     
     
    /*
     * Az alábbi metodus beolvassa az XML dokumentumot
     */
    public static Document parseXML(String fileName) throws  ParserConfigurationException, SAXException, IOException{
    	
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            
            // XML dokumentum beolvasása és DOM dokumentummá alakítása
            Document document = builder.parse(new File(fileName));
            
            
            // metodus meghivas üres szövegek eltávolítására
            cleanDocument(document.getDocumentElement()); 
            
            return document;    
    }

    /*
     *Delete empty lines 
     * 
     */
    private static void cleanDocument(Node root) {
        NodeList nodeList = root.getChildNodes();
        
        // Delete empty text spaces 
        List<Node> toDeleteEmptyTextList = new ArrayList<>();
        
        
        //veigjarni a gyoker elemeket 
        for (int i = 0; i < nodeList.getLength(); i++) {
        	
        	
        	//megnezi hogy a textnode ures-e
            if (nodeList.item(i).getNodeType() == Node.TEXT_NODE && nodeList.item(i).getTextContent().strip().isEmpty()) {
            	
                toDeleteEmptyTextList.add(nodeList.item(i)); //hozzadja
                
            } else {
                cleanDocument(nodeList.item(i));
            }
        }
        
        
      
        for (Node node : toDeleteEmptyTextList) {
        	
        	
            root.removeChild(node);
        }
    }

    /*
     * Az alábbi metodus kiirja file-ba az xml dokumentumot
     */
    public static void writeDocument(Document document, StreamResult output){
        try {    
        	
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            
            Transformer transformer = transformerFactory.newTransformer();
            
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            
            // DOMletrehozas dokumentumbol
            DOMSource source = new DOMSource(document);
            
            transformer.transform(source, output);
            
        } catch (Exception e) {
        	
            e.printStackTrace();
        }  	
    }

    /*
     * XML formazo metodus
     */
    public static String formatXML(Document document) {
    	
        return "<?xml version=\"" + document.getXmlVersion() + "\" encoding=\"" + document.getXmlEncoding() + "\" ?>\n" +
               formatElement(document.getDocumentElement(), 0);
    }
    
    
    /*
     * Az xml kiirast formazza
     */
    public static String formatElement(Node node, int indent) {
    	
    	
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            return "";
        }
        
       
        StringBuilder output = new StringBuilder();
        
        
        output.append(getIndent(indent)).append("<").append(((Element) node).getTagName());
        
        
        if (node.hasAttributes()) {
            for (int i = 0; i < node.getAttributes().getLength(); i++) {
            	
                Node attribute = node.getAttributes().item(i);
                output.append(" ").append(attribute.getNodeName()).append("=\"").append(attribute.getNodeValue()).append("\"");
            }
        }
        
        
        //gyerekek lekerdezese 
        NodeList children = node.getChildNodes();
        
        
        if (children.getLength() == 1 && children.item(0).getNodeType() == Node.TEXT_NODE) {
            output.append(">").append(children.item(0).getTextContent().trim()).append("</").append(((Element) node).getTagName()).append(">\n");
        } else {
        	
        	
            output.append(">\n"); //zarotag
           
            for (int i = 0; i < children.getLength(); i++) {
            	
            	
                output.append(formatElement(children.item(i), indent + 1));
            }
            
            
            // zarotag hozzadassa
            output.append(getIndent(indent)).append("</").append(((Element) node).getTagName()).append(">\n");
        }
        
        
        return output.toString();
    }

    
    private static String getIndent(int indent) {
    	
        StringBuilder indentation = new StringBuilder();
        
        for (int i = 0; i < indent; i++) {
            indentation.append("    ");
        }
        return indentation.toString();
    }
}