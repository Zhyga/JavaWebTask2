package by.epam.builder;

import by.epam.entity.Computer;
import by.epam.exception.CustomParserException;

import java.util.HashSet;
import java.util.Set;

public abstract class ComputerBuilder {

    protected Set<Computer> computers;

    public ComputerBuilder() {
        computers = new HashSet<>();
    }

    public ComputerBuilder(Set<Computer> computers) {
        this.computers = computers;
    }

    public Set<Computer> getComputers() {
        return computers;
    }

    abstract public void buildSetComputers(String fileName) throws CustomParserException;
}
