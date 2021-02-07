package by.epam.sax;

import by.epam.builder.ComputerBuilder;
import by.epam.exception.CustomParserException;
import by.epam.handler.ComputerErrorHandler;
import by.epam.handler.ComputerHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class ComputerSaxBuilder extends ComputerBuilder {
    private ComputerHandler handler = new ComputerHandler();
    private XMLReader reader;

    public ComputerSaxBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        }
        catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        reader.setErrorHandler(new ComputerErrorHandler());
        reader.setContentHandler(handler);
    }

    @Override
    public void buildSetComputers(String fileName) throws CustomParserException {
        try {
            reader.parse(fileName);
        } catch (IOException | SAXException e) {
           throw new CustomParserException("Failed to Sax parse",e);
        }
        computers = handler.getComputers();
    }
}
