package by.epam.sax;

import by.epam.entity.Computer;
import by.epam.entity.ComputerXMLTag;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class ComputerHandler extends DefaultHandler{
    private Set<Computer> computers;
    private Computer current;
    private ComputerXMLTag currentXmlTag;
    private EnumSet<ComputerXMLTag> withText;
    private ArrayList<String> list = new ArrayList<String>();
    private static final String ELEMENT_DEVICE = ComputerXMLTag.DEVICE.getValue();

    public ComputerHandler() {
        computers = new HashSet<Computer>();
        withText = EnumSet.range(ComputerXMLTag.COMPONENT, ComputerXMLTag.PERIPHERAL);
    }

    public Set<Computer> getComputers() {
        return computers;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (ELEMENT_DEVICE.equals(qName)) {
            current = new Computer();
           setAttributes(attrs);
        } else {
            ComputerXMLTag temp = ComputerXMLTag.valueOf(qName.toUpperCase());
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (ELEMENT_DEVICE.equals(qName)) {
            String[] strings = list.toArray(new String[0]);
            current.getType().setPorts(strings);
            computers.add(current);
            list.clear();
        }
    }

    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case COMPONENT -> current.setComponent(data);
                case ORIGIN -> current.setOrigin(data);
                case PRICE -> current.setPrice(Integer.parseInt(data));
                case ID -> current.setId(data);
                case CRITICAL -> current.setCritical(Boolean.parseBoolean(data));
                case DATE -> {
                    String date = data;
                    LocalDateTime dateTime = LocalDateTime.parse(date);
                    current.setDate(dateTime);
                    break;
                }
                case PERIPHERAL -> current.getType().setPeripheral(Boolean.parseBoolean(data));
                case ENERGY -> current.getType().setEnergy(Integer.parseInt(data));
                case COOLER -> current.getType().setCooler(Boolean.parseBoolean(data));
                case PORT -> {
                    list.add(data);
                    break;
                }
                default -> throw new EnumConstantNotPresentException(
                        currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }

    private void setAttributes(Attributes attrs){
        int size = attrs.getLength();
        for(int i = 0; i < size; i++){
            String localName = attrs.getLocalName(i);
            ComputerXMLTag temp = ComputerXMLTag.valueOf(localName.toUpperCase());
            switch(temp) {
                case PICK -> current.setPick(attrs.getValue(i));
                case MANUFACTURER -> current.setManufacturer(attrs.getValue(i));
                default -> {
                    break;
                }
            }
        }
    }
}
