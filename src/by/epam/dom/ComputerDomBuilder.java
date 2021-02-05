package by.epam.dom;

import by.epam.builder.ComputerBuilder;
import by.epam.entity.Computer;
import by.epam.exception.CustomParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDateTime;

public class ComputerDomBuilder extends ComputerBuilder {
    private static Logger logger = LogManager.getLogger();
    private DocumentBuilder docBuilder;

    public ComputerDomBuilder() throws CustomParserException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("error in Dom builder",e);
            throw new CustomParserException(e);
        }
    }

    @Override
    public void buildSetComputers(String fileName) throws CustomParserException {
        Document doc;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList deviceList = root.getElementsByTagName("Device");
            for (int i = 0; i < deviceList.getLength(); i++) {
                Element deviceElement = (Element) deviceList.item(i);
                Computer computer = buildStudent(deviceElement);
                computers.add(computer);
            }
        } catch (IOException | SAXException e){
            logger.error("Parsing failure",e);
            throw new CustomParserException(e);
        }
    }

    private Computer buildStudent(Element deviceElement) {
        Computer computer = new Computer();
        computer.setCritical(Boolean.parseBoolean(getElementTextContent(deviceElement, "critical")));
        computer.setComponent(getElementTextContent(deviceElement, "component"));
        computer.setId(getElementTextContent(deviceElement, "id"));
        computer.setOrigin(getElementTextContent(deviceElement, "origin"));
        String date = getElementTextContent(deviceElement, "date");
        LocalDateTime dateTime = LocalDateTime.parse(date);
        computer.setDate(dateTime);
        Integer price = Integer.parseInt(getElementTextContent(deviceElement, "price"));
        computer.setPrice(price);
        computer.setPick(deviceElement.getAttribute("pick"));
        if(deviceElement.getAttribute("manufacturer") != "") {
            computer.setManufacturer(deviceElement.getAttribute("manufacturer"));
        }
        Computer.Type type = computer.getType();
        Element typeElement = (Element) deviceElement.getElementsByTagName("type").item(0);
        type.setCooler(Boolean.parseBoolean(getElementTextContent(typeElement, "cooler")));
        Integer energy = Integer.parseInt(getElementTextContent(typeElement, "energy"));
        type.setEnergy(energy);
        type.setPeripheral(Boolean.parseBoolean(getElementTextContent(typeElement, "peripheral")));
        type.setPorts(getElementArrayTextContent(typeElement,"port"));
        logger.info("Component: " + computer.getComponent() + " set");
        return computer;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

    private static String[] getElementArrayTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        String[] text = new String[nList.getLength()];
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            text[i] = node.getTextContent();
        }
        return text;
    }
}
