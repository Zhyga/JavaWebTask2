package by.epam.dom;

import by.epam.exception.ParserException;

public class DomConsoleMain {
    public static void main(String[] args) throws ParserException {
        ComputerDomBuilder domBuilder = new ComputerDomBuilder();
        domBuilder.buildSetComputers("data_xml/computer.xml");
        System.out.println(domBuilder.getComputers());
    }
}
