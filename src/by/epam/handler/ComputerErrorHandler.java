package by.epam.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class ComputerErrorHandler implements ErrorHandler {
    private static Logger logger = LogManager.getLogger();

    public void warning(SAXParseException exception) {
        logger.warn(getLineColumnNumber(exception) + "-" + exception.getMessage());
    }

    public void error(SAXParseException exception) {
        logger.error(getLineColumnNumber(exception) + " - " + exception.getMessage());
    }

    public void fatalError(SAXParseException exception) {
        logger.fatal(getLineColumnNumber(exception) + " - " + exception.getMessage());
    }

    private String getLineColumnNumber(SAXParseException exception) {
        return exception.getLineNumber() + " : " + exception.getColumnNumber();
    }
}
