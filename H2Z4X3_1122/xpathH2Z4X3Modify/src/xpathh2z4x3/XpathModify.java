package xpathh2z4x3;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XpathModify {
	
public static void main(String[] args) {
		
		try {
			
			
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
			Document document = documentBuilder.parse("studentH2Z4X3.xml");
			
			document.getDocumentElement().normalize();
			
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			//eleri utvonalat megadni
			

			//#1 Válassza ki a második student elemet, amely a class root element gyermeke
			//String expression = "class/orarend[position()=2]";
			
			//#2  Válassza ki az utolso  student elemet, amely a class root element gyermeke
			//String expression = "class/orarend[last()]";

			//#3  Válassza ki az  utolsó előtti orarnedd elemet, amely a class root element gyermeke
			//String expression = "class/orarend[last()-1]";


			
			myNodeList.item(0).setNodeValue("");
		
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document,XPathConstants.NODESET);
			
			for (int i = 0; i< nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				
				System.out.println("\nAktuális elem: " + node.getNodeName());
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
					Element element = (Element) node;
					
					System.out.println("Targy ID: " + element.getAttribute("id"));
					System.out.println("Targy: " + element.getElementsByTagName("targy").item(0).getTextContent());
					System.out.println("Idopont: " + element.getElementsByTagName("idopont").item(0).getTextContent());
					System.out.println("helyszin: " + element.getElementsByTagName("helyszin").item(0).getTextContent());
					System.out.println("oktato: " + element.getElementsByTagName("oktato").item(0).getTextContent());		
					System.out.println("szak: " + element.getElementsByTagName("szak").item(0).getTextContent());			
				}
				
			}
			
		}	catch(ParserConfigurationException e)	{
			e.printStackTrace();
		}	catch(SAXException e)	{
			e.printStackTrace();
		}	catch(IOException e)	{
			e.printStackTrace();
		}	catch(XPathExpressionException e)	{
			e.printStackTrace();
		}
		
	}

}
