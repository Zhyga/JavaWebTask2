package by.epam.entity;

public enum ComputerXMLTag {
    DEVICES("devices"),
    DEVICE("Device"),
    COMPONENT("component"),
    ORIGIN("origin"),
    CRITICAL("critical"),
    PRICE("price"),
    DATE("date"),
    ID("id"),
    ENERGY("energy"),
    PORT("port"),
    COOLER("cooler"),
    PERIPHERAL("peripheral"),
    TYPE("type"),
    PICK("pick"),
    MANUFACTURER("manufacturer");
    private String value;
    ComputerXMLTag(String value) {this.value = value;  }
    public String getValue() {return value;  }
}
