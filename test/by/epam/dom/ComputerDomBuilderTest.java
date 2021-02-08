package by.epam.dom;

import by.epam.builder.ComputerBuilder;
import by.epam.entity.Computer;
import by.epam.exception.CustomParserException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import warehouse.ComputerWarehouse;
import java.util.Set;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class ComputerDomBuilderTest {
    private ComputerBuilder builder;

    @BeforeClass
    public void setUp() {
        try {
            builder = new ComputerDomBuilder();
        } catch (CustomParserException e) {
            fail("Incorrect input");
        }
    }

    @AfterClass
    public void tearDown() {
        builder = null;
    }

    @Test
    public void buildComputerTest() {
        try {
            Set<Computer> expected = ComputerWarehouse.getComputers();
            builder.buildSetComputers("data/computer.xml");
            Set<Computer> actual = builder.getComputers();
            assertEquals(actual, expected);

        } catch (CustomParserException e) {
            fail("Incorrect input");
        }
    }
}
