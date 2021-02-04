package by.epam.stax;

import by.epam.exception.ParserException;

public class StaxConsoleMain {
    public static void main(String[] args) throws ParserException {
        ComputerStaxBuilder staxBuilder = new ComputerStaxBuilder();
        staxBuilder.buildSetComputers("data_xml/computer.xml");
        System.out.println(staxBuilder.getComputers());
    }
}
