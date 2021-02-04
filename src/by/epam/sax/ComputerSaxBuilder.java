package by.epam.sax;

import by.epam.builder.ComputerParsingBuilder;
import by.epam.exception.ParserException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class ComputerSaxBuilder extends ComputerParsingBuilder {
    private ComputerHandler handler = new ComputerHandler();
    private XMLReader reader;

    public ComputerSaxBuilder() {// reader configuration
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        }
        catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace(); // log
        }
        reader.setErrorHandler(new ComputerErrorHandler());
        reader.setContentHandler(handler);
    }

    @Override
    public void buildSetComputers(String fileName) throws ParserException {
        try {
            reader.parse(fileName);
        } catch (IOException | SAXException e) {
           throw new ParserException("Failed to Sax parse" + e.getMessage());
        }
        computers = handler.getComputers();
    }
}
