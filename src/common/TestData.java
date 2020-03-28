package common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TestData {
	public static List<Object[]> readTestData(String testDataFileName, String tcName) {
		List<Object[]> testDataList = new ArrayList<Object[]>();
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(new File(BaseTest.testDataDir+testDataFileName));
			document.getDocumentElement().normalize();
			NodeList tcList = document.getElementsByTagName(tcName);
			for (int j = 0; j < tcList.getLength(); j++) {
				Node tcNode = tcList.item(j);
				if (tcNode.getNodeType() == Node.ELEMENT_NODE) {
					NodeList nList = tcNode.getChildNodes();

					for (int temp = 0; temp < nList.getLength(); temp++) {
						Node node = nList.item(temp);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							HashMap<String, String> rowData = new HashMap<String, String>();
							Element eElement = (Element) node;
							for (int i = 0; i < eElement.getAttributes().getLength(); i++) {
								rowData.put(eElement.getAttributes().item(i).getNodeName(),
										eElement.getAttribute(eElement.getAttributes().item(i).getNodeName()));
							}
							testDataList.add(new Object[] { rowData });
						}
					}
				}
			}

		} catch (SAXException ex) {
		} catch (IOException ex) {
		} catch (ParserConfigurationException ex) {
		}
		return testDataList;
	}

}
