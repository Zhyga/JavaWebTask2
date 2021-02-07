package by.epam.validator;

import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import by.epam.handler.ComputerErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
public class BaseXMLValidator {
    private static Logger logger = LogManager.getLogger();

    public boolean  isXMLValid(String fileName, String schemaName) {
        boolean isValid = false;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.setErrorHandler(new ComputerErrorHandler());
            validator.validate(source);
            isValid = true;
        } catch (SAXException | IOException e) {
            logger.error(fileName + " is not correct or valid");
        }
        return isValid;
    }
}
