package by.epam.stax;

import by.epam.builder.ComputerBuilder;
import by.epam.entity.Computer;
import by.epam.entity.ComputerXMLTag;
import by.epam.exception.CustomParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ComputerStaxBuilder extends ComputerBuilder {
    private static Logger logger = LogManager.getLogger();
    private XMLInputFactory inputFactory;

    public ComputerStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildSetComputers(String fileName) throws CustomParserException {
        XMLStreamReader reader;
        String name;
        try(FileInputStream inputStream = new FileInputStream(new File(fileName))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(ComputerXMLTag.DEVICE.getValue())) {
                        Computer computer = buildComputer(reader);
                        computers.add(computer);
                    }
                }
            }
        }
        catch (XMLStreamException | FileNotFoundException e) {
            logger.error("Parsing exception",e);
            throw new CustomParserException(e);
        } catch (IOException e) {
            logger.error("Problems with file",e);
            throw new CustomParserException(e);
        }
    }

    private Computer buildComputer(XMLStreamReader reader) throws XMLStreamException {
        Computer computer = new Computer();
        computer.setPick(reader.getAttributeValue(null, ComputerXMLTag.PICK.getValue()));
        String manufacturerAttr = reader.getAttributeValue(null, ComputerXMLTag.MANUFACTURER.getValue());
        if(manufacturerAttr != null) {
            computer.setManufacturer(manufacturerAttr);
        }
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (ComputerXMLTag.valueOf(name.toUpperCase())) {
                        case ID ->  computer.setId(getXMLText(reader));
                        case COMPONENT -> computer.setComponent(getXMLText(reader));
                        case ORIGIN -> computer.setOrigin(getXMLText(reader));
                        case PRICE -> computer.setPrice(Integer.parseInt(getXMLText(reader)));
                        case CRITICAL -> computer.setCritical(Boolean.parseBoolean(getXMLText(reader)));
                        case DATE -> {
                            String date = getXMLText(reader);
                            LocalDateTime dateTime = LocalDateTime.parse(date);
                            computer.setDate(dateTime);
                            break;
                        }
                        case TYPE -> computer.setType(getXMLType(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (ComputerXMLTag.valueOf(name.toUpperCase()) == ComputerXMLTag.DEVICE) {
                        logger.info("Component: " + computer.getComponent() + " set");
                        return computer;
                    }
            }
        }throw new XMLStreamException("Unknown element in tag <computer>");
    }

    private Computer.Type getXMLType(XMLStreamReader reader) throws XMLStreamException {
        Computer.Type type = new Computer.Type();
        int choice;
        String name;
        ArrayList<String> list = new ArrayList<String>();
        while (reader.hasNext()) {
            choice = reader.next();
            switch (choice) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (ComputerXMLTag.valueOf(name.toUpperCase())) {
                        case PERIPHERAL -> type.setPeripheral(Boolean.parseBoolean(getXMLText(reader)));
                        case COOLER -> type.setCooler(Boolean.parseBoolean(getXMLText(reader)));
                        case ENERGY -> type.setEnergy(Integer.parseInt(getXMLText(reader)));
                        case PORT -> {
                            String line = getXMLText(reader);
                            list.add(line);
                            break;
                        }
                    }
                    String[] strings = list.toArray(new String[0]);
                    type.setPorts(strings);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (ComputerXMLTag.valueOf(name.toUpperCase()) == ComputerXMLTag.TYPE) {
                        return type;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <type>");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
