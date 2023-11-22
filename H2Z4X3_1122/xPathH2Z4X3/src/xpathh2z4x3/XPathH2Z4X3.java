package xpathh2z4x3;

import java.io.File;
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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;



public class XPathH2Z4X3 {
	
	public static void main(String[] args) {
		
		try {
			
			
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
			Document document = documentBuilder.parse("studentH2Z4X3.xml");
			
			document.getDocumentElement().normalize();
			
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			//eleri utvonalat megadni
			
			//#1 Válassza ki az összes student element, amely a class gyermekei
			//String expression = "class/student";
			
			//#2 Válassza ki azt a student elemet, amely rendelkezik "id" attribúttal és értéke "02"
			//String expression = "class/student[@id=2]";
			
			//#3 Kiválasztja az összes student elemet, amely a class root element gyermeke!
			//String expression = "//student";

			//#4 Válassza ki a második student elemet, amely a class root element gyermeke
			//String expression = "class/student[position()=2]";
			
			//#5  Válassza ki az utolso  student elemet, amely a class root element gyermeke
			//String expression = "class/student[last()]";

			//#6  Válassza ki az  utolsó előtti student elemet, amely a class root element gyermeke
			//String expression = "class/student[last()-1]";

			//#7 Válassza ki az rldő két student elemet, amely a class rott element gyermeke
			//String expression = "class/student[position()<3]";

			//#8 Válassza ki a class rot element összes gyermek elemét
			//String expression = "class/*";

			//#9 Válassza ki az összes student elemet, amely rendelkezik legalább egy bármilyen attribútummal
			//String expression = "class/student[*]";

			//#10 Válassza ki a dokumentum összes elemét
			//String expression = "*";

			//#11 Válassza ki a class root element összes student elemét, amelynél a kor>20
			//String expression = "class/student[kor > 20]";

			//#12 Válassza ki az összes student elem összes keresztnev or vezeteknev csomopontot
			String expression = "class/student/keresztnév | class/student/vezetéknév";


			

		
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document,XPathConstants.NODESET);
			
			for (int i = 0; i< nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				
				System.out.println("\nAktuális elem: " + node.getNodeName());
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
					Element element = (Element) node;
					
					System.out.println("Hallgat ID: " + element.getAttribute("id"));
					System.out.println("Keresztnév: " + element.getElementsByTagName("keresztnév").item(0).getTextContent());
					System.out.println("Vezetéknév: " + element.getElementsByTagName("vezetéknév").item(0).getTextContent());
					System.out.println("Becenév: " + element.getElementsByTagName("becenév").item(0).getTextContent());
					System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());				
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
