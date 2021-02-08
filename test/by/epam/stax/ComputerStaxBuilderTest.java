package by.epam.stax;

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

public class ComputerStaxBuilderTest {
    private ComputerBuilder builder;

    @BeforeClass
    public void setUp() {
        builder = new ComputerStaxBuilder();
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
