package by.epam.dom;

import by.epam.builder.ComputerBuilder;
import by.epam.entity.Computer;
import by.epam.entity.Notebook;
import by.epam.exception.CustomParserException;
import by.epam.handler.ComputerXMLTag;
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
            NodeList deviceList = root.getElementsByTagName(ComputerXMLTag.DEVICE.getValue());
            NodeList notebookList = root.getElementsByTagName(ComputerXMLTag.NOTEBOOK.getValue());
            for (int i = 0; i < deviceList.getLength(); i++) {
                Element deviceElement = (Element) deviceList.item(i);
                Computer computer = buildComputer(deviceElement);
                computers.add(computer);
            }
            for (int i = 0; i < notebookList.getLength(); i++) {
                Element notebookElement = (Element) notebookList.item(i);
                Notebook notebook = buildNotebook(notebookElement);
                computers.add(notebook);
            }
        } catch (IOException | SAXException e){
            logger.error("Parsing failure",e);
            throw new CustomParserException(e);
        }
    }

    private Computer buildComputer(Element deviceElement) {
        Computer computer = new Computer();
        computer.setCritical(Boolean.parseBoolean(getElementTextContent(deviceElement, ComputerXMLTag.CRITICAL.getValue())));
        computer.setComponent(getElementTextContent(deviceElement, ComputerXMLTag.COMPONENT.getValue()));
        computer.setId(getElementTextContent(deviceElement, ComputerXMLTag.ID.getValue()));
        computer.setOrigin(getElementTextContent(deviceElement, ComputerXMLTag.ORIGIN.getValue()));
        String date = getElementTextContent(deviceElement, ComputerXMLTag.DATE.getValue());
        LocalDateTime dateTime = LocalDateTime.parse(date);
        computer.setDate(dateTime);
        Integer price = Integer.parseInt(getElementTextContent(deviceElement, ComputerXMLTag.PRICE.getValue()));
        computer.setPrice(price);
        computer.setPick(deviceElement.getAttribute(ComputerXMLTag.PICK.getValue()));
        if(deviceElement.getAttribute(ComputerXMLTag.MANUFACTURER.getValue()) != "") {
            computer.setManufacturer(deviceElement.getAttribute(ComputerXMLTag.MANUFACTURER.getValue()));
        }
        Computer.Type type = computer.getType();
        Element typeElement = (Element) deviceElement.getElementsByTagName(ComputerXMLTag.TYPE.getValue()).item(0);
        type.setCooler(Boolean.parseBoolean(getElementTextContent(typeElement, ComputerXMLTag.COOLER.getValue())));
        Integer energy = Integer.parseInt(getElementTextContent(typeElement, ComputerXMLTag.ENERGY.getValue()));
        type.setEnergy(energy);
        type.setPeripheral(Boolean.parseBoolean(getElementTextContent(typeElement, ComputerXMLTag.PERIPHERAL.getValue())));
        type.setPorts(getElementArrayTextContent(typeElement,ComputerXMLTag.PORT.getValue()));
        logger.info("Component: " + computer.getComponent() + " set");
        return computer;
    }

    private Notebook buildNotebook(Element notebookElement) {
        Notebook notebook = new Notebook();
        notebook.setCritical(Boolean.parseBoolean(getElementTextContent(notebookElement, ComputerXMLTag.CRITICAL.getValue())));
        notebook.setComponent(getElementTextContent(notebookElement, ComputerXMLTag.COMPONENT.getValue()));
        notebook.setId(getElementTextContent(notebookElement, ComputerXMLTag.ID.getValue()));
        notebook.setOrigin(getElementTextContent(notebookElement, ComputerXMLTag.ORIGIN.getValue()));
        String date = getElementTextContent(notebookElement, ComputerXMLTag.DATE.getValue());
        LocalDateTime dateTime = LocalDateTime.parse(date);
        notebook.setDate(dateTime);
        Integer price = Integer.parseInt(getElementTextContent(notebookElement, ComputerXMLTag.PRICE.getValue()));
        notebook.setPrice(price);
        notebook.setPick(notebookElement.getAttribute(ComputerXMLTag.PICK.getValue()));
        if(notebookElement.getAttribute(ComputerXMLTag.MANUFACTURER.getValue()) != "") {
            notebook.setManufacturer(notebookElement.getAttribute(ComputerXMLTag.MANUFACTURER.getValue()));
        }
        notebook.setWeight(Integer.parseInt(getElementTextContent(notebookElement,ComputerXMLTag.WEIGHT.getValue())));
        notebook.setWifi(Boolean.parseBoolean(getElementTextContent(notebookElement, ComputerXMLTag.WIFI.getValue())));
        notebook.setDisplay(getElementTextContent(notebookElement,ComputerXMLTag.DISPLAY.getValue()));
        Computer.Type type = notebook.getType();
        Element typeElement = (Element) notebookElement.getElementsByTagName(ComputerXMLTag.TYPE.getValue()).item(0);
        type.setCooler(Boolean.parseBoolean(getElementTextContent(typeElement, ComputerXMLTag.COOLER.getValue())));
        Integer energy = Integer.parseInt(getElementTextContent(typeElement, ComputerXMLTag.ENERGY.getValue()));
        type.setEnergy(energy);
        type.setPeripheral(Boolean.parseBoolean(getElementTextContent(typeElement, ComputerXMLTag.PERIPHERAL.getValue())));
        type.setPorts(getElementArrayTextContent(typeElement,ComputerXMLTag.PORT.getValue()));
        logger.info("Component: " + notebook.getComponent() + " set");
        return notebook;
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
