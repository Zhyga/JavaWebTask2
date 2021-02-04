package by.epam.sax;

import by.epam.exception.ParserException;

import java.util.Arrays;

public class SaxConsoleMain {

    public static void main(String[] args) throws ParserException {
        ComputerSaxBuilder saxBuilder = new ComputerSaxBuilder();
        saxBuilder.buildSetComputers("data_xml/computer.xml");
        System.out.println(saxBuilder.getComputers());
    }
}


