package by.epam.builder;

import by.epam.entity.Computer;
import by.epam.exception.ParserException;

import java.util.HashSet;
import java.util.Set;

public abstract class ComputerParsingBuilder {

    protected Set<Computer> computers;

    public ComputerParsingBuilder() {
        computers = new HashSet<Computer>();
    }

    public ComputerParsingBuilder(Set<Computer> computers) {
        this.computers = computers;
    }

    public Set<Computer> getComputers() {
        return computers;
    }

    abstract public void buildSetComputers(String fileName) throws ParserException;
}
